package Service;

import Entity.*;
import Repository.UserProfileRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class UserService {
    static Scanner scanner = new Scanner(System.in);

    public static UserProfile login(String username, String password){
        ArrayList<UserProfile> userProfiles = UserProfileRepository.profiles;
        for (UserProfile user : userProfiles){
            if (user.getUsername().equals(username))
                if (user.getPassword().equals(password))
                    if (!user.getBanned()) {
                        UserProfileRepository.userSignedIn = user;
                        return user;
                    }
        }
        //user didn't exist
        return null;
    }

    public static UserProfile signup(String firstName, String lastName, String email, String username, String password){
        ArrayList<UserProfile> userProfiles = UserProfileRepository.profiles;

        for (UserProfile user : userProfiles){
            if (user.getUsername().equals(username)){
                // Username already exists
                return null;
            }
        }

        UserProfile newUser = new UserProfile(firstName, lastName, email, username, password);
        userProfiles.add(newUser);

        UserProfileRepository.userSignedIn = newUser;
        return newUser;
    }

    public static void deleteProfile(String username, String password){
        for (UserProfile user : UserProfileRepository.profiles){
            if (user.getUsername().equals(username)){
                if (user.getPassword().equals(password)) {
                    UserProfileRepository.profiles.remove(user);
                    return;
                }
            }
        }
    }


    public static ArrayList<UserProfile> searchUserProfile(String searchQuery) {
        ArrayList<UserProfile> profiles = UserProfileRepository.profiles;
        ArrayList<UserProfile> searchResults = new ArrayList<>();

        for (UserProfile profile : profiles) {
            // Check if searchQuery is present in any relevant fields of UserProfile
            if ((profile.getFirstName() != null && profile.getFirstName().toLowerCase().contains(searchQuery.toLowerCase())) ||
                    (profile.getLastName() != null && profile.getLastName().toLowerCase().contains(searchQuery.toLowerCase())) ||
                    (profile.getUsername() != null && profile.getUsername().toLowerCase().contains(searchQuery.toLowerCase())) ||
                    (profile.getEmail() != null && profile.getEmail().toLowerCase().contains(searchQuery.toLowerCase())) ||
                    (profile.getAbout() != null && profile.getAbout().toLowerCase().contains(searchQuery.toLowerCase())) ||
                    (profile.getHeadLine() != null && profile.getHeadLine().toLowerCase().contains(searchQuery.toLowerCase()))) {
                searchResults.add(profile);
            }
        }

        return searchResults;
    }


    public static void joinGroup(Group group) {
        UserProfile user = UserProfileRepository.userSignedIn;
        ArrayList<Group> groups = user.getGroups();
        groups.add(group);
        user.setGroups(groups);

        group.getMembers().add(user);

        user.getInterests().add(new Interest(InterestType.GROUP, group));

    }

    public static void followUser(UserProfile profile) {
        UserProfile user = UserProfileRepository.userSignedIn;
        user.getFollowings().add(profile);
        profile.getFollowers().add(user);

        if(profile instanceof OrganizationProfile && ((OrganizationProfile) profile).getSchool())
            user.getInterests().add(new Interest(InterestType.SCHOOL, profile));
        else if(profile instanceof OrganizationProfile && !((OrganizationProfile) profile).getSchool())
            user.getInterests().add(new Interest(InterestType.COMPANY, profile));
        else
            user.getInterests().add(new Interest(InterestType.USER, profile));

    }

    public static void connectWithUser(UserProfile user) {
        user.getConnectionRequests().add(new ConnectionRequest(UserProfileRepository.userSignedIn));
    }

    public static void acceptConnection(UserProfile userProfile, ConnectionRequest connectionRequest) {
        connectionRequest.setStatus(Status.ACCEPTED);
        UserProfile user = UserProfileRepository.userSignedIn;

        user.getFollowers().add(userProfile);
        user.getFollowings().add(userProfile);
        user.getConnections().add(userProfile);

        userProfile.getFollowers().add(user);
        userProfile.getFollowings().add(user);
        userProfile.getConnections().add(user);
    }

    public static void deleteComment(Post post, Comment comment) {
        post.getComments().remove(comment);
    }

    public static void endorse(UserProfile user, Skill skill) {
        skill.getEndorsements().add(user);
    }

    public static void rejectConnection(UserProfile userProfile, ConnectionRequest connection) {
        connection.setStatus(Status.REJECTED);
    }
}
