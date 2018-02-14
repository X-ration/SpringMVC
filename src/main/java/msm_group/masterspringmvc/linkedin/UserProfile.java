package msm_group.masterspringmvc.linkedin;

public class UserProfile {

    private String firstName;
    private String lastName;
    private String email;
    private String profession;
    private String company;
    private String location;

    public UserProfile(String firstName, String lastName, String email, String profession, String company, String location) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profession = profession;
        this.company = company;
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
