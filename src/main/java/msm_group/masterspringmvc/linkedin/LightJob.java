package msm_group.masterspringmvc.linkedin;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class LightJob {

    private String title;
    private String company;
    private String location;
    private String description;

    private LocalDateTime dateTime;
    private String jobImageUrl;
    private String lang;
    private Integer popularity;

    public LightJob(String title, String company, String location, String description, LocalDateTime dateTime, String jobImageUrl, String lang, Integer popularity) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.description = description;
        this.dateTime = dateTime;
        this.jobImageUrl = jobImageUrl;
        this.lang = lang;
        this.popularity = popularity;
    }

    public static LightJob toLightJob(Job job) {
        LightJob lightJob = new LightJob(job.getTitle(),job.getCompany(),job.getLocation(),job.getDescription(),
                null,job.getImageUrl(),job.getLanguageCode(),job.getPopularity());
        Date createdAt = job.getCreatedAt();
        if(createdAt != null) {
            lightJob.dateTime = LocalDateTime.ofInstant(createdAt.toInstant(), ZoneId.systemDefault());
        }
        return lightJob;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getJobImageUrl() {
        return jobImageUrl;
    }

    public void setJobImageUrl(String jobImageUrl) {
        this.jobImageUrl = jobImageUrl;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }
}
