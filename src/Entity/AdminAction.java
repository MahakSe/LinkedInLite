package Entity;

public class AdminAction {
    private PerformedAction performedAction;
    private String reason;
    private Object object;

    public AdminAction(PerformedAction performedAction, String reason, Object object) {
        this.performedAction = performedAction;
        this.reason = reason;
        this.object = object;
    }

}
