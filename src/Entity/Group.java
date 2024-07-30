package Entity;

import java.util.ArrayList;
import java.util.Date;

public class Group {
    private String name;
    private UserProfile owner;
    private Date creationDate;
    private ArrayList<UserProfile> admins;
    private ArrayList<UserProfile> members;
    private Boolean isPublic;
    private ArrayList<Post> posts;

    public Group(String name, UserProfile owner, Boolean isPublic) {
        this.name = name;
        this.owner = owner;
        this.isPublic = isPublic;
        this.creationDate = new Date();
        this.admins = new ArrayList<>();
        this.members = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public UserProfile getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Date getCreationDate() {
        return creationDate;
    }


    public ArrayList<UserProfile> getAdmins() {
        return admins;
    }

    public ArrayList<UserProfile> getMembers() {
        return members;
    }
}
