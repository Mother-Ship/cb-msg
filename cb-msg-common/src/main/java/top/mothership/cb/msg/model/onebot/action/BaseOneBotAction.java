package top.mothership.cb.msg.model.onebot.action;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class BaseOneBotAction {
    private Long selfId;
}
