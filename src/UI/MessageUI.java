package UI;

import Entity.Message;

public class MessageUI {
    public static void printMessage(Message message) {
        System.out.println("\n" + message.getText()
                + "\nSender: " + message.getSender()
                + "\nSending Time" + message.getSentDate()
                + "\nSeen: " + message.getSeen()
                + "\n"
                );
    }
}
