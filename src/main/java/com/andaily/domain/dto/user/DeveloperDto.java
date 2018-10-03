package com.andaily.domain.dto.user;

import com.andaily.domain.user.Developer;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 13-12-18
 *
 * @author Shengzhao Li
 */
public class DeveloperDto extends UserDto {


    public DeveloperDto() {
        super();
    }

    public DeveloperDto(Developer developer) {
        super(developer, false);
        this.scrumTerm = developer.scrumTerm();
    }

    public static List<DeveloperDto> toDeveloperDtos(List<Developer> userList) {
        List<DeveloperDto> dtoList = new ArrayList<DeveloperDto>(userList.size());
        for (Developer d : userList) {
            dtoList.add(new DeveloperDto(d));
        }
        return dtoList;
    }
}
