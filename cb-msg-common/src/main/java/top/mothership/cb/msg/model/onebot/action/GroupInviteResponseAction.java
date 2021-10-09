package top.mothership.cb.msg.model.onebot.action;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import top.mothership.cb.msg.enums.onebot.Lv3Type;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public final class GroupInviteResponseAction extends BaseOneBotAction {
    private String subType= Lv3Type.INVITE_REQUEST.getName();
    private String flag;
    private Boolean approve;
}
