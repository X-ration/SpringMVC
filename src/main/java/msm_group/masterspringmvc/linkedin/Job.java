package msm_group.masterspringmvc.linkedin;

import java.util.Date;

public class Job {

    private String title;
    private String company;
    private String location;
    private String description;

    //根据Tweet补充的附加属性
    private Date createdAt;
    private String imageUrl;
    private Integer popularity;
    private String languageCode;

    public Job(String title, String company, String location, String description, Date createdAt, String imageUrl, Integer popularity, String languageCode) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.description = description;
        this.createdAt = createdAt;
        this.imageUrl = imageUrl;
        this.popularity = popularity;
        this.languageCode = languageCode;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    @Override
    public String toString() {
        return "[Job]Title:" + title + "\r\n"
                + "Company:" + company + "\r\n"
                + "Location:" + location + "\r\n"
                + "Description" + description + "\r\n";
    }
}
