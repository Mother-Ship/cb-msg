package top.mothership.cb.msg.enums.cq.lv2;

import io.swagger.annotations.ApiModelProperty;
import top.mothership.cb.msg.enums.cq.PostType;

public enum NoticeType implements Lv2Type {

    @ApiModelProperty("群文件上传")
    GROUP_UPLOAD,

    @ApiModelProperty("群管理员变动")
    GROUP_ADMIN,

    @ApiModelProperty("群成员减少")
    GROUP_DECREASE,

    @ApiModelProperty("群成员增加")
    GROUP_INCREASE,

    @ApiModelProperty("群禁言")
    GROUP_BAN,

    @ApiModelProperty("好友添加")
    FRIEND_ADD,

    @ApiModelProperty("群聊撤回")
    GROUP_RECALL,

    @ApiModelProperty("好友撤回")
    FRIEND_RECALL,

    @ApiModelProperty("窗口抖动/群内戳一戳/运气王/荣誉变更")
    NOTIFY,

    @ApiModelProperty("群成员名片变动")
    GROUP_CARD,

    @ApiModelProperty("离线文件")
    OFFLINE_FILE,

    @ApiModelProperty("其他客户端上下线")
    CLIENT_STATUS,

    @ApiModelProperty("精华消息")
    ESSENCE,


    ;
    @Override
    public PostType getParentType() {
        return PostType.NOTICE;
    }
}
