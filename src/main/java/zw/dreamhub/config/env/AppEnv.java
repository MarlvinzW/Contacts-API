package zw.dreamhub.config.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 9/6/2022
 */

@ConfigurationProperties(prefix = "app")
@Component
@Data
public class AppEnv {
    private Application application;
    private Url url;

    @Data
    public static class Url {
        private String unSecured;
        private String secured;
    }

    @Data
    public static class Application {
        private String defaultAppName;
    }

}
