package com.andaily.domain.dto.user;

import com.andaily.domain.dto.SimpleDto;
import com.andaily.domain.shared.paginated.DefaultPaginated;
import com.andaily.domain.user.ScrumTerm;
import com.andaily.infrastructure.mybatis.data.ScrumTermData;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Date: 13-11-24
 *
 * @author Shengzhao Li
 */
public class UserOverviewDto extends DefaultPaginated<UserDto> {

    private String email;
    private ScrumTerm term;
    private String teamGuid;
    private boolean archived;

    private List<SimpleDto> teams = new ArrayList<SimpleDto>();
    private List<ScrumTermData> termDataList = new ArrayList<ScrumTermData>();

    public UserOverviewDto() {
    }

    public ScrumTerm[] getTerms() {
        return ScrumTerm.values();
    }

    @Override
    public Map<String, Object> defaultQueryMap() {
        final Map<String, Object> map = super.defaultQueryMap();
        map.put("email", StringUtils.isEmpty(email) ? null : "%" + email + "%");
        map.put("teamGuid", StringUtils.isEmpty(teamGuid) ? null : teamGuid);
        map.put("term", term);
        map.put("archived", archived);
        return map;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ScrumTerm getTerm() {
        return term;
    }

    public void setTerm(ScrumTerm term) {
        this.term = term;
    }

    public List<ScrumTermData> getTermDataList() {
        return termDataList;
    }

    public void setTermDataList(List<ScrumTermData> termDataList) {
        this.termDataList = termDataList;
    }

    public String getTeamGuid() {
        return teamGuid;
    }

    public void setTeamGuid(String teamGuid) {
        this.teamGuid = teamGuid;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public List<SimpleDto> getTeams() {
        return teams;
    }

    public void setTeams(List<SimpleDto> teams) {
        this.teams = teams;
    }
}
