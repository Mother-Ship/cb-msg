package top.mothership.cb.msg.onebot.processor.impl;

import org.springframework.stereotype.Component;
import top.mothership.cb.msg.onebot.context.OneBotMessageContext;
import top.mothership.cb.msg.onebot.processor.OneBotMessageProcessor;
import top.mothership.cb.msg.enums.onebot.Lv2Type;
import top.mothership.cb.msg.enums.onebot.Lv3Type;

@Component
public class GroupInviteProcessor implements OneBotMessageProcessor {
    @Override
    public void process(String rawMessage) {

    }

    @Override
    public boolean isFit() {
        return OneBotMessageContext.getLv2Type() == Lv2Type.GROUP_REQUEST
         && OneBotMessageContext.getLv3Type() == Lv3Type.INVITE_REQUEST;
    }
}