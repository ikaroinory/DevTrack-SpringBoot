package cn.auroralab.devtrack;

import cn.auroralab.devtrack.util.SpringContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(SpringContextUtils.class)
public class DevTrackSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(DevTrackSpringBootApplication.class, args);
    }
}
