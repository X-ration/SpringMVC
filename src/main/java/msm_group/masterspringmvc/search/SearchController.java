package msm_group.masterspringmvc.search;

import msm_group.masterspringmvc.linkedin.Job;
import msm_group.masterspringmvc.linkedin.LightJob;
import msm_group.masterspringmvc.profile.ProfileForm;
import msm_group.masterspringmvc.profile.UserProfileSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SearchController {

    private SearchService searchService;
    private UserProfileSession userProfileSession;

    @Autowired
    public SearchController(SearchService searchService, UserProfileSession userProfileSession) {
        this.searchService = searchService;
        this.userProfileSession = userProfileSession;
    }

    @ModelAttribute
    public ProfileForm getProfileForm() {
        return userProfileSession.toForm();
    }

    @RequestMapping("/search/{searchType}")
    public ModelAndView search(@PathVariable String searchType, @MatrixVariable List<String> keywords) {
        List<LightJob> jobs = searchService.search(searchType, keywords);
        ModelAndView modelAndView = new ModelAndView("resultPage");
        modelAndView.addObject("jobs_searched", jobs);
        modelAndView.addObject("keyword_search", String.join(",",keywords));
        return modelAndView;
    }

}