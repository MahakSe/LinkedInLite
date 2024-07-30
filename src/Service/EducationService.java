package Service;

import Entity.Education;
import Entity.Experience;
import Repository.UserProfileRepository;

public class EducationService {
    public static void addEducation(Education education) {
        UserProfileRepository.userSignedIn.getProfileSections().add(education);
    }
}
