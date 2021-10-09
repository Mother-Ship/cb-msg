package top.mothership.cb.msg.onebot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import top.mothership.cb.msg.model.onebot.OneBotActionRoute;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "onebot")
@Data
public class OneBotActionRouteConfig {
    private List<OneBotActionRoute> route = new ArrayList<>();


    public Optional<OneBotActionRoute> getBySelfId(Long selfId) {
        return route.stream().filter(r -> Objects.equals(r.getSelfId(), selfId)).findFirst();
    }
}
