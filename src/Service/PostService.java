package Service;

import Entity.*;
import Repository.PostRepository;
import Repository.UserProfileRepository;

import java.util.ArrayList;

public class PostService {
    public static ArrayList<Post> searchPost(String searchQuery) {
        ArrayList<Post> posts = PostRepository.posts;
        ArrayList<Post> searchResults = new ArrayList<>();

        // iterate through the posts and check if searchQuery is present in post content or author username
        for (Post post : posts) {
            if (post.getContent().toLowerCase().contains(searchQuery.toLowerCase()) ||
                    post.getAuthor().getUsername().toLowerCase().contains(searchQuery.toLowerCase())) {
                searchResults.add(post);
            }
        }

        return searchResults;
    }

    public static void addReactionToPost(Post post, ReactionType reactionType) {
        if(UserProfileRepository.userSignedIn.getAllowedToComment()) {
            Reaction reaction = new Reaction(UserProfileRepository.userSignedIn, reactionType);
            post.getReactions().add(reaction);
        }
    }

    public static void addCommentToPost(Post post, String comment) {
        if(UserProfileRepository.userSignedIn.getAllowedToComment()) {
            Comment comment1 = new Comment(comment, UserProfileRepository.userSignedIn, post);
            post.getComments().add(comment1);
            UserProfileRepository.userSignedIn.getComments().add(comment1);
        }
    }

    public static void addPost(String content) {
        Post post = new Post(content, UserProfileRepository.userSignedIn);
        UserProfileRepository.userSignedIn.getPostShared().add(post);
        PostRepository.posts.add(post); // add post to global repository
    }

    public static void deletePost(Post post) {
        UserProfile user = post.getAuthor();
        PostRepository.posts.remove(post);
        user.getPostShared().remove(post);
    }
}

