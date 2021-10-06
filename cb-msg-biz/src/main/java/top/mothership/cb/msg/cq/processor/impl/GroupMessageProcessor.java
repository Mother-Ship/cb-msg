package top.mothership.cb.msg.entry.cq.processor.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import top.mothership.cb.msg.context.CqMessageContext;
import top.mothership.cb.msg.entry.cq.processor.CqMessageProcessor;
import top.mothership.cb.msg.enums.cq.Lv2Type;
import top.mothership.cb.msg.model.GroupMessage;

public class GroupMessageProcessor implements CqMessageProcessor {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RepeatService repeatService;

    @SneakyThrows
    @Override
    public void process(String rawCqMessage) {
        GroupMessage message = objectMapper.readValue(rawCqMessage, GroupMessage.class);

    }

    @Override
    public boolean isFit() {
        return CqMessageContext.getLv2Type() == Lv2Type.GROUP_MESSAGE;
    }
}
