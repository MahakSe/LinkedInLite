package Service;

import Entity.OrganizationProfile;
import Entity.UserProfile;
import Repository.UserProfileRepository;

import java.util.ArrayList;

public class OrganizationService {

    public static ArrayList<OrganizationProfile> searchCompanies(String searchQuery) {
        ArrayList<OrganizationProfile> results = new ArrayList<>();

        for (UserProfile profile : UserProfileRepository.profiles) {
            if (profile instanceof OrganizationProfile) {
                OrganizationProfile orgProfile = (OrganizationProfile) profile;

                // Ensure getSchool() is not null before proceeding
                if (orgProfile.getSchool() != null && !orgProfile.getSchool()) {
                    if ((orgProfile.getEmail() != null && orgProfile.getEmail().toLowerCase().contains(searchQuery.toLowerCase())) ||
                            (orgProfile.getUsername() != null && orgProfile.getUsername().toLowerCase().contains(searchQuery.toLowerCase()))) {
                        results.add(orgProfile);
                    }
                }
            }
        }
        return results;
    }

    public static ArrayList<OrganizationProfile> searchSchools(String searchQuery) {
        ArrayList<OrganizationProfile> results = new ArrayList<>();

        for (UserProfile profile : UserProfileRepository.profiles) {
            if (profile instanceof OrganizationProfile) {
                OrganizationProfile orgProfile = (OrganizationProfile) profile;

                // Ensure getSchool() is not null and true before proceeding
                if (orgProfile.getSchool() != null && orgProfile.getSchool()) {
                    if ((orgProfile.getEmail() != null && orgProfile.getEmail().toLowerCase().contains(searchQuery.toLowerCase())) ||
                            (orgProfile.getUsername() != null && orgProfile.getUsername().toLowerCase().contains(searchQuery.toLowerCase()))) {
                        results.add(orgProfile);
                    }
                }
            }
        }
        return results;
    }

}
