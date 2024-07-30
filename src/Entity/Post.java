package Entity;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Date;

public class Post {
    public static Boolean isDisable = Boolean.FALSE;
    private String content;
    private UserProfile author;
    private Date date;
    private ArrayList<Reaction> reactions;
    private ArrayList<Comment> comments;

    public Post(String content, UserProfile author) {
        this.content = content;
        this.author = author;
        this.date = new Date();
        this.reactions = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserProfile getAuthor() {
        return author;
    }

    public void setAuthor(UserProfile author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(ArrayList<Reaction> reactions) {
        this.reactions = reactions;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
