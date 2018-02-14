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
    public String hello(@RequestParam(value = "keyword", defaultValue = "") String keyword, Model model) {

        String text = linkedIn.getUserProfile().getEmail();
        model.addAttribute("profile_email", text);
        model.addAttribute("keyword_search",keyword);
        model.addAttribute("jobs_searched", linkedIn.searchJobs(keyword));

        return "resultPage";
    }

}
