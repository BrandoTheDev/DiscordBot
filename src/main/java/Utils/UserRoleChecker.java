package Utils;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.util.List;

public final class UserRoleChecker {

    public static boolean isUserAllowedOrOwner(Member member,List<Role> roles, String roleRequired) {
        for(Role role : roles) {
            //  user is server owner OR if user has specified "modRole"
            String requiredRole = roleRequired.toLowerCase();
            String roleName = role.getName().toLowerCase();

            if (member.isOwner() || roleName.equals(requiredRole)) {
                return true;
            }
        }
        return false;
    }

    public static boolean userHasRole(List<Role> roles, String roleRequired) {
        for(Role role : roles) {
            //  if user has specified "Role"
            String roleName = role.getName().toLowerCase();
            String requiredRole = roleRequired.toLowerCase();

            if (roleName.equals(requiredRole)) {
                return true;
            }
        }
        return false;
    }

}
