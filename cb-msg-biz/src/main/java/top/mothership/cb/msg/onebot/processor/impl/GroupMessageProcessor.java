package top.mothership.cb.msg.onebot.processor.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import top.mothership.cb.msg.onebot.RepeatQueue;
import top.mothership.cb.msg.onebot.context.OneBotMessageContext;
import top.mothership.cb.msg.onebot.processor.OneBotMessageProcessor;
import top.mothership.cb.msg.enums.onebot.Lv2Type;
import top.mothership.cb.msg.model.onebot.event.GroupMessageEvent;
import top.mothership.cb.msg.regex.OneBotMessagePattern;

public class GroupMessageProcessor implements OneBotMessageProcessor {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RepeatQueue repeatQueue;

    @SneakyThrows
    @Override
    public void process(String rawMessage) {
        GroupMessageEvent message = objectMapper.readValue(rawMessage, GroupMessageEvent.class);

        message.setText(OneBotMessagePattern.CQ_CODE.matcher(message.getMessage()).replaceAll(""));
        repeatQueue.put(message);


    }

    @Override
    public boolean isFit() {
        return OneBotMessageContext.getLv2Type() == Lv2Type.GROUP_MESSAGE;
    }
}
