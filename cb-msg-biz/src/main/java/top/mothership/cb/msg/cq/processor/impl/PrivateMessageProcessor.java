package top.mothership.cb.msg.cq.processor.impl;

import top.mothership.cb.msg.cq.context.CqMessageContext;
import top.mothership.cb.msg.cq.processor.CqMessageProcessor;
import top.mothership.cb.msg.enums.cq.Lv2Type;

public class PrivateMessageProcessor implements CqMessageProcessor {
    @Override
    public void process(String rawCqMessage) {

    }

    @Override
    public boolean isFit() {
        return CqMessageContext.getLv2Type() == Lv2Type.PRIVATE_MESSAGE;
    }
}
