package top.mothership.cb.msg.model.onebot.event;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.mothership.cb.msg.enums.onebot.GroupMemberRole;
import top.mothership.cb.msg.utils.CbEnumUtil;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroupMessageEvent extends BaseOneBotEvent {
    @ApiModelProperty("消息ID")
    private Integer messageId;
    @ApiModelProperty("群ID")
    private Long groupId;
    @ApiModelProperty("发送人QQ")
    private Long userId;
    @ApiModelProperty("消息体")
    private String message;
    @ApiModelProperty("消息体纯文本部分，去除CQ码")
    private String text;
    @ApiModelProperty("消息体命令部分，感叹号开头空格结束的文本")
    private String command;
    @ApiModelProperty("发送人信息对象")
    private Sender sender;
    @ApiModelProperty("收消息的QQ")
    private Long selfId;

    public boolean isSendByAdmin(){
        return sender !=null
                && GroupMemberRole.ADMIN.equals(
                        CbEnumUtil.getOneBotEnum(GroupMemberRole.class, sender.getRole()));
    }
}
