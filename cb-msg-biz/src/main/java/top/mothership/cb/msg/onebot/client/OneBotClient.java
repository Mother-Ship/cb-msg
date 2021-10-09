package top.mothership.cb.msg.onebot.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.mothership.cb.msg.model.onebot.action.response.MessageInfo;
import top.mothership.cb.msg.model.onebot.action.response.OneBotResponse;
import top.mothership.cb.msg.model.onebot.action.*;

import java.util.Objects;

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
    private OneBotActionRouterConfig config;

    @SneakyThrows
    public OneBotResponse<MessageInfo> sendGroupMessage(GroupMessageAction message) {
        String url = config.getBySelfId(message.getSelfId())
                .orElseThrow(() -> new RuntimeException("根据发送者 " + message.getSelfId() + "未找到OneBot部署地址")).getUrl();
        RequestBody body = RequestBody.create(objectMapper.writeValueAsString(message), JSON);
        Request request = new Request.Builder()
                .url(url + "/send_group_msg")
                .post(body).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return objectMapper.readValue(Objects.requireNonNull(response.body()).string(), new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("群消息发送失败,消息："+  objectMapper.writeValueAsString(message), e);
        }
    }

    public void sendPrivateMessage(PrivateMessageAction message) {
    }

    public void sendGroupBan(GroupBanAction ban) {
    }

    public void kick(GroupKickAction kick) {
    }

    public void handleGroupAddRequest(GroupInviteResponseAction response) {
    }

    public void getGroupMember(Long groupId) {
    }

    private Integer getPort(Long selfQQ) {
        return null;
    }
}
