package top.mothership.cb.msg.entry.cq.processor.impl;

import org.springframework.stereotype.Component;
import top.mothership.cb.msg.entry.cq.processor.CqMessageProcessor;
import top.mothership.cb.msg.model.BaseCqMessage;
@Component
public class GroupInviteProcessor implements CqMessageProcessor {
    @Override
    public void process(String rawCqMessage) {

    }

    @Override
    public boolean isFit(BaseCqMessage baseCqMessage) {
        return false;
    }
}
