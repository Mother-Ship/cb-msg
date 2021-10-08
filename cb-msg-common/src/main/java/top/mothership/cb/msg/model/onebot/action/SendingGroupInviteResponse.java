package top.mothership.cb.msg.model.onebot.action;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.mothership.cb.msg.enums.onebot.Lv3Type;

@EqualsAndHashCode(callSuper = true)
@Data
public class SendingGroupInviteResponse extends BaseOneBotAction {
    private String subType= Lv3Type.INVITE_REQUEST.getName();
    private String flag;
    private Boolean approve;
}
