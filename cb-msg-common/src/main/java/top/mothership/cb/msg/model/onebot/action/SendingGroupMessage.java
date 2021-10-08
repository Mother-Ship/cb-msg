package top.mothership.cb.msg.model.onebot.action;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SendingGroupMessage extends BaseOneBotAction {
    private Long groupId;
    private String message;
}
