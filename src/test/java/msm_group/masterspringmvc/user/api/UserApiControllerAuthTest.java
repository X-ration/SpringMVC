package msm_group.masterspringmvc.user.api;

import msm_group.masterspringmvc.MasterSpringMvcApplication;
import msm_group.masterspringmvc.config.WebSecurityConfiguration;
import msm_group.masterspringmvc.user.UserBuilder;
import msm_group.masterspringmvc.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author adam
 * 创建于 2018-02-21 17:52.
 * 对Basic认证的测试。
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MasterSpringMvcApplication.class)
public class UserApiControllerAuthTest {

    @Autowired
    private FilterChainProxy springSecurityFilter;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilter).build();
        userRepository.reset(new UserBuilder().email("bob@spring.io").build());
    }

    /**
     * 对GET /api/users/ 的测试，当认证未通过。
     * @throws Exception
     */
    @Test
    public void unauthenticated_cannot_list_users() throws Exception {
        this.mockMvc.perform(
                get("/api/users/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    /**
     * 对GET /api/users/ 的测试，当以admin身份登录时。
     * @throws Exception
     */
    @Test
    public void admin_can_list_users() throws Exception {
        this.mockMvc.perform(
                get("/api/users/")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("Authorization", basicAuth("admin","admin")))
                .andExpect(status().isOk());
    }

    /**
     * 将一组Basic认证的用户名和密码转换为HTTP请求头格式。
     * @param login Basic认证用户名
     * @param password Basic认证用户密码
     * @return Basic认证的HTTP请求格式化字符串
     */
    private String basicAuth(String login, String password) {
        byte[] auth = (login + ":" + password).getBytes();
        return "Basic " + Base64.getEncoder().encodeToString(auth);
    }

}
