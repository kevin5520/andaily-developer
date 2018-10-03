package com.andaily.domain.dto.user;

import com.andaily.domain.dto.SimpleDisabledDto;
import com.andaily.domain.shared.PasswordHandler;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.ScrumTerm;
import com.andaily.domain.user.User;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 13-12-6
 *
 * @author Shengzhao Li
 */
public class UserFormDto extends UserDto {

    private String teamGuid;
    private List<SimpleDisabledDto> teams = new ArrayList<SimpleDisabledDto>();
    private String existEmail;
    private String existCellPhone;

    private String password;
    private String rePassword;

    private boolean addNext;

    public UserFormDto() {
    }

    public UserFormDto(String guid, String teamGuid) {
        this.guid = guid;
        this.teamGuid = teamGuid;
    }

    public UserFormDto(User user) {
        super(user);
        this.existEmail = user.email();
        this.existCellPhone = user.cellPhone();

        if (user.isDeveloper()) {
            Developer developer1 = (Developer) user;
            this.teamGuid = developer1.team() != null ? developer1.team().guid() : null;
        }
    }

    public ScrumTerm[] getTerms() {
        return ScrumTerm.values();
    }

    @Override
    public boolean isNewly() {
        return super.isNewly() || "create".equalsIgnoreCase(guid);
    }

    public String getTeamGuid() {
        return teamGuid;
    }

    public void setTeamGuid(String teamGuid) {
        this.teamGuid = teamGuid;
    }

    public List<SimpleDisabledDto> getTeams() {
        return teams;
    }

    public void setTeams(List<SimpleDisabledDto> teams) {
        this.teams = teams;
    }

    public String getExistEmail() {
        return existEmail;
    }

    public void setExistEmail(String existEmail) {
        this.existEmail = existEmail;
    }

    public boolean isAddNext() {
        return addNext;
    }

    public void setAddNext(boolean addNext) {
        this.addNext = addNext;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public Developer toDomain() {
        String pass = PasswordHandler.encryptPassword(this.password);
        return new Developer(email, pass, nickName, StringUtils.isEmpty(cellPhone) ? null : cellPhone, scrumTerm)
                .description(description);
    }

    public String getExistCellPhone() {
        return existCellPhone;
    }

    public void setExistCellPhone(String existCellPhone) {
        this.existCellPhone = existCellPhone;
    }
}
