package com.andaily.domain.dto.user;

import com.andaily.domain.dto.AbstractDTO;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.Language;
import com.andaily.domain.user.ScrumTerm;
import com.andaily.domain.user.User;

/**
 * @author Shengzhao Li
 */
public class MyProfileDto extends AbstractDTO {

    private ScrumTerm term;

    private String email;
    private String existEmail;


    private String cellPhone;
    private String nickName;
    private Language language = Language.ENGLISH;


    public MyProfileDto() {
    }

    public MyProfileDto(User user) {
        this.email = existEmail = user.email();
        this.cellPhone = user.cellPhone();
        this.nickName = user.nickName();

        if (user instanceof Developer) {
            Developer developer = (Developer) user;
            this.term = developer.scrumTerm();
            this.language = developer.language();
        }
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public ScrumTerm getTerm() {
        return term;
    }

    public void setTerm(ScrumTerm term) {
        this.term = term;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExistEmail() {
        return existEmail;
    }

    public void setExistEmail(String existEmail) {
        this.existEmail = existEmail;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public User updateUser(User user) {
        user.email(email)
                .cellPhone(cellPhone)
                .nickName(nickName);
        if (user instanceof Developer) {
            ((Developer) user).language(this.language);
        }
        return user;
    }
}