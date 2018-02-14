package msm_group.masterspringmvc.controller;

import msm_group.masterspringmvc.linkedin.LinkedIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value = "/postSearch", method = RequestMethod.POST)
    public String postSearch(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String keyword = request.getParameter("keyword");
        if(keyword.equals("")) {
            redirectAttributes.addFlashAttribute("error","Empty word not permitted! Please try another word.");
            return "redirect:/";
        }
        if(linkedIn.searchJobs(keyword).size() == 0) {
            redirectAttributes.addFlashAttribute("error", "No result found! Please try another word.");
            return "redirect:/";
        }
        redirectAttributes.addAttribute("keyword", keyword);
        return "redirect:result";
    }


}
