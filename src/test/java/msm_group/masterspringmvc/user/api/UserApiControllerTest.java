package msm_group.masterspringmvc.user.api;

/**
 * @author adam
 * 创建于 2018-02-21 16:45.
 * 对UserApiController的测试。
 */

import msm_group.masterspringmvc.MasterSpringMvcApplication;
import msm_group.masterspringmvc.user.User;
import msm_group.masterspringmvc.user.UserBuilder;
import msm_group.masterspringmvc.user.UserRepository;
import msm_group.masterspringmvc.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MasterSpringMvcApplication.class)
public class UserApiControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        userRepository.reset(new UserBuilder().email("bob@spring.io").build());
    }

    /**
     * 针对GET /api/users/ 的测试。
     * @throws Exception
     */
    @Test
    public void should_list_users() throws Exception {
        this.mockMvc.perform(
                get("/api/users/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].email",is("bob@spring.io")));
    }

    /**
     * 针对POST /api/users/ newUser 的测试。
     * @throws Exception
     */
    @Test
    public void should_create_new_user() throws Exception {
        User user = new UserBuilder().email("john@spring.io").build();
        this.mockMvc.perform(
                post("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.toJson(user)))
                .andExpect(status().isCreated());

        assertThat(userRepository.findAll())
                .extracting(User::getEmail)
                .containsOnly("bob@spring.io","john@spring.io");
    }

    /**
     * 针对DELETE /api/users/{userEmail} 的测试，当用户存在时。
     * @throws Exception
     */
    @Test
    public void should_delete_user() throws Exception {
        this.mockMvc.perform(
                delete("/api/users/bob@spring.io")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertThat(userRepository.findAll()).hasSize(0);
    }

    /**
     * 针对DELETE /api/users/{userEmail} 的测试，当用户不存在时。
     * @throws Exception
     */
    @Test
    public void should_return_not_found_when_deleting_unknown_user() throws Exception {
        this.mockMvc.perform(
                delete("/api/users/non-existing@mail.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    /**
     * 针对PUT /api/users/{userEmail} 的测试，当用户已经存在时。
     * @throws Exception
     */
    @Test
    public void put_should_update_existing_user() throws Exception {
        User user = new UserBuilder().email("ignored@spring.io").build();
        this.mockMvc.perform(
                put("/api/users/bob@spring.io")
                        .content(JsonUtil.toJson(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertThat(userRepository.findAll())
                .extracting(User::getEmail)
                .containsOnly("bob@spring.io");
    }

}
