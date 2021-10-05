package top.mothership.cb.msg.enums.cq.lv2;

import io.swagger.annotations.ApiModelProperty;
import top.mothership.cb.msg.enums.cq.PostType;

public enum RequestType implements Lv2Type {
    @ApiModelProperty("群请求")
    GROUP,

    @ApiModelProperty("加好友请求")
    FRIEND,
    ;
    @Override
    public PostType getParentType() {
        return PostType.REQUEST;
    }
}
