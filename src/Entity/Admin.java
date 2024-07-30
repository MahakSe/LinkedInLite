package Entity;

import java.util.ArrayList;

public class Admin extends UserProfile {

    private int adminLevel;
    private ArrayList<AdminAction> actions = new ArrayList<>();

    public Admin(String firstName, String lastName, String email, String username, String password) {
        super(firstName, lastName, email, username, password);
        this.adminLevel = 1;
    }



    public int getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(int adminLevel) {
        this.adminLevel = adminLevel;
    }

    public ArrayList<AdminAction> getActions() {
        return actions;
    }

}
