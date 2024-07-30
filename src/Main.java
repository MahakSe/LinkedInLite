import Entity.OrganizationProfile;
import Entity.Post;
import Entity.ReportReason;
import Entity.UserProfile;
import Repository.*;
import UI.UserUI;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Generate user profiles
        UserProfileRepository.generateUserProfiles();
        ReportRepository.generateReports();
        PostRepository.generatePosts();
        GroupRepository.generateGroups();

        // Show login page
        logInPage();

    }
    public static void logInPage() {

        while (true) {

            System.out.println("1: LogIn\n2: SignUp\n3: Exit");
            String option = scanner.nextLine();
            switch (option) {
                case "1": {
                    UserProfile user = UserUI.login();
                    if (user != null)
                        UserUI.loggedInPages();
                    break;
                }
                case "2": {
                    UserProfile user = UserUI.signup();
                    if (user != null)
                        UserUI.loggedInPages();
                    break;
                }
                case "3": {
                    return;
                }
                default: {
                    System.out.println("Invalid option");
                }
            }
        }

    }


}
