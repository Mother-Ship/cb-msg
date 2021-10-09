package top.mothership.cb.msg.model.onebot.action;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import top.mothership.cb.msg.enums.onebot.Lv3Type;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public final class GroupMemberRequestAction extends BaseOneBotAction {
    private Long groupId;
}
