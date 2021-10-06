package top.mothership.cb.msg.cq.processor.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import top.mothership.cb.msg.cq.RepeatQueue;
import top.mothership.cb.msg.cq.context.CqMessageContext;
import top.mothership.cb.msg.cq.processor.CqMessageProcessor;
import top.mothership.cb.msg.enums.cq.Lv2Type;
import top.mothership.cb.msg.model.GroupMessage;
import top.mothership.cb.msg.regex.CqMessagePattern;

public class GroupMessageProcessor implements CqMessageProcessor {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RepeatQueue repeatQueue;

    @SneakyThrows
    @Override
    public void process(String rawCqMessage) {
        GroupMessage message = objectMapper.readValue(rawCqMessage, GroupMessage.class);

        message.setText(CqMessagePattern.CQ_CODE.matcher(message.getMessage()).replaceAll(""));
        repeatQueue.put(message);


    }

    @Override
    public boolean isFit() {
        return CqMessageContext.getLv2Type() == Lv2Type.GROUP_MESSAGE;
    }
}
