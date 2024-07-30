package UI;

import Entity.*;
import Repository.UserProfileRepository;
import Service.EducationService;

import java.util.Date;
import java.util.Scanner;

import static UI.ExperienceUI.parseDate;

public class EducationUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void printEducation(Education education) {
        System.out.println("Title: "+ education.getTitle()
                + "\nField: " + education.getFiled()
                + "\nDegree: " + education.getDegree()
                + "\nGrade: " + education.getGrade()
                + "\nSchool: " + education.getOrganization()
                + "\nStart Date: " + education.getStartDate()
                + "\nEnd Date: " + education.getEndDate()
        );
    }

    public static void addEducation() {
        System.out.println("Enter the required information below");

        System.out.println("School:");
        String school = scanner.nextLine();
        OrganizationProfile organizationProfile = UserProfileRepository.getOrganizationByUsername(school);
        if (organizationProfile == null){
            System.out.println("Invalid name!");
            return;
        }

        System.out.println("Degree:\n" +
                "1. ASSOCIATE\n" +
                "2. BACHELOR,\n" +
                "3. MASTER,\n" +
                "4. DOCTORATE,\n" +
                "5. PROFESSIONAL,\n" +
                "6. DIPLOMA,\n" +
                "7. HIGH_SCHOOL,\n" +
                "8. OTHER");

        int input = scanner.nextInt();
        scanner.nextLine();

        Degree degree;
        switch (input) {
            case 1:
                degree = Degree.ASSOCIATE;
                break;
            case 2:
                degree = Degree.BACHELOR;
                break;
            case 3:
                degree = Degree.MASTER;
                break;
            case 4:
                degree = Degree.DOCTORATE;
                break;
            case 5:
                degree = Degree.PROFESSIONAL;
                break;
            case 6:
                degree = Degree.DIPLOMA;
                break;
            case 7:
                degree = Degree.HIGH_SCHOOL;
                break;
            case 8:
                degree = Degree.OTHER;
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }


        System.out.println("Filed of study:");
        String filed = scanner.nextLine();

        System.out.println("Grade:");
        String grade = scanner.nextLine();

        System.out.println("Start Date (yyyy-MM-dd):");
        Date startDate = parseDate(scanner.nextLine());

        System.out.println("End Date (yyyy-MM-dd, or press enter if current):");
        String endDateInput = scanner.nextLine();
        Date endDate = endDateInput.isEmpty() ? null : parseDate(endDateInput);

        // Create the Experience object
        Education education = new Education(school, organizationProfile, filed, degree, startDate, endDate, grade);

        EducationService.addEducation(education);

        System.out.println("Education added successfully!");
    }
}
