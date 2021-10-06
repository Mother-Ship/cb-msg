package top.mothership.cb.msg.entry;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.mothership.cb.msg.context.CqMessageContext;
import top.mothership.cb.msg.entry.CqMessageProcessingContext;
import top.mothership.cb.msg.enums.cq.Lv2Type;
import top.mothership.cb.msg.enums.cq.Lv3Type;
import top.mothership.cb.msg.model.BaseCqMessage;

@RestController
@Slf4j
public class EntryController {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CqMessageProcessingContext cqMessageProcessContext;


    @PostMapping("/cq_entry")
    @SneakyThrows
    public void cqEntry(@RequestBody String json) {
        var baseCqMessage = objectMapper.readValue(json, BaseCqMessage.class);

        Lv2Type lv2 = Lv2Type.findByParent(baseCqMessage);
        Lv3Type lv3 = Lv3Type.findByParent(baseCqMessage);

        if (lv2 == null){
            log.warn("分析消息类型错误，baseCqMessage：{}，视情况随OneBot规范更新代码", json);
            return;
        }

        CqMessageContext.set(lv2, lv3, json);

        var processor = cqMessageProcessContext.getProcessor();
        processor.process(json);
    }
}
