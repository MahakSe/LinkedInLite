package Service;

import Entity.Experience;
import Repository.UserProfileRepository;

public class ExperienceService {
    public static void addExperience(Experience experience) {
        UserProfileRepository.userSignedIn.getProfileSections().add(experience);
    }
}
