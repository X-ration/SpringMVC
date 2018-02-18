package msm_group.masterspringmvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@Profile("sst")
@EnableRedisHttpSession
public class RedisConfig {

}
