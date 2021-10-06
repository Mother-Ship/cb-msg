package top.mothership.cb.msg.cq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import top.mothership.cb.msg.cq.processor.CqMessageProcessor;
import top.mothership.cb.msg.model.BaseCqMessage;

import java.util.List;

@Slf4j
@Component
public class CqMessageProcessingContext {

    @Autowired
    private List<CqMessageProcessor> cqHandleList;

    public CqMessageProcessor getProcessor() {
        for (CqMessageProcessor processor : cqHandleList) {
            if (processor.isFit()) {
                return processor;
            }
        }
        return null;
    }
}
