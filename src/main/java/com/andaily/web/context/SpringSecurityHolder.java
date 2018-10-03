package com.andaily.web.context;

import com.andaily.domain.dto.user.AndailyUserDetails;
import com.andaily.domain.shared.security.SecurityHolder;
import com.andaily.domain.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Shengzhao Li
 */
public class SpringSecurityHolder implements SecurityHolder {

    public AndailyUserDetails currentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof AndailyUserDetails) {
            return (AndailyUserDetails) principal;
        }
        return null;
    }


    @Override
    public User currUser() {
        AndailyUserDetails userDetails = currentUserDetails();
        return (userDetails != null ? userDetails.getUser() : null);
    }
}
