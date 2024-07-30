package UI;

import Entity.OrganizationProfile;
import Entity.UserProfile;

import java.util.Scanner;

public class OrganizationUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void printPage(OrganizationProfile org) {
        System.out.println("\n------------------");
        System.out.println("Username: " + org.getUsername());
        if (!org.getAbout().isEmpty())
            System.out.println("About: " + org.getAbout());
        while(true) {
            System.out.println("""
                    1: View posts
                    2: View associated members
                    3: Exit""");
            String option = scanner.nextLine();
            switch (option) {
                case "1": {
                    UserUI.viewAllPost(org);
                    break;
                }
                case "2": {
                    viewMembers(org);
                    break;
                }
                case "3": {
                    return;
                }
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    public static void viewMembers(OrganizationProfile company) {
        for(UserProfile userProfile: company.getEmployees()){
            System.out.println(userProfile.getUsername());
        }
    }
}
