package com.andaily.domain.shared.security;

import com.andaily.domain.user.User;
import org.apache.commons.lang.StringUtils;

/**
 * @author Shengzhao Li
 */
public class SecurityUtils {

    private static SecurityHolder securityHolder;

    /**
     * Get current login user guid.
     *
     * @return User guid or null
     */
    public static String currentUserGuid() {
        final User user = securityHolder.currUser();
        return (user != null ? user.guid() : null);
    }

    public static User currUser() {
        return securityHolder.currUser();
    }

    public void setSecurityHolder(SecurityHolder securityHolder) {
        SecurityUtils.securityHolder = securityHolder;
    }

    /**
     * Return current is logged or not.
     *
     * @return True is login,otherwise false
     */
    public static boolean isLogged() {
        return StringUtils.isNotBlank(currentUserGuid());
    }

    /**
     * Update security user information
     *
     * @param user New user instance
     */
    public static void updateUserDetails(User user) {
        securityHolder.currentUserDetails().updateUserDetails(user);
    }
}