package com.andaily.service;

import com.andaily.domain.dto.user.ChangePasswordDto;
import com.andaily.domain.dto.user.MyProfileDto;
import com.andaily.domain.dto.user.UserFormDto;
import com.andaily.domain.dto.user.UserOverviewDto;

/**
 * @author Shengzhao Li
 */
public interface UserService {


    /**
     * Check the password is current logged user or not
     *
     * @param password password
     * @return True is right
     */
    boolean checkCurrentUserPassword(String password);

    boolean uniqueCellphone(String cellPhone);

    UserOverviewDto loadUserOverviewDto(UserOverviewDto userOverviewDto);

    UserFormDto loadUserFormDto(String guid, String teamGuid);

    boolean uniqueEmail(String email);

    //return  user guid
    String persistUserForm(UserFormDto userFormDto);

    //return team guid or null
    String archiveUser(String guid, boolean archive);

    //return new password
    String resetPassword(String guid);

    MyProfileDto loadMyProfileDto();

    void updateMyProfileDto(MyProfileDto profileDto);

    void changeCurrentUserPassword(ChangePasswordDto changePasswordDto);
}
