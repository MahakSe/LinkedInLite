package UI;

import Entity.*;
import Repository.ProfileSectionRepository;
import Repository.UserProfileRepository;
import Service.SkillService;

import java.util.ArrayList;
import java.util.Scanner;

public class SkillUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void printSkill(Skill skillSection){
        System.out.println("Title: "+ skillSection.getTitle()
                + "\nOrganization: " + skillSection.getSkillUsageArea()
                + "\n"
        );

    }

    public static void addSkill() {

        System.out.println("Enter the required information below");

        System.out.println("Skill:");
        String title = scanner.nextLine();
        ProfileSection usage = null;


        System.out.println("""
                    1: Add where you used the skill
                    2: Skip""");
        String option = scanner.nextLine();
        switch (option){
            case "1":{
                System.out.println("Education:");
                ArrayList<ProfileSection> profileSections = UserProfileRepository.userSignedIn.getProfileSections();
                for (ProfileSection profileSection: profileSections){
                    if(profileSection instanceof Education){
                        System.out.println(profileSection.getId() + ":" + profileSection.getTitle());
                    }
                }
                System.out.println("Experience:");
                for (ProfileSection profileSection: profileSections){
                    if(profileSection instanceof Experience){
                        System.out.println(profileSection.getId() + ": " + profileSection.getTitle());
                    }
                }

                System.out.println("\nSelect the number:");
                Long id = scanner.nextLong();
                scanner.nextLine();
                ProfileSection profileSection = ProfileSectionRepository.getProfileSectionById(id, profileSections);
                if(profileSection == null){
                    System.out.println("Wrong number!");
                }
                else{
                    usage = profileSection;
                }
                break;
            }
            case "2":{
                break;
            }
        }
        Skill skill = new Skill(title, usage);
        SkillService.addSkill(skill);
        System.out.println("Skill added successfully!");
    }
}
