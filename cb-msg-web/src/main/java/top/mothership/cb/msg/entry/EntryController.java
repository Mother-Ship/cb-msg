package top.mothership.cb.msg.entry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.mothership.cb.msg.context.CqMessageTypeContext;
import top.mothership.cb.msg.entry.cq.CqMessageProcessingContext;
import top.mothership.cb.msg.enums.cq.PostType;
import top.mothership.cb.msg.enums.cq.lv2.Lv2Type;
import top.mothership.cb.msg.enums.cq.lv2.MessageType;
import top.mothership.cb.msg.enums.cq.lv2.NoticeType;
import top.mothership.cb.msg.enums.cq.lv2.RequestType;
import top.mothership.cb.msg.model.BaseCqMessage;
import top.mothership.cb.msg.utils.CbEnumUtil;

import java.util.ServiceLoader;

@RestController
public class EntryController {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CqMessageProcessingContext cqMessageHandleContext;

    @PostMapping("/cq_entry")
    public void cqEntry(@RequestBody String json) throws JsonProcessingException {
        var baseCqMessage = objectMapper.readValue(json, BaseCqMessage.class);

        var postType = CbEnumUtil.getCqType(PostType.class, baseCqMessage.getPostType());

        Lv2Type lv2Type = ServiceLoader.load(Lv2Type.class).stream().filter(
                lv2TypeProvider -> lv2TypeProvider.get().getParentType().equals(postType)
        ).findFirst().orElseThrow(()-> new RuntimeException("根据PostType没有找到二级Type枚举")).get();

        //根据二级消息类型，从请求体对应的字段中抽取二级类型


        CqMessageTypeContext.set();


        var processor = cqMessageHandleContext.getProcessor(baseCqMessage);
        processor.process(json);
    }
}
