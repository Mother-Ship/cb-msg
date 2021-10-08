package top.mothership.cb.msg.enums.onebot;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import top.mothership.cb.msg.model.onebot.event.BaseOneBotEvent;
import top.mothership.cb.msg.utils.CbEnumUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

import static top.mothership.cb.msg.enums.onebot.PostType.*;

@Getter
@ToString
@AllArgsConstructor
@Slf4j
public class Lv2Type {

    public static final Map<PostType, List<Lv2Type>> VALUES = new LinkedHashMap<>();
    @ApiModelProperty("群消息")
    public static Lv2Type GROUP_MESSAGE = new Lv2Type(MESSAGE, "group");
    @ApiModelProperty("私聊消息")
    public static Lv2Type PRIVATE_MESSAGE = new Lv2Type(MESSAGE, "private");
    @ApiModelProperty("群文件上传")
    public static Lv2Type GROUP_UPLOAD = new Lv2Type(NOTICE, "group_upload");
    @ApiModelProperty("群管理员变动")
    public static Lv2Type GROUP_ADMIN = new Lv2Type(NOTICE, "group_admin");
    @ApiModelProperty("群成员减少")
    public static Lv2Type GROUP_DECREASE = new Lv2Type(NOTICE, "group_decrease");
    @ApiModelProperty("群成员增加")
    public static Lv2Type GROUP_INCREASE = new Lv2Type(NOTICE, "group_increase");
    @ApiModelProperty("群禁言")
    public static Lv2Type GROUP_BAN = new Lv2Type(NOTICE, "group_ban");
    @ApiModelProperty("好友添加")
    public static Lv2Type FRIEND_ADD = new Lv2Type(NOTICE, "friend_add");
    @ApiModelProperty("群聊撤回")
    public static Lv2Type GROUP_RECALL = new Lv2Type(NOTICE, "group_recall");
    @ApiModelProperty("好友撤回")
    public static Lv2Type FRIEND_RECALL = new Lv2Type(NOTICE, "friend_recall");
    @ApiModelProperty("窗口抖动/群内戳一戳/运气王/荣誉变更")
    public static Lv2Type NOTIFY = new Lv2Type(NOTICE, "notify");
    @ApiModelProperty("群成员名片变动")
    public static Lv2Type GROUP_CARD = new Lv2Type(NOTICE, "group_card");
    @ApiModelProperty("离线文件")
    public static Lv2Type OFFLINE_FILE = new Lv2Type(NOTICE, "offline_file");
    @ApiModelProperty("其他客户端上下线")
    public static Lv2Type CLIENT_STATUS = new Lv2Type(NOTICE, "client_status");
    @ApiModelProperty("精华消息")
    public static Lv2Type ESSENCE = new Lv2Type(NOTICE, "essence");
    @ApiModelProperty("群请求")
    public static Lv2Type GROUP_REQUEST = new Lv2Type(REQUEST, "group");
    @ApiModelProperty("好友请求")
    public static Lv2Type FRIEND_REQUEST = new Lv2Type(REQUEST, "friend");

    static {
        Field[] fields = Lv2Type.class.getFields();
        List<Lv2Type> tempList = new ArrayList<>(fields.length);
        for (Field field : fields) {
            if (Modifier.isPublic(field.getModifiers())) {
                try {
                    if (field.get(null) instanceof Lv2Type)
                        tempList.add((Lv2Type) field.get(null));
                } catch (IllegalAccessException ignore) {
                }
            }
        }
        VALUES.putAll(tempList.stream().collect(Collectors.groupingBy(Lv2Type::getParent)));
    }

    private final PostType parent;

    private final String name;

    public static Lv2Type findByParent(BaseOneBotEvent baseOneBotEvent) {
        PostType postType = CbEnumUtil.getOneBotEnum(PostType.class, baseOneBotEvent.getPostType());

        if (postType == null) {
            return null;
        }

        List<Lv2Type> list = VALUES.get(postType);
        String lv2TypeRaw = null;
        switch (postType) {
            case REQUEST:
                lv2TypeRaw = baseOneBotEvent.getRequestType();
                break;
            case NOTICE:
                lv2TypeRaw = baseOneBotEvent.getNoticeType();
                break;
            case MESSAGE:
                lv2TypeRaw = baseOneBotEvent.getMessageType();
                break;
        }

        for (Lv2Type lv2Type : list) {
            if (Objects.equals(lv2TypeRaw, lv2Type.getName())){
                return lv2Type;
            }
        }

        return null;
    }
}
