package top.mothership.cb.msg.enums.onebot;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import top.mothership.cb.msg.model.onebot.event.BaseOneBotEvent;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@ToString
@Slf4j
public class Lv3Type {
    public static final Map<Lv2Type, List<Lv3Type>> VALUES = new LinkedHashMap<>();
    @ApiModelProperty("普通消息")
    public static Lv3Type NORMAL_GROUP_MESSAGE = new Lv3Type(Lv2Type.GROUP_MESSAGE, "normal");
    @ApiModelProperty("匿名消息")
    public static Lv3Type ANONYMOUS_GROUP_MESSAGE = new Lv3Type(Lv2Type.GROUP_MESSAGE, "anonymous");
    @ApiModelProperty("群内消息提示")
    public static Lv3Type NOTICE_GROUP_MESSAGE = new Lv3Type(Lv2Type.GROUP_MESSAGE, "notice");
    @ApiModelProperty("精华消息扇出")
    public static Lv3Type DELETE_ESSENCE_NOTIFY = new Lv3Type(Lv2Type.ESSENCE, "delete");
    @ApiModelProperty("设置群管理")
    public static Lv3Type SET = new Lv3Type(Lv2Type.GROUP_ADMIN, "set");
    @ApiModelProperty("取消群管理")
    public static Lv3Type UNSET = new Lv3Type(Lv2Type.GROUP_ADMIN, "set");
    @ApiModelProperty("主动退群")
    public static Lv3Type LEAVE = new Lv3Type(Lv2Type.GROUP_DECREASE, "leave");
    @ApiModelProperty("成员被踢")
    public static Lv3Type KICK = new Lv3Type(Lv2Type.GROUP_DECREASE, "kick");
    @ApiModelProperty("本体被踢出")
    public static Lv3Type KICK_ME = new Lv3Type(Lv2Type.GROUP_DECREASE, "kick_me");
    @ApiModelProperty("管理员审批新成员入群")
    public static Lv3Type APPROVE = new Lv3Type(Lv2Type.GROUP_INCREASE, "approve");
    @ApiModelProperty("有新成员被邀请入群")
    public static Lv3Type INVITE = new Lv3Type(Lv2Type.GROUP_INCREASE, "invite");
    @ApiModelProperty("设置禁言")
    public static Lv3Type BAN = new Lv3Type(Lv2Type.GROUP_BAN, "ban");
    @ApiModelProperty("解除禁言")
    public static Lv3Type LIFT_BAN = new Lv3Type(Lv2Type.GROUP_BAN, "lift_ban");
    @ApiModelProperty("戳一戳")
    public static Lv3Type POKE = new Lv3Type(Lv2Type.NOTIFY, "poke");
    @ApiModelProperty("红包运气王")
    public static Lv3Type LUCKY_KING = new Lv3Type(Lv2Type.NOTIFY, "lucky_king");
    @ApiModelProperty("群荣耀变更请求")
    public static Lv3Type HONOR = new Lv3Type(Lv2Type.NOTIFY, "honor");
    @ApiModelProperty("加群请求")
    public static Lv3Type ADD_REQUEST = new Lv3Type(Lv2Type.GROUP_REQUEST, "add");
    ;
    @ApiModelProperty("邀请本体入群")
    public static Lv3Type INVITE_REQUEST = new Lv3Type(Lv2Type.GROUP_REQUEST, "invite");

    static {
        Field[] fields = Lv3Type.class.getFields();
        List<Lv3Type> tempList = new ArrayList<>(fields.length);
        for (Field field : fields) {
            if (Modifier.isPublic(field.getModifiers())) {
                try {
                    if (field.get(null) instanceof Lv3Type)
                        tempList.add((Lv3Type) field.get(null));
                } catch (IllegalAccessException ignore) {
                }
            }
        }
        VALUES.putAll(tempList.stream().collect(Collectors.groupingBy(Lv3Type::getParent)));
    }

    private final Lv2Type parent;
    private final String name;

    public static Lv3Type findByParent(BaseOneBotEvent baseOneBotEvent) {
        Lv2Type lv2Type = Lv2Type.findByParent(baseOneBotEvent);
        if (lv2Type == null) {
            return null;
        }
        String lv3TypeRaw = baseOneBotEvent.getSubType();

        List<Lv3Type> list = VALUES.get(lv2Type);

        if (CollectionUtils.isEmpty(list)) {
            if (lv3TypeRaw != null) {
                log.warn("二级类型{}下未封装三级类型，但实际上报出现{}，视情况随OneBot规范更新代码", lv2Type, lv3TypeRaw);
            }
            return null;
        }


        for (Lv3Type lv3Type : list) {
            if (Objects.equals(lv3TypeRaw, lv3Type.getName())) {
                return lv3Type;
            }
        }

        log.warn("二级类型{}下出现未知三级类型{}，视情况随OneBot规范更新代码", lv2Type, lv3TypeRaw);

        return null;
    }
}
