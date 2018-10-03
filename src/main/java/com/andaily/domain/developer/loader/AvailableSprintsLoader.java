package com.andaily.domain.developer.loader;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.SprintRepository;
import com.andaily.domain.dto.developer.SprintMeetingOverviewDto;
import com.andaily.domain.dto.developer.SprintSimpleDto;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.ScrumTerm;
import com.andaily.web.context.BeanProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2017/11/3
 *
 * @author Shengzhao Li
 */
public abstract class AvailableSprintsLoader {


    protected transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);


    protected List<SprintSimpleDto> loadAvailableSprints() {
        Map<String, Object> map = new HashMap<>();
        map.put("currUser", SecurityUtils.currUser());
        // Different role will call different limit
        final ScrumTerm scrumTerm = SecurityUtils.currUser().scrumTerm();
        map.put("isProductOwner", scrumTerm.equals(ScrumTerm.PRODUCT_OWNER) ? "yes" : null);
        map.put("isTeamMember", (scrumTerm.isMaster() || scrumTerm.isMember()) ? "yes" : null);

        List<Sprint> sprints = sprintRepository.findAvailableSprints(map);
        List<SprintSimpleDto> sprintDtos = new ArrayList<>();
        for (Sprint sprint : sprints) {
            final SprintSimpleDto simpleDto = new SprintSimpleDto(sprint);
            sprintDtos.add(simpleDto);
        }
        return sprintDtos;
    }
}
