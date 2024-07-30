package Entity;

import java.util.ArrayList;

public class OrganizationProfile extends UserProfile {
    private ArrayList<UserProfile> employees;
    private Boolean isSchool;
    public OrganizationProfile(String firstName, String lastName, String email, String username, String password) {
        super(firstName, lastName, email, username, password);
        this.employees = new ArrayList<>();
    }

    public ArrayList<UserProfile> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<UserProfile> employees) {
        this.employees = employees;
    }

    public Boolean getSchool() {
        return isSchool;
    }

    public void setSchool(Boolean school) {
        isSchool = school;
    }
}
