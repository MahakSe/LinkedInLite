package UI;

import Entity.*;
import Repository.GroupRepository;
import Service.GroupService;
import Service.OrganizationService;
import Service.PostService;
import Service.UserService;

import java.util.ArrayList;
import java.util.Scanner;

public class SearchUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void search() {
        System.out.println("Enter the string you want to search:");
        String searchQuery = scanner.nextLine();

        System.out.println("""
                Search in
                1: People
                2: Posts
                3: Companies
                4: Groups
                5: Schools
                6: All
                7: Exit""");
        String option = scanner.nextLine();

        switch (option) {
            case "1": {
                searchPeople(searchQuery);
                break;
            }
            case "2": {
                searchPosts(searchQuery);
                break;
            }
            case "3": {
                searchCompanies(searchQuery);
                break;
            }
            case "4": {
                searchGroups(searchQuery);
                break;
            }
            case "5": {
                searchSchools(searchQuery);
                break;
            }
            case "6":{
                searchAll(searchQuery);
                break;
            }
            case "7": {
                return;
            }
            default: {
                System.out.println("Invalid option!");
            }
        }
    }

    private static void searchAll(String searchQuery) {
        /*ArrayList<OrganizationProfile> schools = OrganizationService.searchSchools(searchQuery);
        ArrayList<Group> groups = GroupService.searchGroup(searchQuery);
        ArrayList<OrganizationProfile> companies = OrganizationService.searchCompanies(searchQuery);
        ArrayList<Post> posts = PostService.searchPost(searchQuery);
        ArrayList<UserProfile> userProfiles = UserService.searchUserProfile(searchQuery);*/

        System.out.println("-------- People --------");
        searchPeople(searchQuery);
        System.out.println("-------- Schools --------");
        searchSchools(searchQuery);
        System.out.println("-------- Companies --------");
        searchCompanies(searchQuery);
        System.out.println("-------- Groups --------");
        searchGroups(searchQuery);
        System.out.println("-------- Posts --------");
        searchPosts(searchQuery);
    }

    /*private static <T> void displayResults(String category, ArrayList<T> results) {
        System.out.println("\n--- " + category + " ---");
        if(category.equals("People")){
            for (int i = 0; i < Math.min(3, results.size()); i++) {
                UserProfile user = (UserProfile) results.get(i);
                System.out.println(user.getUsername());
                System.out.println("""
                        1: See profile
                        2: """);
                UserUI.printProfile();
            }
        }
        else if (category.equals("Posts")) {
            for (int i = 0; i < Math.min(3, results.size()); i++) {
                Post post = (Post)results.get(i);
                PostUI.printPost(post);
                PostUI.viewPostOption(post);
            }
        }
        else if (category.equals("Companies")) {
            for (int i = 0; i < Math.min(3, results.size()); i++) {
                OrganizationProfile company = (OrganizationProfile) results.get(i);
                System.out.println(company.getUsername());
                System.out.println("1: Open profile\n2: Next\n3: Exit");
                String option = scanner.nextLine();
                switch (option){
                    case "1":{
                        UserUI.printProfile(company);
                        break;
                    }
                    case "2":{
                        break;
                    }
                    case "3":{
                        return;
                    }
                    default:
                        System.out.println("Invalid option!");
                }
            }

        }
        else if (category.equals("Schools")) {
            for (int i = 0; i < Math.min(3, results.size()); i++) {
                OrganizationProfile school = (OrganizationProfile) results.get(i);
                System.out.println(school.getUsername());
                System.out.println("1: Open profile\n2: Next\n3: Exit");
                String option = scanner.nextLine();
                switch (option){
                    case "1":{
                        UserUI.printProfile(school);
                        break;
                    }
                    case "2":{
                        break;
                    }
                    case "3":{
                        return;
                    }
                    default:
                        System.out.println("Invalid option!");
                }

            }

        }
        else if (category.equals("Groups")) {
            for (int i = 0; i < Math.min(3, results.size()); i++) {
                Group group = (Group) results.get(i);
                System.out.println(group.getName());
                System.out.println("1: Open group\n2: Next\n3: Exit");
                String option = scanner.nextLine();
                switch (option){
                    case "1":{
                        GroupUI.showGroup(group);
                        break;
                    }
                    case "2":{
                        break;
                    }
                    case "3":{
                        return;
                    }
                    default:
                        System.out.println("Invalid option!");
                }
            }

        }
    }

    private static <T> void askForMoreResults(String category, ArrayList<T> results) {

    }*/

    private static void searchSchools(String searchQuery) {
        ArrayList<OrganizationProfile> schools = OrganizationService.searchSchools(searchQuery);
        if(schools.isEmpty()){
            System.out.println("No results for " + searchQuery);
            return;
        }
        for (OrganizationProfile School: schools){
            {
                System.out.println(School.getUsername());
                System.out.println("""
                    1: Next
                    2: View page
                    3: Exit""");
                String option = scanner.nextLine();
                switch (option){
                    case "1":{
                        break;
                    }
                    case "2": {
                        OrganizationUI.printPage(School);
                        break;
                    }
                    case "3":
                        return;
                    default:
                        System.out.println("Invalid option!");
                }
            }
        }

    }

    private static void searchGroups(String searchQuery) {
        ArrayList<Group> groups = GroupService.searchGroup(searchQuery);
        if(groups.isEmpty()){
            System.out.println("No results for " + searchQuery);
            return;
        }
        for (Group group: groups){
            System.out.println(group.getName());
            System.out.println("""
                    1: Next
                    2: View group
                    3: Exit""");
            String option = scanner.nextLine();
            switch (option){
                case "1":{
                    break;
                }
                case "2":{
                    GroupUI.showGroup(group);
                    break;
                }
                case "3":{
                    return;
                }
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void searchCompanies(String searchQuery) {
        ArrayList<OrganizationProfile> companies = OrganizationService.searchCompanies(searchQuery);
        if(companies.isEmpty()){
            System.out.println("No results for " + searchQuery);
            return;
        }
        for (OrganizationProfile company: companies){
            {
                System.out.println(company.getUsername());
                System.out.println("""
                    1: Next
                    2: View page
                    3: Exit""");
                String option = scanner.nextLine();
                switch (option){
                    case "1":{
                        break;
                    }
                    case "2": {
                        OrganizationUI.printPage(company);
                        break;
                    }
                    case "3":
                        return;
                    default:
                        System.out.println("Invalid option!");
                }
            }
        }
    }

    private static void searchPosts(String searchQuery) {
        ArrayList<Post> posts = PostService.searchPost(searchQuery);
        if(posts.isEmpty()){
            System.out.println("No results for " + searchQuery);
            return;
        }
        for (Post post: posts){
            PostUI.printPost(post);
            System.out.println("""
                    1: Next
                    2: View post options
                    3: Exit""");
            String option = scanner.nextLine();
            switch (option){
                case "1":{
                    break;
                }
                case "2": {
                    PostUI.viewPostOption(post);
                    break;
                }
                case "3":
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void searchPeople(String searchQuery) {
        ArrayList<UserProfile> userProfiles = UserService.searchUserProfile(searchQuery);
        if(userProfiles.isEmpty()){
            System.out.println("No results for " + searchQuery);
            return;
        }
        for (UserProfile userProfile: userProfiles){
            System.out.println(userProfile.getUsername());
            System.out.println("""
                    1: Next
                    2: View profile
                    3: Exit""");
            String option = scanner.nextLine();
            switch (option){
                case "1":{
                    break;
                }
                case "2": {
                    UserUI.printProfile(userProfile);
                    break;
                }
                case "3":
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
