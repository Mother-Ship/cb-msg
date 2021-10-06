package top.mothership.cb.msg.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroupMessage extends BaseCqMessage{
    private Integer messageId;
    private Long groupId;
    private Long userId;
    private String message;
}
