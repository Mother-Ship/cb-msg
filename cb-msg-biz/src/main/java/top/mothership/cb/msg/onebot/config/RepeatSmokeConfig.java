package top.mothership.cb.msg.onebot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import top.mothership.cb.msg.model.onebot.OneBotActionRoute;
import top.mothership.cb.msg.model.onebot.RepeatSmokeSetting;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "onebot.repeat")
@Data
public class RepeatSmokeConfig {
    private List<RepeatSmokeSetting> smoke = new ArrayList<>();

    public Optional<RepeatSmokeSetting> getByGroupId(Long groupId) {
        return smoke.stream().filter(s -> Objects.equals(s.getGroupId(), groupId)).findFirst();
    }
}
