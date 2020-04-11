package api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("service")
@Getter
@Setter
@Component
public class ServiceProperties {

    private String directory;
}
