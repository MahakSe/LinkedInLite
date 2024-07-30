package UI;

import Entity.Experience;
import Entity.ExperienceType;
import Entity.OrganizationProfile;
import Repository.UserProfileRepository;
import Service.ExperienceService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ExperienceUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void printExperience(Experience experience) {
        System.out.println("Title: " + experience.getTitle()
                + "\nExperience Type: " + experience.getExperienceType()
                + "\nDescription: " + experience.getDescription()
                + "\nOrganization: " + experience.getOrganization().getUsername()
                + "\nStart Date: " + experience.getStartDate()
                + "\nEnd Date: " + (experience.getEndDate() == null ? "Currently Working!" : experience.getEndDate())
        );

    }

    public static void addExperience() {
        System.out.println("Enter the required information below");

        System.out.println("Title:");
        String title = scanner.nextLine();

        System.out.println("Enter Employment Type (1. FULL_TIME, 2. PART_TIME, 3. INTERNSHIP, 4. FREELANCE):");

        int input = scanner.nextInt();
        scanner.nextLine();
        ExperienceType experienceType;
        switch (input) {
            case 1:
                experienceType = ExperienceType.FULL_TIME;
                break;
            case 2:
                experienceType = ExperienceType.PART_TIME;
                break;
            case 3:
                experienceType = ExperienceType.INTERNSHIP;
                break;
            case 4:
                experienceType = ExperienceType.FREELANCE;
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("Company Name:");
        String orgUserName = scanner.nextLine();
        OrganizationProfile organizationProfile = UserProfileRepository.getOrganizationByUsername(orgUserName);
        if (organizationProfile == null){
            System.out.println("Invalid name!");
            return;
        }
        System.out.println("Address:");
        String address = scanner.nextLine();



        System.out.println("Start Date (yyyy-MM-dd):");
        Date startDate = parseDate(scanner.nextLine());

        System.out.println("End Date (yyyy-MM-dd, or press enter if current):");
        String endDateInput = scanner.nextLine();
        Date endDate = endDateInput.isEmpty() ? null : parseDate(endDateInput);

        System.out.println("Description:");
        String description = scanner.nextLine();

        // Create the Experience object
        Experience experience = new Experience(title, organizationProfile, startDate, endDate, experienceType, description, address);

        ExperienceService.addExperience(experience);

        System.out.println("Experience added successfully!");
    }

    public static Date parseDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return null;
        }
    }
}
