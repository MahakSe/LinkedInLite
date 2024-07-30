package Entity;

public class Reaction {
    private UserProfile user;
    private ReactionType reactionType;

    public Reaction(UserProfile user, ReactionType reactionType) {
        this.user = user;
        this.reactionType = reactionType;
    }

    // Getters and setters
    public UserProfile getUser() {
        return user;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }

    public ReactionType getReactionType() {
        return reactionType;
    }

    public void setReactionType(ReactionType reactionType) {
        this.reactionType = reactionType;
    }
}
