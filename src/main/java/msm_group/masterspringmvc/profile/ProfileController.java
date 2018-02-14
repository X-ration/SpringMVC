package msm_group.masterspringmvc.profile;

import msm_group.masterspringmvc.util.USLocalDateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class ProfileController {

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String displayProfile(ProfileForm profileForm) {
        return "profile/profilePage";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String saveProfile(@Valid ProfileForm profileForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            System.out.println("[POST]has error");
            return "profile/profilePage";
        }

        System.out.println("[POST]save ok" + profileForm);
        return "redirect:/profile";
    }

    @ModelAttribute("dateFormat")  //暴露dateFormat属性给Web页面
    public String localeFormat(Locale locale) {
        return USLocalDateFormatter.getPattern(locale);
    }

}
