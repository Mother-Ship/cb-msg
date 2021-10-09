package top.mothership.cb.msg.model.onebot.action.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GroupMemberInfo {
    @ApiModelProperty("成员QQ号")
    private Long userId;

    @ApiModelProperty("群号")
    private Long groupId;

    @ApiModelProperty("群名片")
    private String card;

    @ApiModelProperty("角色")
    private String role;
}
