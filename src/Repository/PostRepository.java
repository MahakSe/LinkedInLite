package Repository;

import Entity.Post;
import Entity.UserProfile;
import Service.UserService;

import java.util.ArrayList;

public class PostRepository {
    public static ArrayList<Post> posts = new ArrayList<>();

    public static void generatePosts() {
        UserProfile user1 = new UserProfile("sk", "k", "kjah.com", "sk", "password1");
        UserProfile user2 = new UserProfile("melo", "Smith", "jane.melo@example.com", "melo", "password2");
        UserProfile user3 = new UserProfile("lol", "Beam", "lol.beam@example.com", "hello", "password3");

        user2.getConnections().add(user1);
        user1.getConnections().add(user2);
        UserProfileRepository.profiles.add(user1);
        UserProfileRepository.profiles.add(user2);
        UserProfileRepository.profiles.add(user3);

        ArrayList<Post> posts11 = new ArrayList<>();
        ArrayList<Post> posts12 = new ArrayList<>();
        ArrayList<Post> posts13 = new ArrayList<>();

        // Creating some posts
        Post post1 = new Post("Hello, this is my first post!", user1);
        posts11.add(post1);

        Post post2 = new Post("Just attended an amazing conference!", user2);
        posts12.add(post2);

        Post post3 = new Post("Check out this cool article on tech trends.", user3);
        posts13.add(post3);

        Post post4 = new Post("Excited to start my new job at TechCorp!", user1);
        posts11.add(post4);

        Post post5 = new Post("Does anyone have book recommendations for summer reading?", user2);
        posts12.add(post5);


        user1.setPostShared(posts11);
        user2.setPostShared(posts12);
        user3.setPostShared(posts13);


        // Adding posts to the repository
        posts.add(post1);
        posts.add(post2);
        posts.add(post3);
        posts.add(post4);
        posts.add(post5);
    }
}
