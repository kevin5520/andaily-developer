package com.andaily.domain.developer.loader;

import com.andaily.domain.developer.Backlog;
import com.andaily.domain.developer.BacklogRepository;
import com.andaily.domain.developer.project.ProjectRepository;
import com.andaily.domain.dto.developer.BacklogFormDto;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.ScrumTerm;
import com.andaily.infrastructure.mybatis.data.SimpleProjectData;
import com.andaily.web.context.BeanProvider;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 13-11-5
 *
 * @author Shengzhao Li
 */
public class BacklogFormDtoLoader {

    private transient BacklogRepository backlogRepository = BeanProvider.getBean(BacklogRepository.class);
    private transient ProjectRepository projectRepository = BeanProvider.getBean(ProjectRepository.class);

    private final String guid;
    private final String projectGuid;

    public BacklogFormDtoLoader(String guid, String projectGuid) {
        this.guid = guid;
        this.projectGuid = projectGuid;
    }

    public BacklogFormDto load() {
        BacklogFormDto backlogFormDto = new BacklogFormDto(guid, projectGuid);
        if (!backlogFormDto.isNewly()) {
            Backlog backlog = backlogRepository.findByGuid(guid);
            backlogFormDto = new BacklogFormDto(backlog);
        }
        loadAvailableProjects(backlogFormDto);
        return backlogFormDto;
    }

    private void loadAvailableProjects(BacklogFormDto backlogFormDto) {
        if (StringUtils.isEmpty(backlogFormDto.getProjectGuid())) {
            Map<String, Object> map = new HashMap<>();
            map.put("currUser", SecurityUtils.currUser());
            // Different role will call different limit
            final ScrumTerm scrumTerm = SecurityUtils.currUser().scrumTerm();
            map.put("isProductOwner", scrumTerm.equals(ScrumTerm.PRODUCT_OWNER) ? "yes" : null);
            map.put("isTeamMember", (scrumTerm.isMaster() || scrumTerm.isMember()) ? "yes" : null);

            List<SimpleProjectData> projectDatas = projectRepository.findAvailableSimpleProjects(map);
            backlogFormDto.setAvailableProjects(projectDatas);
        } else {
            SimpleProjectData projectData = projectRepository.findSimpleProject(backlogFormDto.getProjectGuid());
            backlogFormDto.setAvailableProjects(Arrays.asList(projectData));
        }
    }
}
