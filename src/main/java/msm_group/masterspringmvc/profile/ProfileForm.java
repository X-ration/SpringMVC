package msm_group.masterspringmvc.profile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//POJO, plain old Java object

public class ProfileForm {

    private String linkedInHandle;
    private String email;
    private LocalDate birthDate;
    private List<String> tastes = new ArrayList<>();

    public String getLinkedInHandle() {
        return linkedInHandle;
    }

    public void setLinkedInHandle(String linkedInHandle) {
        this.linkedInHandle = linkedInHandle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getTastes() {
        return tastes;
    }

    public void setTastes(List<String> tastes) {
        this.tastes = tastes;
    }

    @Override
    public String toString() {
        return "[ProfileForm] " + linkedInHandle + " " + email + " "
                + birthDate + " " + tastes + "\n";
    }
}
