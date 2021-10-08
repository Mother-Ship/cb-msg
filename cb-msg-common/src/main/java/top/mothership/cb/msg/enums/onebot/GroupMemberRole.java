package top.mothership.cb.msg.enums.onebot;

import io.swagger.annotations.ApiModelProperty;

public enum GroupMemberRole {
    @ApiModelProperty("群主")
    OWNER,
    @ApiModelProperty("管理员")
    ADMIN,
    @ApiModelProperty("成员")
    MEMBER,
    ;
}
