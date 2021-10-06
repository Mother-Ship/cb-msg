package top.mothership.cb.msg.cq;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Component;
import top.mothership.cb.msg.model.GroupMessage;
import top.mothership.cb.msg.regex.CqMessagePattern;

import java.util.Map;

@Component
public class RepeatQueue {
    private static final String COLONS = ":";
    @Autowired
    private ListOperations<String, GroupMessage> listOps;

    @Autowired
    private HashOperations<String, String, Integer> hashOps;


    public void put(GroupMessage message) {

        String queueKey = message.getGroupId() + COLONS + "queue";
        String counterKey = message.getGroupId() + COLONS + "counter";

        //不确定是否需要初始化操作

        //纯媒体，图片+符号（去除图片后文本长度<3）不计数
        if (StringUtils.length(message.getText()) > 3) {
            //如果文本是汉字字母数字混杂符号，则计数去除符号内容；文本为纯符号则计数原文本
            hashOps.increment(counterKey, deInterfering(message.getText()), 1);
        }

        listOps.rightPush(queueKey, message);

        Long queueSize = listOps.size(queueKey);
        if (queueSize != null && queueSize > 100) {
            GroupMessage removed = listOps.leftPop(queueKey);

            if (removed != null) {
                if (StringUtils.length(removed.getText()) > 3) {
                    hashOps.increment(counterKey, deInterfering(removed.getText()), -1);
                }
            }
        }

        //对比此群最近100条消息中每条消息出现的次数
        for (Map.Entry<String, Integer> counter : hashOps.entries(counterKey).entrySet()) {
            if (counter.getValue() < 6) {
                continue;
            }
            //禁言操作

        }

    }

    private String deInterfering(String message) {
        String deInterfered = CqMessagePattern.REPEAT_DE_INTERFERING_REGEX.matcher(message)
                .replaceAll("");

        return StringUtils.isBlank(deInterfered) ? message : deInterfered;
    }

}
