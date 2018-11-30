package com.andaily.domain.dto.team;

import com.andaily.domain.shared.paginated.DefaultPaginated;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * Date: 13-12-17
 *
 * @author Shengzhao Li
 */
public class TeamOverviewDto extends DefaultPaginated<TeamDto> {

    private String name;
    private boolean archived;
    
    private String createDate;

    public TeamOverviewDto() {
    }

    @Override
    public Map<String, Object> defaultQueryMap() {
        final Map<String, Object> map = super.defaultQueryMap();
        map.put("name", StringUtils.isEmpty(name) ? null : "%" + name + "%");
        
        
        map.put("createDate", StringUtils.isEmpty(createDate) ? null : "%" + createDate + "%");
        
        map.put("archived", archived);
        return map;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
   
    public String getcreateDate() {
        return createDate;
    }

    public void setcreateDate(String createDate) {
        this.createDate = createDate;
    }
        
}
