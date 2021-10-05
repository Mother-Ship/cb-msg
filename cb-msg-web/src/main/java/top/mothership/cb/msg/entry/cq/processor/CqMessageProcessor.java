package top.mothership.cb.msg.entry.cq.processor;

import top.mothership.cb.msg.model.BaseCqMessage;

public interface CqMessageProcessor {
    void process(String rawCqMessage);

    boolean isFit(BaseCqMessage baseCqMessage);
}
