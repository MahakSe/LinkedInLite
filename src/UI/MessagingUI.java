package UI;

import Entity.Message;
import Entity.Messaging;
import Entity.UserProfile;
import Repository.UserProfileRepository;
import Service.MessageService;

import java.util.ArrayList;
import java.util.Scanner;

public class MessagingUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void messaging() {
        UserProfile user = UserProfileRepository.userSignedIn;
        ArrayList<Messaging> messaging = user.getMessaging();
        showAllChatRooms(messaging);
    }

    private static void showAllChatRooms(ArrayList<Messaging> messaging) {
        for (Messaging chatRoom: messaging){
            System.out.println(chatRoom.getProfile().getUsername());
        }
        System.out.println("1: Open a chatroom\n2: Exit\n");
        String option = scanner.nextLine();
        switch (option) {
            case "1": {
                System.out.println("Enter the username:\n");
                String username = scanner.nextLine();
                showOneChatroom(messaging, username);
                break;
            }
            case "2": {
                break;
            }

            default: {
                System.out.println("Invalid option");
            }
        }
    }

    public static void showOneChatroom(ArrayList<Messaging> messaging, String username) {
        UserProfile u = UserProfileRepository.findUserByUsername(username);
        Messaging chatroom = null;

        for (Messaging m : messaging) {
            if (username.equals(m.getProfile().getUsername())) {
                chatroom = m;

                // Mark messages as seen
                for (int i = chatroom.getMessages().size() - 1; i >= 0 && !chatroom.getMessages().get(i).getSeen(); i--) {
                    if( chatroom.getMessages().get(i).getSender() != UserProfileRepository.userSignedIn)
                        chatroom.getMessages().get(i).setSeen(Boolean.TRUE);
                }

                // Show the chatroom messages
                for (Message message : chatroom.getMessages()) {
                    MessageUI.printMessage(message);
                    /*System.out.println("1: Reply\n2: Next");
                    String option = scanner.nextLine();
                    switch (option) {
                        case "1":
                            replyToMessage(message, chatroom);
                            break;
                        case "2":
                            break;
                    */
                }
            }
        }
        while (true) {
            System.out.println("1: Send message\n2: Exit");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    sendMessage(u, null, chatroom);
                    break;
                case "2":
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private static void sendMessage(UserProfile u, Message reply, Messaging chatroom) {
        System.out.println("Write your message:");
        String message = scanner.nextLine();
        MessageService.sendMessage(u, message, reply, chatroom);
    }


    private static void replyToMessage(Message message, Messaging chatroom) {
        UserProfile sender = message.getSender();
        sendMessage(sender, message, chatroom);
    }
}
