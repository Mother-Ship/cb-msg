package top.mothership.cb.msg.cq.processor.impl;

import org.springframework.stereotype.Component;
import top.mothership.cb.msg.cq.context.CqMessageContext;
import top.mothership.cb.msg.cq.processor.CqMessageProcessor;
import top.mothership.cb.msg.enums.cq.Lv2Type;
import top.mothership.cb.msg.enums.cq.Lv3Type;

@Component
public class GroupInviteProcessor implements CqMessageProcessor {
    @Override
    public void process(String rawCqMessage) {

    }

    @Override
    public boolean isFit() {
        return CqMessageContext.getLv2Type() == Lv2Type.GROUP_REQUEST
         && CqMessageContext.getLv3Type() == Lv3Type.INVITE_REQUEST;
    }
}
