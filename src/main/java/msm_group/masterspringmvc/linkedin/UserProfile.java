package msm_group.masterspringmvc.linkedin;

public class UserProfile {

    private String name;
    private String email;
    private String profession;
    private String company;
    private String location;

    public UserProfile(String name, String email, String profession, String company, String location) {
        this.name = name;
        this.email = email;
        this.profession = profession;
        this.company = company;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
