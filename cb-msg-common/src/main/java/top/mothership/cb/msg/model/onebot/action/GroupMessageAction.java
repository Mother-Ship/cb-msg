package top.mothership.cb.msg.model.onebot.action;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import top.mothership.cb.msg.model.onebot.event.GroupMessageEvent;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class GroupMessageAction extends BaseOneBotAction {
    private Long groupId;
    private String message;


    @SuppressWarnings("rawtypes")
    public static GroupMessageActionBuilder fromSourceEvent(GroupMessageEvent event){
        return GroupMessageAction.builder()
                .groupId(event.getGroupId())
                .selfId(event.getSelfId());
    }
}
