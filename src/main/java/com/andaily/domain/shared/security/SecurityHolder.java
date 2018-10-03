package com.andaily.domain.shared.security;

import com.andaily.domain.dto.user.AndailyUserDetails;
import com.andaily.domain.user.User;

/**
 * @author Shengzhao Li
 */
public interface SecurityHolder {

    AndailyUserDetails currentUserDetails();

    User currUser();

}
