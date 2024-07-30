package Repository;

import Entity.Admin;
import Entity.OrganizationProfile;
import Entity.UserProfile;

import java.util.ArrayList;

public class UserProfileRepository {
    public static ArrayList<UserProfile> profiles = new ArrayList<>();
    public static UserProfile userSignedIn;

    public static void generateUserProfiles() {
        //generate admin and normal users
        Admin admin = new Admin("Mahak", "S", "john.doe@example.com", "ms", "admin");
        admin.setAdminLevel(2);
        profiles.add(admin);
        profiles.add(new Admin("mam", "sy", "john.doe@example.com", "mam", "123"));
        profiles.add(new UserProfile("John", "Doe", "john.doe@example.com", "johndoe", "password1"));
        profiles.add(new UserProfile("Jane", "Doe", "jane.doe@example.com", "janedoe", "password2"));
        profiles.add(new UserProfile("Jim", "Beam", "jim.beam@example.com", "jimbeam", "password3"));
        profiles.add(new UserProfile("Jack", "Daniels", "jack.daniels@example.com", "jackdaniels", "password4"));
        profiles.add(new UserProfile("Johnny", "Walker", "johnny.walker@example.com", "johnnywalker", "password5"));
        profiles.add(new UserProfile("James", "Bond", "james.bond@example.com", "jamesbond", "password6"));
        profiles.add(new UserProfile("Bruce", "Wayne", "bruce.wayne@example.com", "brucewayne", "password7"));
        profiles.add(new UserProfile("Clark", "Kent", "clark.kent@example.com", "clarkkent", "password8"));
        profiles.add(new UserProfile("Tony", "Stark", "tony.stark@example.com", "tonystark", "password9"));
        profiles.add(new UserProfile("Peter", "Parker", "peter.parker@example.com", "peterparker", "password10"));

        //generate organization profiles
        UserProfile person2 = new UserProfile("Jane", "Doe", "jane.doe@example.com", "janedoe2", "password2");
        UserProfile person3 = new UserProfile("Jim", "Beam", "jim.beam@example.com", "jimbeam2", "password3");
        OrganizationProfile org = new OrganizationProfile("John2", "Doe", "john.doe@example.com", "johndoe5", "password1");
        ArrayList<UserProfile> emp1 = new ArrayList<>();
        emp1.add(person2);
        emp1.add(person3);
        org.setEmployees(emp1);
        profiles.add(org);
        profiles.add(new OrganizationProfile("Jane2", "Doe", "jane.doe@example.com", "janedoe", "password2"));
        profiles.add(new OrganizationProfile("Jim2", "Beam", "jim.beam@example.com", "jimbeam", "password3"));
        profiles.add(new OrganizationProfile("Jack2", "Daniels", "jack.daniels@example.com", "jackdan4iels", "password4"));
        profiles.add(new OrganizationProfile("Johnny", "Walker", "johnny.walker@example.com", "johnnywalker", "password5"));
        profiles.add(new OrganizationProfile("James", "Bond", "james.bond@example.com", "jamesbond6", "password6"));
    }

    public static OrganizationProfile getOrganizationByUsername(String orgUserName) {
        for (UserProfile org : profiles) {
            if (org instanceof OrganizationProfile && org.getUsername().equals(orgUserName)) {
                return (OrganizationProfile) org;
            }
        }
        return null;
    }

    public static UserProfile findUserByUsername(String username){
        for(UserProfile user: profiles){
            if(user.getUsername().equals(username)){return user;}
        }
        return null;
    }


}
