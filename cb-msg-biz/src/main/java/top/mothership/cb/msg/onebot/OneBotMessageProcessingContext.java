package top.mothership.cb.msg.onebot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import top.mothership.cb.msg.onebot.processor.OneBotMessageProcessor;

import java.util.List;

@Slf4j
@Component
public class OneBotMessageProcessingContext {

    @Autowired
    private List<OneBotMessageProcessor> processors;

    public OneBotMessageProcessor getProcessor() {
        for (OneBotMessageProcessor processor : processors) {
            if (processor.isFit()) {
                return processor;
            }
        }
        return null;
    }
}
