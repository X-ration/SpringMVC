package msm_group.masterspringmvc.search;

import msm_group.masterspringmvc.linkedin.LightJobBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;

/**
 * @author adam
 * 对SearchController的Stub测试中LinkedInSearch接口的替代实现。
 */
@Configuration
public class StubLinkedInSearchConfig {

    @Primary @Bean
    public LinkedInSearch linkedInSearch() {
        return ((searchType, keywords) -> Arrays.asList(
                new LightJobBuilder().title("jobTitle").build(),
                new LightJobBuilder().title("secondJob").build())
        );
    }

}
