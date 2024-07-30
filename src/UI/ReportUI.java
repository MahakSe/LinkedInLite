package UI;

import Entity.*;
import Repository.ReportRepository;
import Repository.UserProfileRepository;
import Service.AdminService;
import Service.ReportService;

import java.util.ArrayList;
import java.util.Scanner;

public class ReportUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void reportAccount(Object account) {
        System.out.println("""
                1: Report entire account
                2: Report profile element
                3: Exit""");
        String option = scanner.nextLine();
        switch (option){
            case "1":{
                ReportReason reportReason = reportReason(account);
                if (reportReason != null){
                    ReportService.report(account, reportReason);
                }
                else{
                    System.out.println("Report failed!");
                    return;
                }
                break;
            }
            case "2":{
                ReportReason reportReason = reportProfileElement();
                if (reportReason != null){
                    ReportService.report(account, reportReason);
                }
                else{
                    System.out.println("Report failed!");
                    return;
                }
                break;
            }
            case "3":{
                return;
            }
            default:
                System.out.println("Invalid option!");
        }

    }

    public static ReportReason reportProfileElement() {
        ReportReason reportReason = null;
        System.out.println("Select an option that applies:");
        System.out.println("""
                1: Harassment\t2: Fraud or scam\t3: Spam\t4: Misinformation\t5: Hateful speech
                6: THREATS_OR_VIOLENCE\t7: SELF_HArm\t8: SEXUAL_CONTENT\t9: Fake account\t10: INFRINGEMENT
                11: Exit""");
        String option = scanner.nextLine();
        switch (option) {
            case "1": {
                reportReason = ReportReason.HARASSMENT;
                break;
            }
            case "2": {
                reportReason = ReportReason.SCAM;
                break;
            }
            case "3": {
                reportReason = ReportReason.SPAM;
                break;
            }
            case "4": {
                reportReason = ReportReason.MISINFORMATION;
                break;
            }
            case "5": {
                reportReason = ReportReason.HATEFUL_SPEECH;
                break;
            }
            case "6": {
                reportReason = ReportReason.THREATS_OR_VIOLENCE;
                break;
            }
            case "7": {
                reportReason = ReportReason.SELF_HARM;
                break;
            }
            case "8": {
                reportReason = ReportReason.SEXUAL_CONTENT;
                break;
            }
            case "9": {
                reportReason = ReportReason.FAKE_ACCOUNT;
                break;
            }
            case "10": {
                reportReason = ReportReason.INFRINGEMENT;
                break;
            }
            case "11" :{
                return null;
            }
            default:
                System.out.println("Invalid option!");
        }
        return reportReason;
    }

    public static ReportReason reportReason(Object o) {
        ReportReason reportReason = null;

        if (o instanceof Group || o instanceof Post || o instanceof OrganizationProfile) {
            reportReason = reportProfileElement();
        }
        else if(o instanceof UserProfile) {
            System.out.println("Select an option that applies:");
            System.out.println("""
                    1: This person is impersonating someone
                    2: This account has been hacked
                    3: This account is not a real person
                    4: Exit""");
            String option = scanner.nextLine();
            switch (option) {
                case "1": {
                    reportReason = ReportReason.STOLEN_IDENTITY;
                    break;
                }
                case "2": {
                    reportReason = ReportReason.HACKED_ACCOUNT;
                    break;
                }
                case "3": {
                    reportReason = ReportReason.NOT_BEING_A_REAL_PERSON;
                    break;
                }
                case "4":
                    return null;
                default:
                    System.out.println("Invalid option!");
            }
        }
        return reportReason;
    }

    public static void showReports() {
        UserProfile user = UserProfileRepository.userSignedIn;
        Admin admin = (Admin) user;
        ArrayList<Report> reports = ReportRepository.reports;

        for(Report report:reports){
            printReport(report);
            reportActions(report);
            
            boolean flag = true;
            while(flag) {
                System.out.println("""
                    1: Next
                    2: Exit""");
                String option = scanner.nextLine();
                
                switch (option) {
                    case "1": {
                        flag = false;
                        break;
                    }
                    case "2": {
                        return;
                    }
                    default:
                        System.out.println("Invalid option");
                }
            }
        }


    }

    private static void reportActions(Report report) {
        while (true) {
            System.out.println("""
                    1: Mark report as false.
                    2: Restrict the user from posting
                    3: Restrict the user from commenting
                    4: Send a warning
                    """);
            if (report.getReported() instanceof Post) {
                System.out.println("5: remove post");
            } else if (report.getReported() instanceof Comment) {
                System.out.println("""
                        6: Ban user
                        7: Remove Comment
                        """);
            }

            System.out.println("0: Exit");
            String option = scanner.nextLine();
            String actionReason = "";
            if(!option.equals("0")) {
                actionReason = actionReason();
            }
            switch (option){
                case "0":{return;}
                case "1":{
                    ReportService.markFalse(report, actionReason);
                    break;
                }
                case "2":{
                    UserProfile user = AdminService.findUser(report);
                    if (user == null)
                        System.out.println("No user found!");

                    else {
                        report.setReportStatus(Status.ACCEPTED);
                        AdminService.restrictPosting(user, actionReason);
                    }
                    break;
                }
                case "3":{
                    UserProfile user = AdminService.findUser(report);
                    if (user == null)
                        System.out.println("No user found!");

                    else {
                        report.setReportStatus(Status.ACCEPTED);
                        AdminService.restrictCommenting(user, actionReason);
                    }
                    break;
                }
                case "4":{
                    UserProfile user = AdminService.findUser(report);
                    if (user == null)
                        System.out.println("No user found!");

                    else {
                        report.setReportStatus(Status.ACCEPTED);
                        AdminService.sendWarning(user, actionReason);
                    }
                    break;
                }
                case "5":{
                    report.setReportStatus(Status.ACCEPTED);
                    AdminService.removePost(report, actionReason);
                    break;
                }
                case "6":{
                    UserProfile user = AdminService.findUser(report);
                    if(user != null) {
                        report.setReportStatus(Status.ACCEPTED);
                        AdminService.banUser(user, actionReason);
                    }
                    else{
                        System.out.println("No user found! ");
                    }
                    break;
                }
                case "7":{
                    report.setReportStatus(Status.ACCEPTED);
                    AdminService.removeComment(report, actionReason);
                    break;
                }
                default:
                    System.out.println("Invalid option!");
            }
        }
    }


    public static String actionReason() {
        System.out.println("Provide a report for your action:");
        return scanner.nextLine();
    }

    private static void printReport(Report report) {
        if (report.getReportStatus().equals(Status.PENDING)){
            System.out.println("Reported: " + report.getReported());
            System.out.println("Reporter: " + report.getReporter().getUsername());
            System.out.println("Report Reason: " + report.getReportReason());
        }
    }
}
