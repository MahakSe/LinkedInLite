package Entity;

import java.util.ArrayList;
import java.util.Date;

public class Comment {
    public static Boolean isDisable = Boolean.FALSE;
    private String text;
    private UserProfile commenter;
    private Date date;
    private ArrayList<Reaction> reactions;
    private ArrayList<Comment> replies;
    private Object post; //messaging

    public Comment(String text, UserProfile commenter, Post post) {
        this.post = post;
        this.text = text;
        this.commenter = commenter;
        this.date = new Date();
        this.reactions = new ArrayList<>();
        this.replies = new ArrayList<>();
    }

    public Object getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getText() {
        return text;
    }

    public UserProfile getCommenter() {
        return commenter;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<Comment> getReplies() {
        return replies;
    }

}
