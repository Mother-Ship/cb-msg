package top.mothership.cb.msg.model.onebot.action;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SendingPrivateMessage extends BaseOneBotAction {
    private Long userId;
    private String message;
}
