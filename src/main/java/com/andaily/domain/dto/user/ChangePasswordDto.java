package com.andaily.domain.dto.user;

import com.andaily.domain.dto.AbstractDTO;

/**
 * @author Shengzhao Li
 */
public class ChangePasswordDto extends AbstractDTO {


    private String oldPassword;

    private String newPassword;
    private String rePassword;

    public ChangePasswordDto() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}