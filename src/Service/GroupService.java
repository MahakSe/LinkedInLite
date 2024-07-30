package Service;

import Entity.Group;
import Repository.GroupRepository;

import java.util.ArrayList;

public class GroupService {
    public static ArrayList<Group> searchGroup(String searchQuery) {
        ArrayList<Group> groups = GroupRepository.groups;
        ArrayList<Group> results = new ArrayList<>();

        for (Group group : groups) {
            if (group.getName().toLowerCase().contains(searchQuery.toLowerCase()) ||
            group.getOwner().getUsername().toLowerCase().contains(searchQuery.toLowerCase())) {
                results.add(group);
            }
        }

        return results;
    }
}
