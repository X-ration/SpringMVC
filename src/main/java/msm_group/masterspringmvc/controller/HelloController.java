package msm_group.masterspringmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @RequestMapping("/")
    public String hello(@RequestParam(value = "name", defaultValue = "AnyUser", required = true) String userName, Model model) {
        model.addAttribute("message","Hello from the controller, " + userName);
        return "resultPage";
    }

}
