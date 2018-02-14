package msm_group.masterspringmvc.linkedin;

public class Job {

    private String title;
    private String company;
    private String location;
    private String description;

    public Job(String title, String company, String location, String description) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Title:" + title + "\r\n"
                + "Company:" + company + "\r\n"
                + "Location:" + location + "\r\n"
                + "Description" + description + "\r\n";
    }
}
