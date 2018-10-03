package com.andaily.service.impl;

import com.andaily.domain.developer.loader.UserFormDtoLoader;
import com.andaily.domain.developer.loader.UserOverviewDtoLoader;
import com.andaily.domain.developer.operation.UserFormPersister;
import com.andaily.domain.dto.user.ChangePasswordDto;
import com.andaily.domain.dto.user.MyProfileDto;
import com.andaily.domain.dto.user.UserFormDto;
import com.andaily.domain.dto.user.UserOverviewDto;
import com.andaily.domain.shared.PasswordHandler;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.User;
import com.andaily.domain.user.UserRepository;
import com.andaily.infrastructure.support.LogHelper;
import com.andaily.service.MailService;
import com.andaily.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Shengzhao Li
 */
public class UserServiceImpl implements UserService {

    private static LogHelper log = LogHelper.create(UserServiceImpl.class);

    private UserRepository userRepository;
    private MailService mailService;


    @Override
    public boolean checkCurrentUserPassword(String password) {
        log.debug("Check current user password: " + password);
        String userGuid = SecurityUtils.currentUserGuid();
        String encryptPass = PasswordHandler.encryptPassword(password);
        String myPass = userRepository.findMyPassword(userGuid);
        boolean equals = myPass.equals(encryptPass);
        log.debug("Check result: " + equals);
        return equals;
    }

    @Override
    public boolean uniqueCellphone(String cellPhone) {
        String userGuid = userRepository.findUserGuidByCellphone(cellPhone);
        return StringUtils.isEmpty(userGuid);
    }

    @Override
    public UserOverviewDto loadUserOverviewDto(UserOverviewDto userOverviewDto) {
        UserOverviewDtoLoader loader = new UserOverviewDtoLoader(userOverviewDto);
        return loader.load();
    }

    @Override
    public UserFormDto loadUserFormDto(String guid, String teamGuid) {
        UserFormDtoLoader userFormDtoLoader = new UserFormDtoLoader(guid, teamGuid);
        return userFormDtoLoader.load();
    }

    @Override
    public boolean uniqueEmail(String email) {
        final User user = userRepository.findByEmailIncludeArchived(email);
        return user == null;
    }

    @Override
    public String persistUserForm(UserFormDto userFormDto) {
        UserFormPersister persister = new UserFormPersister(userFormDto);
        return persister.persist();
    }

    @Override
    public String archiveUser(String guid, boolean archive) {
        User user = userRepository.findByGuid(guid);
        user.archive(archive);

        String teamGuid = null;
        if (user.isDeveloper()) {
            Developer developer = (Developer) user;
            teamGuid = developer.team() != null ? developer.team().guid() : null;
        }
        return teamGuid;
    }

    @Override
    public String resetPassword(String guid) {
        User user = userRepository.findByGuid(guid);
        return user.resetPassword();
    }

    @Override
    public MyProfileDto loadMyProfileDto() {
        final User user = SecurityUtils.currUser();
        return new MyProfileDto(user);
    }

    @Override
    public void updateMyProfileDto(MyProfileDto profileDto) {
        final User user = SecurityUtils.currUser();
        profileDto.updateUser(user)
                .saveOrUpdate();
        //update userdetails
        SecurityUtils.updateUserDetails(user);
    }

    @Override
    public void changeCurrentUserPassword(ChangePasswordDto changePasswordDto) {
        final User user = SecurityUtils.currUser();
        user.changePassword(changePasswordDto.getNewPassword());
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
}
