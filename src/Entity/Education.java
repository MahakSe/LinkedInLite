package Entity;

import java.util.Date;

public class Education extends ProfileSection {
    private String filed = "";
    private Degree degree;
    private OrganizationProfile organization;
    private Date startDate;
    private Date endDate;
    private String grade;

    public Education(String title, OrganizationProfile organization, String filed, Degree degree, Date startDate, Date endDate, String grade) {
        super(title);
        this.filed = filed;
        this.degree = degree;
        this.organization = organization;
        this.startDate = startDate;
        this.endDate = endDate;
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }

    public OrganizationProfile getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationProfile organization) {
        this.organization = organization;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
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

    /*@Override
    public String getDetails() {
        return "Education at " + institution + ": " + getTitle() + ", Graduated in " + graduationYear;
    }*/
}
