package top.mothership.cb.msg.onebot.processor.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.mothership.cb.CbCmdService;
import top.mothership.cb.cmd.model.response.CbCmdResponse;
import top.mothership.cb.msg.enums.onebot.Lv2Type;
import top.mothership.cb.msg.model.onebot.action.GroupMessageAction;
import top.mothership.cb.msg.model.onebot.event.GroupMessageEvent;
import top.mothership.cb.msg.onebot.RepeatQueue;
import top.mothership.cb.msg.onebot.client.OneBotClient;
import top.mothership.cb.msg.onebot.context.OneBotMessageContext;
import top.mothership.cb.msg.onebot.processor.OneBotMessageProcessor;
import top.mothership.cb.msg.regex.OneBotMessagePattern;
import top.mothership.cb.msg.utils.onebot.OneBotMessageUtil;

@Component
public class GroupMessageProcessor implements OneBotMessageProcessor {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RepeatQueue repeatQueue;
    @Autowired
    private OneBotClient oneBotClient;
    @Autowired
    private CbCmdService cbCmdService;

    @SneakyThrows
    @Override
    public void process(String rawMessage) {
        val event = objectMapper.readValue(rawMessage, GroupMessageEvent.class);

        event.setText(OneBotMessagePattern.CQ_CODE.matcher(event.getMessage()).replaceAll(""));
        repeatQueue.tryPut(event);

        CbCmdResponse response = cbCmdService.cmd(event.getText());

        if (response == null || response.isEmpty()){
            return;
        }

        val action = GroupMessageAction
                .fromSourceEvent(event)
                .message(OneBotMessageUtil.toCqCode(response))
                .build();
        oneBotClient.sendGroupMessage(action);

    }

    @Override
    public boolean isFit() {
        return OneBotMessageContext.getLv2Type() == Lv2Type.GROUP_MESSAGE;
    }
}
