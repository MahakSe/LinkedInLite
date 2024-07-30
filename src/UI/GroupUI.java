package UI;

import Entity.Group;
import Entity.Post;
import Entity.ReportReason;
import Entity.UserProfile;
import Repository.UserProfileRepository;
import Service.ReportService;
import Service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class GroupUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void viewGroup(ArrayList<Group> groups) {
        while(true) {
            System.out.println("1: View a specific group\n2: Exit\n");
            String option = scanner.nextLine();
            switch (option) {
                case "1": {
                    System.out.println("Enter the name of the group:\n");
                    String groupName = scanner.nextLine();
                    findGroup(groups, groupName);
                    break;
                }
                case "2": {
                    break;
                }
                default: {
                    System.out.println("Invalid option");
                }
            }
        }
    }

    private static void findGroup(ArrayList<Group> groups, String groupName) {
        for(Group group: groups){
            if (group.getName().equals(groupName))
            {
                showGroup(group);
                return;
            }
        }
    }

    public static void showGroup(Group group) {
        System.out.println("\n------- Group -------");
        System.out.println("Name: " + group.getName());

        if (group.getPublic() || group.getMembers().contains(UserProfileRepository.userSignedIn)){
            boolean flag = true;
            while (flag) {
                System.out.println("""
                        1: See Owner
                        2: See Admins
                        3: See members
                        4: See posts
                        5: See creation date
                        6: Report
                        7: Post content
                        8: Exit""");
                String option = scanner.nextLine();
                switch (option){
                    case "1":{
                        System.out.println(group.getOwner().getUsername());
                        break;
                    }
                    case "2":{
                        for(UserProfile admin: group.getAdmins()){
                            System.out.println(admin.getUsername());
                        }
                        break;
                    }
                    case "3":{
                        for (UserProfile member: group.getMembers()){
                            System.out.println(member.getUsername());
                        }
                        System.out.println("1: See profile of a member\n2: Exit");
                        String option2 = scanner.nextLine();
                        switch (option2){
                            case "1":{
                                System.out.println("Enter username:");
                                String username = scanner.nextLine();
                                for(UserProfile user: group.getMembers()){
                                    if(user.getUsername().equals(username)){
                                        UserUI.printProfile(user);
                                        break;
                                    }
                                }
                            }
                            case "2":{break;}
                        }
                        break;
                    }
                    case "4":{
                        ArrayList<Post> posts = getPosts(group);
                        for (Post post : posts) {
                            PostUI.printGroupPost(post);
                            System.out.println("""
                                    1: Upload previous post
                                    2: Exit group""");
                            String option3 = scanner.nextLine();
                            switch (option3) {
                                case "1": {
                                    break;
                                }
                                case "2": {
                                    return;
                                }
                                default:
                                    System.out.println("Invalid option!");
                            }
                        }
                    }
                    case "5":{
                        System.out.println(group.getCreationDate());
                        break;
                    }
                    case "6":{
                        ReportReason reportReason = ReportUI.reportReason(group);
                        ReportService.report(group, reportReason);
                        break;
                    }
                    case "7":{
                        postContentInGroup(group);
                        break;}
                    case "8":{
                        flag = false;
                        break;
                    }
                    default:
                        System.out.println("Invalid option!");
                }
            }
        }
        if (!group.getMembers().contains(UserProfileRepository.userSignedIn))
        {
            System.out.println("""
                    1: Join
                    2: Exit""");
            String option = scanner.nextLine();
            switch (option){
                case "1":{
                    UserService.joinGroup(group);
                    System.out.println("Success! You are now a member of the group.");
                    break;
                }
                case "2":{
                    return;
                }
                default:
                    System.out.println("Invalid option!");
            }
        }
        System.out.println("----------------");
    }

    public static void postContentInGroup(Group group) {
        UserProfile userProfile = UserProfileRepository.userSignedIn;
        System.out.println("Write the content:");
        String content = scanner.nextLine();
        Post post = new Post(content, userProfile);
        group.getPosts().add(post);
    }

    private static ArrayList<Post> getPosts(Group group) {
        ArrayList<Post> posts = group.getPosts();
        if(!group.getPosts().isEmpty()){
            // Sort posts based on date (most recent first)
            posts.sort(new Comparator<Post>() {
                @Override
                public int compare(Post o1, Post o2) {
                    return o2.getDate().compareTo(o1.getDate());
                }
            });

        }
        return posts;
    }
}
