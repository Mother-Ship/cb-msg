import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import top.mothership.cb.msg.Application;
import top.mothership.cb.msg.onebot.config.OneBotActionRouteConfig;

import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = Application.class)
@TestPropertySource(properties = "spring.profiles.active=local")
class OneBotActionRouteConfigTest {
    @Autowired
    private OneBotActionRouteConfig oneBotActionRouteConfig;

    @Test
    @SneakyThrows
    public void testNacosConfig() {
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(oneBotActionRouteConfig.getRoute());
        }

    }

}