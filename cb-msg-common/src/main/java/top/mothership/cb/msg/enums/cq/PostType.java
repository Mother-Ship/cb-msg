package top.mothership.cb.msg.enums.cq;

import io.swagger.annotations.ApiModelProperty;

public enum PostType {
    @ApiModelProperty("消息")
    MESSAGE,

    @ApiModelProperty("通知")
    NOTICE,

    @ApiModelProperty("请求")
    REQUEST,
    ;

}
