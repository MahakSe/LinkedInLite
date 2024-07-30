package Entity;

import java.util.ArrayList;

public class Skill extends ProfileSection{
    private ArrayList<UserProfile> endorsements;
    private ProfileSection skillUsageArea; //Experience and Education
    private ProficiencyLevel proficiencyLevel;

    public Skill(String title, ProfileSection skillUsageArea) {
        super(title);
        this.skillUsageArea = skillUsageArea;
    }

    public ProfileSection getSkillUsageArea() {
        return skillUsageArea;
    }

    public void setSkillUsageArea(ProfileSection skillUsageArea) {
        this.skillUsageArea = skillUsageArea;
    }

    public ArrayList<UserProfile> getEndorsements() {
        return endorsements;
    }

    public void setEndorsements(ArrayList<UserProfile> endorsements) {
        this.endorsements = endorsements;
    }
}
