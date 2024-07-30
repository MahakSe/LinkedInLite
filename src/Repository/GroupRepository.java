package Repository;

import Entity.Group;
import Entity.UserProfile;

import java.util.ArrayList;

public class GroupRepository {
    public static ArrayList<Group> groups = new ArrayList<>();

    public static void generateGroups() {
        // Create some UserProfile instances to be used as owners and admins
        UserProfile owner1 = new UserProfile("Joh", "Doe", "joh.doe@example.com", "joh", "password1");
        UserProfile owner2 = new UserProfile("Ja", "Doe", "ja.doe@example.com", "ja", "password2");
        UserProfile admin1 = new UserProfile("Al", "Smith", "al.smith@example.com", "Al", "password3");
        UserProfile admin2 = new UserProfile("B", "Johnson", "bob.j@example.com", "b", "password4");

        UserProfileRepository.profiles.add(owner1);
        UserProfileRepository.profiles.add(owner2);
        UserProfileRepository.profiles.add(admin1);
        UserProfileRepository.profiles.add(admin2);


        // Create some groups
        Group group1 = new Group("Tech Enthusiasts", owner1, true);
        group1.getAdmins().add(admin1);
        group1.getMembers().add(admin1);
        group1.getMembers().add(admin2);

        Group group2 = new Group("Music Lovers", owner2, false);
        group2.getAdmins().add(admin2);
        group2.getMembers().add(admin1);
        group2.getMembers().add(admin2);

        Group group3 = new Group("Book Club", owner1, true);
        group3.getAdmins().add(admin1);
        group3.getMembers().add(admin1);
        group3.getMembers().add(admin2);

        // Add groups to the repository
        groups.add(group1);
        groups.add(group2);
        groups.add(group3);
    }
}
