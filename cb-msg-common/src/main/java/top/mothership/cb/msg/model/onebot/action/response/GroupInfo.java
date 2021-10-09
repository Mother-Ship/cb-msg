package top.mothership.cb.msg.model.onebot.action.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GroupInfo {
    @ApiModelProperty("群名")
    private String groupName;

    @ApiModelProperty("群号")
    private Long groupId;

    @ApiModelProperty("群创建时间")
    private Integer groupCreateTime;
}
