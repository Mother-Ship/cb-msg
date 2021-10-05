package top.mothership.cb.msg.enums.cq.lv2;

import io.swagger.annotations.ApiModelProperty;
import top.mothership.cb.msg.enums.cq.PostType;


public enum MessageType implements Lv2Type {
    @ApiModelProperty("私聊消息")
    PRIVATE,

    @ApiModelProperty("群消息")
    GROUP
    ;


    @Override
    public PostType getParentType() {
        return PostType.MESSAGE;
    }
}
