package top.mothership.cb.msg.onebot;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Component;
import top.mothership.cb.msg.model.onebot.event.GroupMessageEvent;
import top.mothership.cb.msg.regex.OneBotMessagePattern;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@Slf4j
@RefreshScope
public class RepeatQueue {
    private static final String COLONS = ":";
    @Value("onebot.repeat.smoke")
    private Map<String,Integer> repeatSmokeConfig;

    @Autowired
    private ListOperations<String, GroupMessageEvent> listOps;

    @Autowired
    private HashOperations<String, String, Integer> hashOps;

    @Autowired
    private RedissonClient redissonClient;

    //需要保证对同一个群两个Key的操作是原子性的

    public void put(GroupMessageEvent message) {
        RLock lock = redissonClient.getLock(String.valueOf(message.getGroupId()));
        try {


            boolean lockSuccess = lock.tryLock(500, TimeUnit.MILLISECONDS);
            if (!lockSuccess) {
                log.warn("【禁言队列】群号" + message.getGroupId() + "获取锁失败");
                return;
            }
            String queueKey = message.getGroupId() + COLONS + "queue";
            String counterKey = message.getGroupId() + COLONS + "counter";


            //将消息压入队列，并更新计数器
            //纯媒体，图片+符号（去除图片后文本长度<3）不计数
            if (StringUtils.length(message.getText()) > 3) {
                //如果文本是汉字字母数字混杂符号，则对去除符号内容计数；文本为纯符号，则对原文本计数
                hashOps.increment(counterKey, deInterfering(message.getText()), 1);
            }
            listOps.rightPush(queueKey, message);

            //如果队列满，则从左出队，并更新计数器
            Long queueSize = listOps.size(queueKey);
            if (queueSize != null && queueSize > 100) {
                GroupMessageEvent removed = listOps.leftPop(queueKey);
                if (removed != null) {
                    if (StringUtils.length(removed.getText()) > 3) {
                        hashOps.increment(counterKey, deInterfering(removed.getText()), -1);
                    }
                }
            }

            //对消息计数器进行遍历，找到大于六条的消息
            List<String> repeatingMessage =  hashOps.entries(counterKey).entrySet()
                    .stream().filter(e->e.getValue() > 6)
                    .map(Map.Entry::getKey).collect(Collectors.toList());
            if (repeatingMessage.contains(message.getText())
                    && message.isSendByAdmin()){

            }

        } catch (Exception e) {
            log.warn("【复读队列】群号{}操作复读计数器、复读队列异常", message.getGroupId(), e);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

    }

    private String deInterfering(String message) {
        String deInterfered = OneBotMessagePattern.REPEAT_DE_INTERFERING_REGEX.matcher(message)
                .replaceAll("");

        return StringUtils.isBlank(deInterfered) ? message : deInterfered;
    }

}
