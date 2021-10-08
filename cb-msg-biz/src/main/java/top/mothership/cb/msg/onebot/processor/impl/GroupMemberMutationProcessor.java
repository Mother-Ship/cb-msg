package top.mothership.cb.msg.onebot.processor.impl;

import top.mothership.cb.msg.onebot.context.OneBotMessageContext;
import top.mothership.cb.msg.onebot.processor.OneBotMessageProcessor;
import top.mothership.cb.msg.enums.onebot.Lv2Type;

public class GroupMemberMutationProcessor implements OneBotMessageProcessor {
    @Override
    public void process(String rawMessage) {

    }

    @Override
    public boolean isFit() {
        return OneBotMessageContext.getLv2Type() == Lv2Type.GROUP_INCREASE
                || OneBotMessageContext.getLv2Type() == Lv2Type.GROUP_DECREASE;
    }
}
