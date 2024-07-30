package Service;

import Entity.ProfileSection;
import Entity.Skill;
import Repository.UserProfileRepository;

public class SkillService {
    public static void addSkill(Skill skill) {
        UserProfileRepository.userSignedIn.getProfileSections().add(skill);
    }
}
