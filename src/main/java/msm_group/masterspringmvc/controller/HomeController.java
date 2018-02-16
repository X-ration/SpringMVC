package msm_group.masterspringmvc.controller;

import msm_group.masterspringmvc.profile.UserProfileSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class HomeController {
    private UserProfileSession userProfileSession;

    @Autowired
    public HomeController(UserProfileSession userProfileSession) {
        this.userProfileSession = userProfileSession;
    }

    @RequestMapping("/")
    public String home() {
        if(userProfileSession.toForm().getTastes().isEmpty()){
            return "redirect:/profile";
        } else {
            String s;
            try {
                s = "redirect:/search/mixed;keywords=" + URLEncoder.encode(String.join(",", String.join(",",userProfileSession.toForm().getTastes())), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                s="redirect:/search/mixed;keywords=";
            }
            return s;
        }
    }
}
