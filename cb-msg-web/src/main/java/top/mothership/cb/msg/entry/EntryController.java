package top.mothership.cb.msg.entry;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.mothership.cb.msg.onebot.OneBotMessageProcessingContext;
import top.mothership.cb.msg.onebot.context.OneBotMessageContext;
import top.mothership.cb.msg.enums.onebot.Lv2Type;
import top.mothership.cb.msg.enums.onebot.Lv3Type;
import top.mothership.cb.msg.model.onebot.event.BaseOneBotEvent;

@RestController
@Slf4j
public class EntryController {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private OneBotMessageProcessingContext oneBotMessageProcessingContext;


    @PostMapping("/one_bot_entry")
    @SneakyThrows
    public void oneBotEntry(@RequestBody String json) {
        var baseOneBotMessage = objectMapper.readValue(json, BaseOneBotEvent.class);

        Lv2Type lv2 = Lv2Type.findByParent(baseOneBotMessage);
        Lv3Type lv3 = Lv3Type.findByParent(baseOneBotMessage);

        if (lv2 == null){
            log.warn("分析消息类型错误，baseOneBotMessage：{}，视情况随OneBot规范更新代码", json);
            return;
        }

        OneBotMessageContext.set(lv2, lv3, json);

        var processor = oneBotMessageProcessingContext.getProcessor();
        processor.process(json);
    }
}
