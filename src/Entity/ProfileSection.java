package Entity;

public abstract class ProfileSection {
    private static Long id = 0L;
    private String title;

    // Constructor
    public ProfileSection(String title) {
        this.title = title;
        this.id = id++;
    }

    public Long getId() {
        return ProfileSection.id;
    }

    public void setId(Long id) {
        ProfileSection.id = id;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Abstract method for additional details
    //public abstract String getDetails();
}

