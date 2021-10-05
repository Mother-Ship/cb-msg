package top.mothership.cb.msg.entry.cq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.mothership.cb.msg.entry.cq.processor.CqMessageProcessor;
import top.mothership.cb.msg.model.BaseCqMessage;

import java.util.List;

@Slf4j
@Component
public class CqMessageProcessingContext {

    @Autowired
    private List<CqMessageProcessor> cqHandleList;

    public CqMessageProcessor getProcessor(BaseCqMessage baseCqMessage) {
        for (CqMessageProcessor processor : cqHandleList) {
            if (processor.isFit(baseCqMessage)) {
                return processor;
            }
        }
        return null;
    }
}
