package UI;

import Entity.*;
import Repository.PostRepository;
import Repository.UserProfileRepository;
import Service.PostService;
import Service.UserService;

import java.util.ArrayList;
import java.util.Scanner;


public class UserUI {

    private static final Scanner scanner = new Scanner(System.in);

    public static UserProfile login() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        UserProfile user = UserService.login(username, password);
        if (user != null) {
            System.out.println("Login successful! Welcome " + user.getUsername());
        } else {
            System.out.println("Login failed! Invalid username or password.");
        }
        return user;
    }

    public static UserProfile signup() {
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        UserProfile user = UserService.signup(firstName, lastName, email, username, password);
        if (user != null) {
            System.out.println("Signup successful! Welcome " + user.getUsername());

        } else {
            System.out.println("Signup failed! Username already exists.");
        }
        return user;
    }

    public static void logout() {
        UserProfileRepository.userSignedIn = null;
        System.out.println("Logged out successfully.");
    }
    
    public static void loggedInPages() {
        if(UserProfileRepository.userSignedIn.getBanned()){
            System.out.println("This account is banned!");
            return;
        }

        while(true) {
            UserProfile user = UserProfileRepository.userSignedIn;
            if (user == null)
                return;
            System.out.println("1: Home\n2: My Network\n3: Messaging\n4: My Profile\n5: Search\n6: Logout");
            if (user instanceof Admin){
                System.out.println("7: Admin panel");
            }
            String option = scanner.nextLine();
            switch (option) {
                case "1": {
                    feed();
                    break;
                }
                case "2": {
                    network();
                    break;
                }
                case "3": {
                    MessagingUI.messaging();
                    break;
                }
                case "4": {
                    me();
                    break;
                }
                case "5": {
                    SearchUI.search();
                    break;
                }
                case "6": {
                    logout();
                    return;
                }
                case "7":{
                    if(user instanceof Admin)
                        AdminUI.adminPanel();

                    else
                        System.out.println("Invalid option!");

                    break;
                }
                default: {
                    System.out.println("Invalid option!");
                }
            }
        }
    }

    private static void me() {
        printProfile(UserProfileRepository.userSignedIn);
        while(true){
            System.out.println("""
                2: Add Profile Section
                3: View Skills
                4: View Education
                5: View Experiences
                6: Add Post
                7: Delete account
                8: Exit""");
            String option = scanner.nextLine();
            switch (option){
                case "2":{
                    addProfileSection();
                    break;
                }
                case "3":{
                    for(ProfileSection skill: UserProfileRepository.userSignedIn.getProfileSections()) {
                        if (skill instanceof Skill)
                            SkillUI.printSkill((Skill) skill);
                    }
                    System.out.println("""
                        1: Add Skill
                        2: Exit""");
                    String option2 = scanner.nextLine();
                    switch (option2){
                        case "1":{
                            SkillUI.addSkill();
                            break;
                        }
                        case "2":{
                            break;
                        }
                        default:{
                            System.out.println("Invalid option!");
                        }
                    }
                    break;
                }
                case "4": {
                    for(ProfileSection education: UserProfileRepository.userSignedIn.getProfileSections()) {
                        if (education instanceof Education)
                            EducationUI.printEducation((Education) education);
                    }
                    break;
                }
                case "5":{
                    for(ProfileSection experience: UserProfileRepository.userSignedIn.getProfileSections()) {
                        if (experience instanceof Experience)
                            ExperienceUI.printExperience((Experience) experience);
                    }
                    break;
                }
                case "6":{
                    PostUI.addPost();
                    break;
                }
                case "7":{
                    UserService.deleteProfile(UserProfileRepository.userSignedIn.getUsername(), UserProfileRepository.userSignedIn.getPassword());
                    UserProfileRepository.userSignedIn = null;
                    return;
                }
                case "8":{
                    return;
                }
                default:{
                    System.out.println("Invalid Option!");
                }
            }
        }
    }


    private static void addProfileSection() {
        while(true){
            System.out.println("1: Add Skill\n2: Add Experience\n3: Add Education\n4: Exit");
            String option = scanner.nextLine();
            switch (option){
                case "1":{
                    SkillUI.addSkill();
                    break;
                }
                case "2":{
                    ExperienceUI.addExperience();
                    break;
                }
                case "3":{
                    EducationUI.addEducation();
                    break;
                }
                case "4":{
                    return;
                }
                default:{
                    System.out.println("Invalid Option!");
                }
            }

        }
    }
    
    public static void network() {
        System.out.println("1: Connections\n2: Following and Followers\n3: Groups\n4: Connection requests\n5: Exit");
        String option = scanner.nextLine();
        switch (option) {
            case "1": {
                showConnections(UserProfileRepository.userSignedIn);
                break;
            }
            case "2": {
                ShowFollowersAndFollowings();
                break;
            }
            case "3": {
                ShowGroups();
                break;
            }
            case "4":
                viewConnectionRequests();
            case "5": {
                return;
            }
            default: {
                System.out.println("Invalid option");
            }
        }

    }

    private static void viewConnectionRequests() {
        boolean flag = true;
        UserProfile user = UserProfileRepository.userSignedIn;
        for(ConnectionRequest connection: user.getConnectionRequests()){
            if (connection.getStatus().equals(Status.PENDING)) {
                UserProfile userProfile = connection.getRequest();
                System.out.println(userProfile.getUsername());
                while (flag) {
                    System.out.println("""
                            1: See profile
                            2: Accept the request
                            3: Reject the request
                            4: Next
                            5: Exit""");
                    String option = scanner.nextLine();
                    switch (option) {
                        case "1": {
                            printProfile(userProfile);
                            break;
                        }
                        case "2": {

                            UserService.acceptConnection(userProfile, connection);
                            System.out.println("You are now connected!");

                            break;
                        }
                        case "3":{
                            UserService.rejectConnection(userProfile, connection);
                            System.out.println("Rejected!");

                            break;
                        }
                        case "4": {
                            flag = false;
                            break;
                        }
                        case "5": {
                            return;
                        }
                        default:
                            System.out.println("Invalid option!");
                    }
                }
            }
        }
    }

    public static void ShowGroups() {
        UserProfile user = UserProfileRepository.userSignedIn;
        ArrayList<Group> groups = user.getGroups();
        for (Group group:groups)
            System.out.println(group.getName());
        GroupUI.viewGroup(groups);
    }
    
    public static void ShowFollowersAndFollowings() {
        UserProfile user = UserProfileRepository.userSignedIn;
        ArrayList<UserProfile> followers = user.getFollowers();
        ArrayList<UserProfile> followings = user.getFollowings();

        while(true){
            System.out.println("1: Followers\n2: Following\n3: Exit\n");
            String option = scanner.nextLine();
            switch (option){
                case "1":{
                    System.out.println("Followers:\n");
                    for(UserProfile userProfile: followers){
                        System.out.println(userProfile.getUsername());
                    }
                    viewUserProfile(followers);
                    break;
                }
                case "2":{
                    System.out.println("Following:\n");
                    for(UserProfile userProfile: followings){
                        System.out.println(userProfile.getUsername());
                    }
                    viewUserProfile(followings);
                    break;
                }
                case "3": {return;}
                default:{
                    System.out.println("Invalid option");
                }
            }
        }


    }

    public static void viewUserProfile(ArrayList<UserProfile> users) {
        while(true) {
            System.out.println("1: View profile of a user\n2: Exit");
            String option = scanner.nextLine();
            switch (option)
            {
                case "1": {
                    System.out.println("Enter the username:\n");
                    String username = scanner.nextLine();
                    for (UserProfile user : users) {
                        if (user.getUsername().equals(username)) {
                            printProfile(user);
                            break;
                        }
                    }
                    System.out.println("User not found!\n");
                    break;
                }
                case "2": {
                    return;
                }
                default: {
                    System.out.println("Invalid option");
                }
            }
        }
    }
    
    private static void showConnections(UserProfile user) {
        ArrayList<UserProfile> connections = user.getConnections();
        for (UserProfile connection: connections){
            System.out.println(connection.getUsername());
        }
        viewUserProfile(connections);

    }

    private static void showFollowers(UserProfile user) {
        ArrayList<UserProfile> followers = user.getFollowers();
        for (UserProfile userProfile: followers){
            System.out.println(userProfile.getUsername());
        }
        viewUserProfile(followers);
    }


    public static void printProfile(UserProfile user) {
        System.out.println("\n--- Profile ---");
        System.out.println("Username: " + user.getUsername());

        if (!(user instanceof OrganizationProfile)) {
            if (user.getFirstName() != null && user.getLastName() != null) {
                System.out.println("Full Name: " + user.getFirstName() + " " + user.getLastName());
            }
        }

        if (user.getAbout() != null && !user.getAbout().isEmpty()) {
            System.out.println("About: " + user.getAbout());
        }

        boolean flag = true;
        while (flag) {
            System.out.println("""
                    1: View posts
                    2: View profile sections
                    3: View Followers
                    4: View Connections
                    5: View interests
                    6: View interactions
                    7: Exit""");

            String option = scanner.nextLine();
            switch (option) {
                case "1": {
                    viewAllPost(user);
                    break;
                }
                case "2": {
                    if (!(user instanceof OrganizationProfile)) {
                        printProfileSection(user);
                    }
                    break;
                }
                case "3":{
                    showFollowers(user);
                }
                case "4":{
                    System.out.println("Connections: " + user.getConnections().size());
                    showConnections(user);
                    break;
                }
                case "5":{
                    viewInterests(user);
                    break;
                }
                case "6": {
                    viewInteractions(user);
                }
                case "7": {
                    flag = false;
                    break;
                }
                default:
                    System.out.println("Invalid option!");
            }
        }
        System.out.println("--------------------\n");
        if(user != UserProfileRepository.userSignedIn)
            viewProfileOption(user);
    }

    private static void viewInteractions(UserProfile user) {
        if(user.getComments() != null) {
            for (Comment comment : user.getComments()) {
                PostUI.printPost((Post) comment.getPost());
                System.out.println(comment.getText());
            }
        }

    }

    private static void viewInterests(UserProfile user) {
        for(Interest interest: user.getInterests()){
            if(interest.getInterestType().equals(InterestType.USER)){
                System.out.println("User: " + ((UserProfile)(interest.getInterest())).getUsername());
            }
            else if(interest.getInterestType().equals(InterestType.COMPANY)){
                System.out.println("Company: " + ((OrganizationProfile)(interest.getInterest())).getUsername());
            }
            else if(interest.getInterestType().equals(InterestType.GROUP)){
                System.out.println("Group: " + ((Group)(interest.getInterest())).getName());
            }
            else if(interest.getInterestType().equals(InterestType.SCHOOL)){
                System.out.println("School: " + ((OrganizationProfile)(interest.getInterest())).getUsername());
            }
        }
    }


    public static void viewProfileOption(UserProfile user) {
        while (true) {
            System.out.println("""
                    1: Follow
                    2: Connect
                    3: Message
                    4: Report
                    5: Exit""");
            String option = scanner.nextLine();
            switch (option){
                case "1":{
                    UserService.followUser(user);
                    System.out.println("Followed successfully!");
                    break;
                }
                case "2":{
                    UserService.connectWithUser(user);
                    System.out.println("Connection request sent.");
                    break;
                }
                case "3":{
                    MessagingUI.showOneChatroom(UserProfileRepository.userSignedIn.getMessaging(), user.getUsername());
                    break;
                }
                case "4":{
                    ReportUI.reportAccount(user);
                    break;
                }
                case "5":{return;}
                default:
                    System.out.println("Invalid option!");
            }
        }
    }



    private static void printProfileSection(UserProfile user) {
        ArrayList<ProfileSection> profileSections = user.getProfileSections();
        System.out.println("Skills:\n");
        for (ProfileSection section: profileSections){
            if (section instanceof Skill)
            {
                SkillUI.printSkill((Skill) section);
                if(user != UserProfileRepository.userSignedIn && UserProfileRepository.userSignedIn.getConnections().contains(user)){
                    System.out.println("""
                            1:Endorse
                            2: Skip""");
                    String option = scanner.nextLine();
                    switch (option){
                        case "1":{
                            UserService.endorse(user, (Skill) section);
                        }
                        case "2":{break;}
                    }
                }
            }
        }

        System.out.println("Experience:\n");
        for (ProfileSection section: profileSections){
            if (section instanceof Experience)
            {
                ExperienceUI.printExperience((Experience) section);
            }
        }

        System.out.println("Education:\n");
        for (ProfileSection section: profileSections){
            if (section instanceof Education)
            {
                EducationUI.printEducation((Education) section);
            }
        }
    }

    private static void feed() {
        UserProfile user = UserProfileRepository.userSignedIn;
        ArrayList<Post> feed = user.getFeed();

        int postCount = 0;
        while (postCount < feed.size()) {
            for (int i = 0; i < 10 && postCount < feed.size(); i++) {
                PostUI.printPost(feed.get(postCount));
                PostUI.viewPostOption(feed.get(postCount));
                postCount++;
            }

            if (postCount < feed.size()) {
                System.out.print("Would you like to see more posts? (yes/no): ");
                String response = scanner.nextLine();
                if (!response.equalsIgnoreCase("yes")) {
                    break;
                }
            }
        }
    }


    public static void viewAllPost(UserProfile profile) {
        for(Post post: profile.getPostShared()){
            PostUI.printPost(post);
            PostUI.viewPostOption(post);
            System.out.println("""
                    1: Next
                    2: Exit""");

            String option = scanner.nextLine();
            switch (option){
                case "1":{
                    break;
                }
                case "2":{
                    return;
                }

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
