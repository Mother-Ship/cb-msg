package top.mothership.cb.msg.onebot;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import top.mothership.cb.msg.onebot.processor.OneBotMessageProcessor;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class OneBotMessageProcessingContext {

    @Autowired
    private List<OneBotMessageProcessor> processors;

    public Optional<OneBotMessageProcessor> getProcessor() {
        for (val processor : processors) {
            if (processor.isFit()) {
                return Optional.of(processor);
            }
        }
        return Optional.empty();
    }
}
