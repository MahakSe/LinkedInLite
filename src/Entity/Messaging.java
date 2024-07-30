package Entity;

import Repository.UserProfileRepository;

import java.util.ArrayList;

public class Messaging {
    private ArrayList<Message> messages;
    private UserProfile profile1;
    private UserProfile profile2;

    private MessagingStatus messagingStatus;

    public Messaging(ArrayList<Message> messages, UserProfile profile1, UserProfile profile2) {
        this.messages = messages;
        this.profile1 = profile1;
        this.profile2 = profile2;
        this.messagingStatus = MessagingStatus.UNREAD;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public UserProfile getProfile() {
        if(UserProfileRepository.userSignedIn == profile1){return profile2;}
        return profile1;
    }


    public MessagingStatus getMessagingStatus() {
        return messagingStatus;
    }

    public void setMessagingStatus(MessagingStatus messagingStatus) {
        this.messagingStatus = messagingStatus;
    }
}
