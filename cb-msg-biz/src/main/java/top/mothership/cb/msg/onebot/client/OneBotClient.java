package top.mothership.cb.msg.onebot.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.mothership.cb.msg.model.onebot.OneBotResponse;
import top.mothership.cb.msg.model.onebot.action.*;

import java.util.Objects;

@Component
@Slf4j
public class OneBotClient {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private static final String domain = "http://onebot.mothership.top";
    @Autowired
    private OkHttpClient okHttpClient;
    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    public OneBotResponse<Long> sendGroupMessage(SendingGroupMessage message) {
        RequestBody body = RequestBody.create(objectMapper.writeValueAsString(message), JSON);
        Request request = new Request.Builder()
                .url(domain + ":" + getPort(message.getSelfId()) + "/send_group_msg")
                .post(body).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            return objectMapper.readValue(Objects.requireNonNull(response.body()).string(), new TypeReference<>() {
            });
        } catch (Exception e) {
            log.error("群消息发送失败,消息：{}", objectMapper.writeValueAsString(message), e);
        }
    }

    public void sendPrivateMessage(SendingPrivateMessage message) {
    }

    public void sendGroupBan(SendingGroupBan ban) {
    }

    public void kick(SendingGroupKick kick) {
    }

    public void handleGroupAddRequest(SendingGroupInviteResponse response) {
    }

    public void getGroupMember(Long groupId) {
    }

    private Integer getPort(Long selfQQ) {
        return null;
    }
}
