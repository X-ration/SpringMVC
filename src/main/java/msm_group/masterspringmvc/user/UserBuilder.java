package msm_group.masterspringmvc.user;

/**
 * @author adam
 * 创建于 2018-02-21 16:51.
 */
public class UserBuilder {

    private final User user;

    public UserBuilder() {
        user = new User();
    }

    public UserBuilder email(String email) {
        user.setEmail(email);
        return this;
    }

    public User build() {
        return user;
    }

}
