package top.mothership.cb.msg.entry.cq.processor.impl;

import top.mothership.cb.msg.entry.cq.processor.CqMessageProcessor;
import top.mothership.cb.msg.model.BaseCqMessage;

public class GroupMemberMutationProcessor implements CqMessageProcessor {
    @Override
    public void process(String rawCqMessage) {

    }

    @Override
    public boolean isFit(BaseCqMessage baseCqMessage) {
        return false;
    }
}
