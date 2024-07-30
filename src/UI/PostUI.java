package UI;

import Entity.*;
import Repository.UserProfileRepository;
import Service.PostService;
import Service.UserService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class PostUI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void viewPostOption(Post post) {
        while(true) {
            System.out.println("""
                        1: View Author's Profile
                        2: Follow Author
                        3: See Reactions
                        4: See Comments
                        5: React to Post
                        6: Comment on Post
                        7: Share Post
                        8: Exit""");
            if(post.getAuthor() == UserProfileRepository.userSignedIn)
                System.out.println("9: Delete post");

            String option = scanner.nextLine();
            switch (option) {
                case "1": {
                    UserUI.printProfile(post.getAuthor());
                    break;
                }
                case "2": {
                    UserService.followUser(post.getAuthor());
                    break;
                }
                case "3": {
                    viewReactions(post);
                    break;
                }
                case "4": {
                    viewComments(post);
                    break;
                }
                case "5": {
                    reactToPost(post);
                    break;
                }
                case "6": {
                    commentOnPost(post);
                    break;
                }
                /*case "7": {
                    sharePost(post);
                    break;
                }*/
                case "8": {
                    return;
                }
                case "9":{
                    PostService.deletePost(post);
                    System.out.println("Post removed successfully!");
                    break;
                }
                default: {
                    System.out.println("Invalid option!");
                }
            }
        }
    }

    private static void commentOnPost(Post post) {
        System.out.println("Enter your comment:");
        String comment = scanner.nextLine();
        PostService.addCommentToPost(post, comment);
    }

    private static void reactToPost(Post post) {
        System.out.println("Choose reaction:");
        System.out.println("""
                1: Like
                2: Celebrate
                3: Support
                4: Love
                5: Insightful
                6: Funny
                7: Exit""");
        String option = scanner.nextLine();
        ReactionType reactionType;

        switch (option) {
            case "1":
                reactionType = ReactionType.LIKE;
                break;
            case "2":
                reactionType = ReactionType.CELEBRATE;
                break;
            case "3":
                reactionType = ReactionType.SUPPORT;
                break;
            case "4":
                reactionType = ReactionType.LOVE;
                break;
            case "5":
                reactionType = ReactionType.INSIGHTFUL;
                break;
            case "6":
                reactionType = ReactionType.FUNNY;
                break;
            case "7":
                return;
            default:
                System.out.println("Invalid option!");
                return;
        }

        PostService.addReactionToPost(post, reactionType);
        System.out.println("You reacted with " + reactionType.name().toLowerCase() + ".");
    }

    private static void viewComments(Post post) {
        for(Comment comment: post.getComments()){
            System.out.println(comment.getCommenter().getUsername() + ": " + comment.getText());

            if (!comment.getReplies().isEmpty()){
                System.out.println("----Replies:");
                for (Comment comment1: comment.getReplies()){
                    System.out.println(comment1.getCommenter().getUsername() + ": " + comment1.getText());
                }
                System.out.println("-----------");
            }
            if (post.getAuthor() == UserProfileRepository.userSignedIn){
                System.out.println("0: Delete comment\n2: Next\n3: Exit");
                String option = scanner.nextLine();
                switch (option) {
                    case "1": {
                        UserService.deleteComment(post, comment);
                    }
                    case "2":{break;}
                    case "3":{return;}
                }
            }
        }

    }

    private static void viewReactions(Post post) {
        for(Reaction reaction: post.getReactions()){
            System.out.println(reaction.getUser().getUsername() + ": " + reaction.getReactionType());
        }
    }

    public static void printPost(Post post) {
        System.out.println(post.getAuthor().getUsername() + "," + post.getDate() + "\n" + post.getContent());
    }

    public static void printGroupPost(Post post) {
        System.out.println(post.getAuthor() + ", " + post.getDate() + "\n" + post.getContent());
        System.out.println("""
                1: View replies
                2: View reactions
                3: Exit""");
        String option = scanner.nextLine();
        switch (option){
            case "1":{
                ArrayList<Comment> replies = post.getComments();
                replies.sort(new Comparator<Comment>() {
                    @Override
                    public int compare(Comment o1, Comment o2) {
                        // Compare dates in descending order
                        return o2.getDate().compareTo(o1.getDate());
                    }
                });
                for(Comment reply: replies){
                    System.out.println(reply.getCommenter() + ", " + reply.getDate() + ": " + reply.getText());

                }
                break;
            }

            case "2":{
                ArrayList<Reaction> reactions = post.getReactions();
                for(Reaction reaction: reactions){
                    System.out.println(reaction.getUser().getUsername() + ": " + reaction.getReactionType());
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

    public static void addPost() {
        System.out.println("Enter post content:");
        String content = scanner.nextLine();
        PostService.addPost(content);
        System.out.println("Post added successfully!");

    }
}
