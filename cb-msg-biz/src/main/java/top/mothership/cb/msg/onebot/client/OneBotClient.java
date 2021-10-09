package top.mothership.cb.msg.onebot.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.mothership.cb.msg.enums.onebot.GroupMemberRole;
import top.mothership.cb.msg.model.onebot.OneBotActionRoute;
import top.mothership.cb.msg.model.onebot.action.*;
import top.mothership.cb.msg.model.onebot.action.response.GroupInfo;
import top.mothership.cb.msg.model.onebot.action.response.GroupMemberInfo;
import top.mothership.cb.msg.model.onebot.action.response.MessageInfo;
import top.mothership.cb.msg.model.onebot.action.response.OneBotResponse;
import top.mothership.cb.msg.onebot.config.OneBotActionRouteConfig;
import top.mothership.cb.msg.utils.CbEnumUtil;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
public class OneBotClient {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private static final String domain = "http://onebot.mothership.top";

    private static final OkHttpClient okHttpClient = new OkHttpClient();

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private OneBotActionRouteConfig config;

    @SneakyThrows
    public OneBotResponse<MessageInfo> sendGroupMessage(GroupMessageAction action) {
        return objectMapper.readValue(send("/send_group_msg", action), new TypeReference<>() {
        });
    }

    @SneakyThrows
    public OneBotResponse<MessageInfo> sendPrivateMessage(PrivateMessageAction action) {
        return objectMapper.readValue(send("/send_private_msg", action), new TypeReference<>() {
        });
    }

    @SneakyThrows
    public OneBotResponse<Void> sendGroupBan(GroupBanAction action) {
        return objectMapper.readValue(send("/set_group_ban", action), new TypeReference<>() {
        });
    }

    @SneakyThrows
    public OneBotResponse<Void> kick(GroupKickAction action) {
        return objectMapper.readValue(send("/set_group_kick", action), new TypeReference<>() {
        });
    }

    @SneakyThrows
    public OneBotResponse<Void> handleGroupAddRequest(GroupInviteResponseAction action) {
        return objectMapper.readValue(send("/set_group_add_request", action), new TypeReference<>() {
        });
    }

    @SneakyThrows
    public OneBotResponse<List<GroupMemberInfo>> getGroupMember(Long groupId) {
        Long selfId = null;
        for (OneBotActionRoute oneBotActionRoute : config.getRoute()) {
            val infoRequestAction = GroupInfoRequestAction.builder()
                    .selfId(oneBotActionRoute.getSelfId())
                    .groupId(groupId).build();
            OneBotResponse<GroupInfo> groupInfo =
                    objectMapper.readValue(send("/get_group_info", infoRequestAction), new TypeReference<>() {
                    });
            if (groupInfo.success() && groupInfo.getData() != null && groupInfo.getData().getGroupCreateTime() != null) {
                selfId = oneBotActionRoute.getSelfId();
            }
        }

        if (selfId == null) {
            throw new RuntimeException("获取群成员信息失败，没有任何QQ在群：" + groupId);
        }

        val action = GroupMemberRequestAction.builder()
                .groupId(groupId)
                .selfId(selfId).build();
        return objectMapper.readValue(send("/get_group_member_list", action), new TypeReference<>() {
        });
    }

    public Long getOwner(Long groupId) {
        val members = getGroupMember(groupId);
        if (!members.success()) {
            return null;
        }
        val member = members.getData().stream()
                .filter(i ->
                        GroupMemberRole.OWNER.equals(
                                CbEnumUtil.getOneBotEnum(GroupMemberRole.class, i.getRole())))
                .findFirst();
        if (member.isEmpty()) {
            return null;
        }
        return member.get().getUserId();
    }

    @SneakyThrows
    private String send(String endPoint, BaseOneBotAction action) {
        String url = config.getBySelfId(action.getSelfId())
                .orElseThrow(() -> new RuntimeException("根据发送者 " + action.getSelfId() + "未找到OneBot部署地址")).getUrl();

        Request request = new Request.Builder()
                .url(url + endPoint)
                .post(RequestBody.create(objectMapper.writeValueAsString(action), JSON)).build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            return Objects.requireNonNull(response.body()).string();
        } catch (Exception e) {
            throw new RuntimeException("OneBot动作" + action.getClass().getSimpleName() + "发送失败,消息：" + objectMapper.writeValueAsString(action), e);
        }
    }
}
