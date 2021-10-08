package top.mothership.cb.msg.model.onebot.event;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BaseOneBotEvent {
    @ApiModelProperty("主类型")
    private String postType;

    @ApiModelProperty("二级类型")
    private String messageType;

    @ApiModelProperty("二级类型")
    private String noticeType;

    @ApiModelProperty("二级类型")
    private String requestType;

    @ApiModelProperty("三级类型")
    private String subType;

}
