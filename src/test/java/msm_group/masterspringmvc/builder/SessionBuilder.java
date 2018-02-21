package msm_group.masterspringmvc.builder;

import msm_group.masterspringmvc.profile.UserProfileSession;
import org.springframework.mock.web.MockHttpSession;

import java.util.Arrays;

public class SessionBuilder {

    private final MockHttpSession session;
    UserProfileSession sessionBean;

    public SessionBuilder() {
        session = new MockHttpSession();
        sessionBean = new UserProfileSession();
        //会话中代表Bean的属性会添加scopedTarget，Spring代理
        session.setAttribute("scopedTarget.userProfileSession", sessionBean);
    }

    public SessionBuilder userTastes(String... tastes) {
        sessionBean.setTastes(Arrays.asList(tastes));
        return this;
    }

    public MockHttpSession build() {
        return session;
    }

}
