package top.mothership.cb.msg.model.onebot.action;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class GroupBanAction extends BaseOneBotAction {
    private Long groupId;
    private Long userId;
    private Integer duration;
}
