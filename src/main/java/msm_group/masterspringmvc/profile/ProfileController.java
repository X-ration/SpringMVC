package msm_group.masterspringmvc.profile;

import msm_group.masterspringmvc.linkedin.LinkedIn;
import msm_group.masterspringmvc.util.USLocalDateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;

@Controller
public class ProfileController {

    private UserProfileSession userProfileSession;
    private MessageSource messageSource;

    //构造函数注入。还有一种方式是域注入。
    @Autowired
    public ProfileController(UserProfileSession userProfileSession, MessageSource messageSource) {
        this.userProfileSession = userProfileSession;
        this.messageSource = messageSource;
    }

    @ModelAttribute
    public ProfileForm getProfileForm() {
        return userProfileSession.toForm();
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String displayProfile(ProfileForm profileForm) {
        return "profile/profilePage";
    }

    @RequestMapping(value = "/profile", params = {"save"}, method = RequestMethod.POST)
    public String saveProfile(@Valid ProfileForm profileForm, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, Locale locale) {
        if(bindingResult.hasErrors()) {
            System.out.println("[POST]has error");
            return "profile/profilePage";
        }
        //LinkedIn.getInstance().getUserProfile().setEmail(profileForm.getEmail());
        userProfileSession.saveForm(profileForm);
        String redirectPage;
        try {
            redirectPage = "redirect:/search/mixed;keywords=" +
                    URLEncoder.encode(String.join(",",profileForm.getTastes()), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            redirectPage = "redirect:/profile";
            //TODO
            redirectAttributes.addAttribute("error_submit",
                    messageSource.getMessage("upload.error.submit",null,locale));
        }
        return redirectPage;
    }

    @RequestMapping(value = "/profile", params = {"addTaste"})
    public String addRow(ProfileForm profileForm) {
        profileForm.getTastes().add(null);
        return "profile/profilePage";
    }

    @RequestMapping(value = "/profile", params = {"removeTaste"})
    public String removeRow(ProfileForm profileForm, HttpServletRequest httpServletRequest) {
        Integer rowId = Integer.valueOf(httpServletRequest.getParameter("removeTaste"));
        profileForm.getTastes().remove(rowId.intValue());
        return "profile/profilePage";
    }

    @ModelAttribute("dateFormat")  //暴露dateFormat属性给Web页面
    public String localeFormat(Locale locale) {
        return USLocalDateFormatter.getPattern(locale);
    }

}
