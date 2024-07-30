package Entity;

public class ConnectionRequest {
    private UserProfile request;
    private Status status = Status.PENDING;

    public ConnectionRequest(UserProfile request) {
        this.request = request;
    }

    public UserProfile getRequest() {
        return request;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
