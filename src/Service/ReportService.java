package Service;

import Entity.*;
import Repository.ReportRepository;
import Repository.UserProfileRepository;

public class ReportService {
    public static void report(Object account, ReportReason reportReason) {
        Report report = new Report(account, UserProfileRepository.userSignedIn, reportReason);
        ReportRepository.reports.add(report);
    }

    public static void markFalse(Report report, String actionReason) {
        Admin admin = (Admin) UserProfileRepository.userSignedIn;
        report.setReportStatus(Status.REJECTED);
        report.setAdmin(admin);
        report.setPerformedAction(PerformedAction.MARK_AS_FALSE);
        report.setPerformedActionReason(actionReason);

        admin.getActions().add(new AdminAction(PerformedAction.MARK_AS_FALSE, actionReason, report.getReported()));
    }
}
