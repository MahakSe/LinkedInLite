package Service;

import Entity.Message;
import Entity.Messaging;
import Entity.UserProfile;
import Repository.UserProfileRepository;

import java.util.ArrayList;

public class MessageService {
    public static void sendMessage(UserProfile u, String message, Message reply, Messaging chatroom) {
        ArrayList<UserProfile> r = new ArrayList<>();
        r.add(u);
        Message message1 = new Message(message, UserProfileRepository.userSignedIn, r);
        if(chatroom == null)
        {
            ArrayList<Message> messages = new ArrayList<>();
            messages.add(message1);
            Messaging messaging = new Messaging(messages, u, UserProfileRepository.userSignedIn);
            UserProfileRepository.userSignedIn.getMessaging().add(messaging);
            u.getMessaging().add(messaging);
        }
        else{chatroom.getMessages().add(message1);}

        if(reply != null){
            reply.getReplies().add(message1);
        }
    }
}
