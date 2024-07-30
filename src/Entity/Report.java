package Entity;

public class Report {
    private Object reported;
    private UserProfile reporter;
    private ReportReason reportReason;

    private Status reportStatus = Status.PENDING;

    private Admin admin;
    private PerformedAction performedAction;
    private String performedActionReason;

    public PerformedAction getPerformedAction() {
        return performedAction;
    }

    public void setPerformedAction(PerformedAction performedAction) {
        this.performedAction = performedAction;
    }

    public Object getReported() {
        return reported;
    }

    public UserProfile getReporter() {
        return reporter;
    }

    public ReportReason getReportReason() {
        return reportReason;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Status getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(Status reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getPerformedActionReason() {
        return performedActionReason;
    }

    public void setPerformedActionReason(String performedActionReason) {
        this.performedActionReason = performedActionReason;
    }

    public Report(Object reported, UserProfile reporter, ReportReason reportReason) {
        this.reported = reported;
        this.reporter = reporter;
        this.reportReason = reportReason;
    }
}
