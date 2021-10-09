package top.mothership.cb.msg.onebot.client;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import top.mothership.cb.msg.model.onebot.OneBotActionRouter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "onebot")
@Data
public class OneBotActionRouterConfig {
    private List<OneBotActionRouter> router = new ArrayList<>();


    public Optional<OneBotActionRouter> getBySelfId(Long selfId) {
        return router.stream().filter(r -> Objects.equals(r.getSelfId(), selfId)).findFirst();
    }
}
