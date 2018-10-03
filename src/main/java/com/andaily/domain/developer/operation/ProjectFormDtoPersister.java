package com.andaily.domain.developer.operation;

import com.andaily.domain.developer.project.Project;
import com.andaily.domain.developer.project.ProjectProductOwner;
import com.andaily.domain.developer.project.ProjectRepository;
import com.andaily.domain.dto.developer.project.ProjectFormDto;
import com.andaily.domain.shared.DateUtils;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.DeveloperRepository;
import com.andaily.infrastructure.support.LogHelper;
import com.andaily.web.context.BeanProvider;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Date: 13-10-22
 *
 * @author Shengzhao Li
 */
public class ProjectFormDtoPersister {

    private static LogHelper log = LogHelper.create(ProjectFormDtoPersister.class);

    private transient ProjectRepository projectRepository = BeanProvider.getBean(ProjectRepository.class);
    private transient DeveloperRepository developerRepository = BeanProvider.getBean(DeveloperRepository.class);

    private ProjectFormDto projectFormDto;

    public ProjectFormDtoPersister(ProjectFormDto projectFormDto) {
        this.projectFormDto = projectFormDto;
    }

    public void persist() {
        if (projectFormDto.isNewly()) {
            createProject();
        } else {
            updateProject();
        }
    }

    private void updateProject() {
        Project existProject = projectRepository.findByGuid(projectFormDto.getGuid());
        Date finishDate = finishDate();
        existProject.finishDate(finishDate)
                .name(projectFormDto.getName())
                .code(projectFormDto.getCode())
                .description(projectFormDto.getDescription());

        updateProductOwners(existProject);
        existProject.saveOrUpdate();
        log.debug(SecurityUtils.currUser().displayName() + " update the project [" + existProject.guid() + "]");
    }

    private void createProject() {
        Date finishDate = finishDate();
        Project project = projectFormDto.toDomain().finishDate(finishDate);
        project.saveOrUpdate();

        updateProductOwners(projectRepository.findByGuid(project.guid()));
        log.debug(SecurityUtils.currUser().displayName() + " create a new project [" + project.guid() + "]");
    }

    private void updateProductOwners(Project project) {
        final List<ProjectProductOwner> productOwners = project.productOwners();
        if (!productOwners.isEmpty()) {
            projectRepository.deleteProjectProductOwners(productOwners);
            productOwners.clear();
        }

        List<Developer> owners = developerRepository.findDeveloperByGuids(projectFormDto.getProductOwnerGuids());
        for (Developer owner : owners) {
            ProjectProductOwner productOwner = new ProjectProductOwner(project, owner);
            productOwner.saveOrUpdate();
        }
    }

    private Date finishDate() {
        String finishDateAsText = projectFormDto.getFinishDate();
        return StringUtils.isEmpty(finishDateAsText) ? null : DateUtils.getDate(finishDateAsText);
    }
}
