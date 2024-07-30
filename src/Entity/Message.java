package Entity;

import java.util.ArrayList;
import java.util.Date;

public class Message {
    private String text;
    private ArrayList<Message> replies;
    private UserProfile sender;
    private ArrayList<UserProfile> receiver;
    private Date sentDate;
    private Boolean seen;


    public Message(String text, UserProfile sender, ArrayList<UserProfile> receiver) {
        this.text = text;
        this.sender = sender;
        this.receiver = receiver;
        this.sentDate = new Date();
        this.seen = Boolean.FALSE;
        this.replies = new ArrayList<>();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<Message> getReplies() {
        return replies;
    }

    public void setReplies(ArrayList<Message> replies) {
        this.replies = replies;
    }

    public UserProfile getSender() {
        return sender;
    }

    public void setSender(UserProfile sender) {
        this.sender = sender;
    }

    public ArrayList<UserProfile> getReceiver() {
        return receiver;
    }

    public void setReceiver(ArrayList<UserProfile> receiver) {
        this.receiver = receiver;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }
}
