package Repository;

import Entity.ProfileSection;

import java.util.ArrayList;

public class ProfileSectionRepository {

    public static ProfileSection getProfileSectionById(Long id, ArrayList<ProfileSection> profileSections) {
        for(ProfileSection profileSection: profileSections){
            if(profileSection.getId().equals(id)) {
                return profileSection;
            }
        }
        return null;
    }
}
