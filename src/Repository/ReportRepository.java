package Repository;

import Entity.Admin;
import Entity.Report;
import Entity.ReportReason;
import Entity.UserProfile;

import java.util.ArrayList;

public class ReportRepository {
    public static ArrayList<Report> reports = new ArrayList<>(); // post, profile, skills


    public static void generateReports() {
        // Create some dummy UserProfile instances
        UserProfile user1 = UserProfileRepository.profiles.get(7);
        UserProfile user2 = UserProfileRepository.profiles.get(8);
        UserProfile user3 = UserProfileRepository.profiles.get(9);

        // Create some dummy Admin instances
        Admin admin1 = (Admin) UserProfileRepository.profiles.get(0);
        Admin admin2 = (Admin) UserProfileRepository.profiles.get(1);

        // Create some Report instances with hardcoded data
        Report report1 = new Report(user1, user2, ReportReason.BEING_FALSE);
        Report report2 = new Report(user2, user3, ReportReason.OTHER);
        Report report3 = new Report(user3, user1, ReportReason.HACKED_ACCOUNT);

        // Add the reports to the reports list
        reports.add(report1);
        reports.add(report2);
        reports.add(report3);
    }

}
