package UI;

import Entity.*;
import Repository.UserProfileRepository;
import Service.AdminService;
import Service.UserService;

import java.util.Objects;
import java.util.Scanner;

public class AdminUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void adminPanel() {
        Admin admin = (Admin) UserProfileRepository.userSignedIn;
        int level = admin.getAdminLevel();
        while(true){
            System.out.println("""
                    1: View reports
                    2: Restrict a user from posting
                    3: Restrict a user from commenting
                    4: Send a warning""");
            if (level == 2) {
                System.out.println("""
                        5: Change the environment configurations
                        6: Add new admins
                        7: Remove existing admins""");
            }
            System.out.println("0: Exit");
            String option = scanner.nextLine();
            String actionReason = "";

            if(!option.equals("1") && !option.equals("0"))
            {
                actionReason = ReportUI.actionReason();
            }
            switch(option) {
                case "0": {
                    return;
                }
                case "1": {
                    ReportUI.showReports();
                    break;
                }
                case "2":{
                    System.out.println("Enter the username:");
                    String userName = scanner.nextLine();
                    UserProfile user2 = null;
                    for(UserProfile user:UserProfileRepository.profiles){
                        if(user.getUsername().equals(userName)){
                            user2 = user;
                            break;
                        }
                    }
                    if(user2 == null)
                        System.out.println("Invalid username!");

                    else
                        AdminService.restrictPosting(user2, actionReason);
                    break;
                }
                case "3":{
                    System.out.println("Enter the username:");
                    String userName = scanner.nextLine();
                    UserProfile user2 = null;
                    for(UserProfile user:UserProfileRepository.profiles){
                        if(user.getUsername().equals(userName)){
                            user2 = user;
                            break;
                        }
                    }
                    if(user2 == null)
                        System.out.println("Invalid username!");

                    else
                        AdminService.restrictCommenting(user2, actionReason);
                    break;
                }
                case "4":{
                    System.out.println("Enter the username:");
                    String userName = scanner.nextLine();
                    UserProfile user2 = null;
                    for(UserProfile user:UserProfileRepository.profiles){
                        if(user.getUsername().equals(userName)){
                            user2 = user;
                            break;
                        }
                    }
                    if(user2 == null)
                        System.out.println("Invalid username!");

                    else
                        AdminService.sendWarning(user2, actionReason);
                    break;
                }
                case "5":{
                    if(level == 2)
                        changeEnvironment();

                    else
                        System.out.println("Invalid option!");
                    break;

                }
                case "6":{
                    if(level == 2)
                        addNewAdmins();

                    else
                        System.out.println("Invalid option!");
                    break;

                }
                case "7":{
                    if(level == 2)
                        removeExistingAdmins(actionReason);

                    else
                        System.out.println("Invalid option!");
                    break;
                }

            }

        }
    }

    private static void removeExistingAdmins(String reason) {
        while (true){
            System.out.println("""
                    1: Remove admin
                    2: Exit""");
            String option = scanner.nextLine();
            switch (option){
                case "1":{
                    System.out.println("Enter username:");
                    String username = scanner.nextLine();
                    UserProfile user = UserProfileRepository.findUserByUsername(username);
                    if(!(user instanceof Admin)){
                        System.out.println("Admin not found!");
                        break;
                    }
                    if(((Admin) user).getAdminLevel() == 1) {
                        AdminService.removeAdmin((Admin) user, reason);
                        System.out.println("Admin " + user.getUsername() + " has been reverted to a regular user profile.");
                    }
                    else{
                        ((Admin) user).setAdminLevel(1);
                        Admin admin2 = (Admin) (UserProfileRepository.userSignedIn);
                        admin2.getActions().add(new AdminAction(PerformedAction.REMOVE_EXISTING_ADMINS, reason, user));
                    }
                }
                case "2":{return;}
                default:
                    System.out.println("Invalid option!");
            }
        }

    }

    private static void addNewAdmins() {
        while (true){
            System.out.println("""
                    1: Add new admin
                    2: Exit""");
            String option = scanner.nextLine();
            switch (option){
                case "1":{
                    System.out.println("Enter username:");
                    String username = scanner.nextLine();
                    UserProfile user = UserProfileRepository.findUserByUsername(username);
                    if(user == null){
                        System.out.println("User not found!");
                        break;
                    }
                    System.out.println("level: 1 or 2");
                    int level = scanner.nextInt();
                    scanner.nextLine();
                    if (level == 1 || level == 2){
                        Admin admin = (Admin) user;
                        admin.setAdminLevel(level);
                        UserProfileRepository.profiles.remove(user);
                        UserProfileRepository.profiles.add(admin);
                        System.out.println("User " + user.getUsername() + " has been successfully promoted to admin level " + level + ".");                    }
                    else{
                        System.out.println("Invalid admin level!");
                    }
                    break;
                }
                case "2":{return;}
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void changeEnvironment() {
        System.out.println("""
                1: Disable comments
                2: Disable posts
                3: Enable comments
                4: Enable posts
                5: Exit""");
        String option = scanner.nextLine();
        switch (option){
            case "1":{
                Comment.isDisable = Boolean.TRUE;
                break;
            }
            case "2":{
                Post.isDisable = Boolean.TRUE;
                break;
            }
            case "3":{
                Comment.isDisable = Boolean.FALSE;
                break;
            }
            case "4":{
                Post.isDisable = Boolean.FALSE;
                break;
            }
            case "5":{return;}
            default:
                System.out.println("Invalid option!");
        }
    }
}
