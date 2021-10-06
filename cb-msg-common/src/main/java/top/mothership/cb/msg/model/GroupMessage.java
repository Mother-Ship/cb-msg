package top.mothership.cb.msg.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroupMessage extends BaseCqMessage {
    @ApiModelProperty("消息ID")
    private Integer messageId;
    @ApiModelProperty("群ID")
    private Long groupId;
    @ApiModelProperty("发送人")
    private Long userId;
    @ApiModelProperty("消息体")
    private String message;
    @ApiModelProperty("消息体纯文本部分，去除CQ码")
    private String text;
    @ApiModelProperty("消息体命令部分，感叹号开头空格结束的文本")
    private String command;
}
