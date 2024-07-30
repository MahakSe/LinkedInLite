package Entity;

import java.util.Date;

public class Experience extends ProfileSection{
    private OrganizationProfile organization;
    private Date startDate;
    private Date endDate;
    private ExperienceType experienceType;
    private String description = "";
    private String address;
    //private GeoLoc location;


    public Experience(String title, OrganizationProfile organization, Date startDate, Date endDate, ExperienceType experienceType, String description, String address) {
        super(title);
        this.organization = organization;
        this.endDate = endDate;
        this.startDate = startDate;
        this.experienceType = experienceType;
        this.description = description;
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public OrganizationProfile getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationProfile organization) {
        this.organization = organization;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ExperienceType getExperienceType() {
        return experienceType;
    }

    public void setExperienceType(ExperienceType experienceType) {
        this.experienceType = experienceType;
    }
}
