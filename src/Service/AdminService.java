package Service;

import Entity.*;
import Repository.PostRepository;
import Repository.UserProfileRepository;

public class AdminService {

    public static void restrictPosting(UserProfile user, String reason) {

        Admin admin = (Admin) UserProfileRepository.userSignedIn;
        user.setAllowedToPost(Boolean.FALSE);

        admin.getActions().add(new AdminAction(PerformedAction.RESTRICT_POSTING, reason ,user));
    }

    public static void restrictCommenting(UserProfile user, String actionReason) {
        Admin admin = (Admin) UserProfileRepository.userSignedIn;
        user.setAllowedToComment(Boolean.FALSE);

        admin.getActions().add(new AdminAction(PerformedAction.RESTRICT_COMMENTING, actionReason ,user));

    }

    public static void sendWarning(UserProfile user, String actionReason) {
        Admin admin = (Admin) UserProfileRepository.userSignedIn;

        user.setWarnings(user.getWarnings() + 1);
        if(user.getWarnings() >= 5){
            user.setWarnings(0);
            user.setBanned(Boolean.TRUE);
        }
        admin.getActions().add(new AdminAction(PerformedAction.SEND_WARNING, actionReason ,user));

    }

    public static void removeAdmin(Admin admin, String reason) {
        UserProfile user = new UserProfile(
                admin.getFirstName(),
                admin.getLastName(),
                admin.getEmail(),
                admin.getUsername(),
                admin.getPassword()
        );
        UserProfileRepository.profiles.remove(admin);
        UserProfileRepository.profiles.add(user);

        Admin admin2 = (Admin) (UserProfileRepository.userSignedIn);
        admin2.getActions().add(new AdminAction(PerformedAction.REMOVE_EXISTING_ADMINS, reason, user));

    }

    public static void removePost(Report report, String reason) {
        Admin admin = (Admin) (UserProfileRepository.userSignedIn);

        Post reported = (Post) report.getReported();
        UserProfile user = reported.getAuthor();

        user.getPostShared().remove(reported);

        admin.getActions().add(new AdminAction(PerformedAction.REMOVE_POST, reason, reported));
    }

    public static void removeComment(Report report, String actionReason) {
        Admin admin = (Admin) (UserProfileRepository.userSignedIn);

        Comment reported = (Comment) report.getReported();
        UserProfile user = reported.getCommenter();
        //find post
        boolean flag = true;
        Post post2 = null;
        for(Post post: PostRepository.posts){
            if(!flag)
                break;
            for(Comment comment:post.getComments()){
                if(comment.equals(reported)){
                    flag = false;
                    post2 = post;
                    break;
                }
            }
        }
        if(post2 != null) {
            post2.getComments().remove(reported);
            admin.getActions().add(new AdminAction(PerformedAction.REMOVE_POST, actionReason, reported));
        }
    }

    public static void banUser(UserProfile user, String actionReason) {
        Admin admin = (Admin) (UserProfileRepository.userSignedIn);

        user.setBanned(Boolean.TRUE);
        admin.getActions().add(new AdminAction(PerformedAction.BAN_USER, actionReason, user));
    }

    public static UserProfile findUser(Report report) {
        if(report.getReported() instanceof UserProfile)
            return ((UserProfile) report.getReported());
        if(report.getReported() instanceof Post)
            return ((Post) report.getReported()).getAuthor();
        if(report.getReported() instanceof Comment)
            return ((Comment) report.getReported()).getCommenter();
        if(report.getReported() instanceof OrganizationProfile)
            return ((UserProfile) report.getReported());
        return null;
    }
}
