package msm_group.masterspringmvc.search;

import msm_group.masterspringmvc.MasterSpringMvcApplication;
import msm_group.masterspringmvc.linkedin.LightJob;
import msm_group.masterspringmvc.linkedin.LightJobBuilder;
import msm_group.masterspringmvc.profile.UserProfileSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author adam
 * 对SearchController的Mock测试。
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MasterSpringMvcApplication.class)
public class SearchControllerMockTest {

    @Mock
    private SearchService searchService;

    @Mock
    private UserProfileSession userProfileSession;

    @InjectMocks
    private SearchController searchController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(searchController)
                .setRemoveSemicolonContent(false)
                .build();
    }

    @Test
    public void should_search() throws Exception {

        when(searchService.search(anyString(), anyListOf(String.class)))
                .thenReturn(Arrays.asList(new LightJobBuilder().title("job").build()
                ));

        this.mockMvc.perform(get("/search/mixed;keywords=spring"))
                .andExpect(status().isOk())
                .andExpect(view().name("resultPage"))
                .andExpect(model().attribute("jobs_searched",everyItem(hasProperty("title",is("job"))
                )));

        verify(searchService, times(1)).search(anyString(),anyListOf(String.class));

    }

}
