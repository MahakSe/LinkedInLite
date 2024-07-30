package Entity;

import java.util.ArrayList;

public class UserProfile {
    private String firstName;
    private String lastName;
    private String email;

    private String username;
    private String password;
    private Long id = 0L;

    private String about;
    private String headLine;
    private ArrayList<Post> feed;
    private ArrayList<Post> postShared;

    private ArrayList<UserProfile> followings;
    private ArrayList<UserProfile> followers = new ArrayList<>();


    private ArrayList<ConnectionRequest> connectionRequests;
    private ArrayList<UserProfile> connections;

    private ArrayList<Interest> interests; //groups, profiles, Schools

    private ArrayList<ProfileSection> profileSections;// skills, education, experience

    private Boolean isBanned;
    private Boolean isAllowedToPost;
    private Integer warnings;
    private Boolean isAllowedToComment;

    private ArrayList<Messaging> messaging;

    private ArrayList<Comment> comments;

    public ArrayList<Comment> getComments() {
        return comments;
    }


    public Integer getWarnings() {
        return warnings;
    }

    public void setWarnings(Integer warnings) {
        this.warnings = warnings;
    }

    public Boolean getAllowedToComment() {
        return isAllowedToComment;
    }

    public void setAllowedToComment(Boolean allowedToComment) {
        isAllowedToComment = allowedToComment;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    private ArrayList<Group> groups;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<UserProfile> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<UserProfile> followers) {
        this.followers = followers;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<UserProfile> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<UserProfile> connections) {
        this.connections = connections;
    }

    public ArrayList<Post> getFeed() {
        ArrayList<Post> feed = new ArrayList<>();
        for(UserProfile userProfile: this.connections)
            feed.addAll(userProfile.getPostShared());
        return feed;
    }

    public void setFeed(ArrayList<Post> feed) {
        this.feed = feed;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public ArrayList<Post> getPostShared() {
        return postShared;
    }

    public void setPostShared(ArrayList<Post> postShared) {
        this.postShared = postShared;
    }

    public ArrayList<UserProfile> getFollowings() {
        return followings;
    }

    public void setFollowings(ArrayList<UserProfile> followings) {
        this.followings = followings;
    }

    public ArrayList<ConnectionRequest> getConnectionRequests() {
        return connectionRequests;
    }

    public void setConnectionRequests(ArrayList<ConnectionRequest> connectionRequests) {
        this.connectionRequests = connectionRequests;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<ProfileSection> getProfileSections() {
        return profileSections;
    }

    public void setProfileSections(ArrayList<ProfileSection> profileSections) {
        this.profileSections = profileSections;
    }

    public ArrayList<Interest> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<Interest> interests) {
        this.interests = interests;
    }

    public Boolean getBanned() {
        return isBanned;
    }

    public void setBanned(Boolean banned) {
        isBanned = banned;
    }

    public Boolean getAllowedToPost() {
        return isAllowedToPost;
    }

    public void setAllowedToPost(Boolean allowedToPost) {
        isAllowedToPost = allowedToPost;
    }

    public ArrayList<Messaging> getMessaging() {
        return messaging;
    }

    public void setMessaging(ArrayList<Messaging> messaging) {
        this.messaging = messaging;
    }

    public UserProfile(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.id = id++;
        this.feed = new ArrayList<>();
        this.postShared = new ArrayList<>();
        this.followings = new ArrayList<>();
        this.connectionRequests = new ArrayList<>();
        this.connections = new ArrayList<>();
        this.interests = new ArrayList<>();
        this.profileSections = new ArrayList<>();
        this.isBanned = false;
        this.isAllowedToPost = Boolean.TRUE;
        this.messaging = new ArrayList<>();
        this.warnings = 0;
        this.isAllowedToComment = Boolean.TRUE;
        this.followers = new ArrayList<>();
    }

}
