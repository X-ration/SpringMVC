package msm_group.masterspringmvc.controller;

import msm_group.masterspringmvc.linkedin.LinkedIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LinkedInController {

    private LinkedIn linkedIn = LinkedIn.getInstance();

    @RequestMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("profile_email", linkedIn.getUserProfile().getEmail());
        return "searchPage";
    }

    @RequestMapping(value = "/result")
    public String hello(@RequestParam(value = "keyword", defaultValue = "") String keyword, Model model) {

        model.addAttribute("profile_email", linkedIn.getUserProfile().getEmail());
        model.addAttribute("keyword_search",keyword);
        model.addAttribute("jobs_searched", linkedIn.searchJobs(keyword));

        return "resultPage";
    }

}
