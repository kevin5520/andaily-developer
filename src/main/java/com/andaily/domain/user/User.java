package com.andaily.domain.user;

import com.andaily.domain.AbstractDomain;
import com.andaily.domain.shared.PasswordHandler;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.team.Team;
import com.andaily.infrastructure.support.LogHelper;
import com.andaily.web.context.BeanProvider;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * @author Shengzhao Li
 */
public class User extends AbstractDomain {

    private static LogHelper log = LogHelper.create(User.class);
    private transient UserRepository userRepository = BeanProvider.getBean(UserRepository.class);

    /**
     * Unique, the user login name
     */
    protected String email;

    protected String password;
    /**
     * Option field
     */
    protected String city;
    protected String nickName;
    protected String cellPhone;

    protected Date lastLoginTime;

    protected String description;

    /**
     * Use the field for identify the user if need verify.
     * For example: when register need active the user, will retrieve the user according to the field value
     */
    protected String verifyCode;

    public User() {
    }

    public User(String email, String password, String nickName) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
    }

    public Date lastLoginTime() {
        return lastLoginTime;
    }

    public String email() {
        return email;
    }

    public String username() {
        return email;
    }

    public String password() {
        return password;
    }

    public String city() {
        return city;
    }

    public String nickName() {
        return nickName;
    }

    public String cellPhone() {
        return cellPhone;
    }

    public String resetPassword() {
        String newPass = PasswordHandler.randomPassword();
        this.password = PasswordHandler.encryptPassword(newPass);
        userRepository.updatePassword(this);
        log.debug(SecurityUtils.currUser() + " change the user(id=" + id() + ") password.");
        return newPass;
    }

    public void update(String nickName, String cellPhone) {
        this.nickName = nickName;
        this.cellPhone = cellPhone;
    }

    public String verifyCode() {
        return verifyCode;
    }

    public User verifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
        return this;
    }

    public boolean isDeveloper() {
        return false;
    }

    public ScrumTerm scrumTerm() {
        throw new UnsupportedOperationException("Only [Developer] implement it");
    }

    public Team team() {
        throw new UnsupportedOperationException("Only [Developer] implement it");
    }


    public User email(String email) {
        this.email = email;
        return this;
    }

    public User cellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
        return this;
    }

    public User nickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String description() {
        return description;
    }

    @SuppressWarnings("unchecked")
    public <T extends User> T description(String description) {
        this.description = description;
        return (T) this;
    }

    public void saveOrUpdate() {
        if (isNewly()) {
            userRepository.saveUser(this);
        } else {
            userRepository.updateUser(this);
        }
    }

    public User archive(boolean archive) {
        this.archived = archive;
        this.saveOrUpdate();
        return this;
    }

    public String displayName() {
        if (StringUtils.isNotEmpty(nickName)) {
            return nickName;
        }
        return username();
    }

    public User changePassword(String newOriginalPassword) {
        this.password = PasswordHandler.encryptPassword(newOriginalPassword);
        userRepository.updatePassword(this);
        return this;
    }

    public Language language() {
        throw new UnsupportedOperationException("Only [Developer] implement it");
    }
}
