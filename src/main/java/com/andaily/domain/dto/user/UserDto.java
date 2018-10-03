package com.andaily.domain.dto.user;

import com.andaily.domain.dto.AbstractDTO;
import com.andaily.domain.dto.team.TeamDto;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.team.Team;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.Language;
import com.andaily.domain.user.ScrumTerm;
import com.andaily.domain.user.User;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class UserDto extends AbstractDTO {

    protected String nickName;
    protected String email;
    protected String cellPhone;
    protected boolean allowEdit;

    protected String description;

    //developer fields

    protected ScrumTerm scrumTerm;
    protected TeamDto teamDto;
    protected boolean developer;

    protected Language language;

    protected boolean archived;

    public UserDto() {
    }

    public UserDto(User user) {
        this(user, true);
    }

    public UserDto(User user, boolean full) {
        this.guid = user.guid();
        this.nickName = user.nickName();
        this.email = user.email();

        this.archived = user.archived();
        this.cellPhone = user.cellPhone();
        this.allowEdit = this.guid.equals(SecurityUtils.currentUserGuid());

        this.description = user.description();

        this.developer = user.isDeveloper();
        if (full && this.developer) {
            Developer tempDeveloper = (Developer) user;
            this.scrumTerm = tempDeveloper.scrumTerm();
            this.language = tempDeveloper.language();

            final Team team = tempDeveloper.team();
            if (team != null) {
                this.teamDto = new TeamDto(team);
            }
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getShowName() {
        if (StringUtils.isNotEmpty(nickName)) {
            return nickName;
        }
        return email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static List<UserDto> toDtos(List<User> userList) {
        List<UserDto> dtoList = new ArrayList<UserDto>(userList.size());
        for (User user : userList) {
            dtoList.add(new UserDto(user));
        }
        return dtoList;
    }

    public boolean isAllowEdit() {
        return allowEdit;
    }

    public ScrumTerm getScrumTerm() {
        return scrumTerm;
    }

    public void setScrumTerm(ScrumTerm scrumTerm) {
        this.scrumTerm = scrumTerm;
    }

    public TeamDto getTeamDto() {
        return teamDto;
    }

    public void setTeamDto(TeamDto teamDto) {
        this.teamDto = teamDto;
    }

    public boolean isDeveloper() {
        return developer;
    }

    public void setDeveloper(boolean developer) {
        this.developer = developer;
    }
}
