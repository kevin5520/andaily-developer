package com.andaily.domain.developer.operation;

import com.andaily.domain.developer.Backlog;
import com.andaily.domain.developer.BacklogDocument;
import com.andaily.domain.developer.BacklogRepository;
import com.andaily.domain.developer.commons.GeckoFile;
import com.andaily.domain.developer.project.Project;
import com.andaily.domain.developer.project.ProjectRepository;
import com.andaily.domain.dto.developer.BacklogFormDto;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.infrastructure.support.LogHelper;
import com.andaily.web.context.BeanProvider;

import java.util.List;

/**
 * Date: 13-9-8
 *
 * @author Shengzhao Li
 */
public class BacklogFormPersister {

    private static LogHelper log = LogHelper.create(BacklogFormPersister.class);

    private transient BacklogRepository backlogRepository = BeanProvider.getBean(BacklogRepository.class);
    private transient ProjectRepository projectRepository = BeanProvider.getBean(ProjectRepository.class);

    private BacklogFormDto backlogFormDto;

    public BacklogFormPersister(BacklogFormDto backlogFormDto) {
        this.backlogFormDto = backlogFormDto;
    }

    public String persist() {
        if (backlogFormDto.isNewly()) {
            return createBacklog();
        } else {
            return updateBacklog();
        }
    }

    private String updateBacklog() {
        Backlog backlog = backlogRepository.findByGuid(backlogFormDto.getGuid());
        updateValues(backlog);
        updateDocuments(backlog);

        backlog.saveOrUpdate();
        log.debug(SecurityUtils.currUser().displayName() + " update the backlog [" + backlog.guid() + "]");
        return backlog.guid();
    }

    private String createBacklog() {
        Backlog backlog = new Backlog().updateCreator(SecurityUtils.currUser());
        //generate unique number
        backlog.number(backlogRepository.maxBacklogNumber() + 1);

        updateValues(backlog);
        backlog.saveOrUpdate();
        updateDocuments(backlogRepository.findByGuid(backlog.guid()));

        log.debug(SecurityUtils.currUser().displayName() + " create the backlog [" + backlog.guid() + "]");
        return backlog.guid();
    }

    private void updateDocuments(Backlog backlog) {
        List<GeckoFile> fileList = backlogFormDto.retrieveFiles();
        for (GeckoFile file : fileList) {
            BacklogDocument backlogDocument = new BacklogDocument(backlog, file)
                    .creator(SecurityUtils.currUser());
            backlogDocument.saveOrUpdate();
        }
    }

    private void updateValues(Backlog backlog) {
        backlog.updateContent(backlogFormDto.getContent())
                .updateType(backlogFormDto.getType())
                .updateEstimateTime(backlogFormDto.getEstimateTime())
                .updatePriority(backlogFormDto.getPriority());

        Project project = projectRepository.findByGuid(backlogFormDto.getProjectGuid());
        backlog.updateProject(project);
    }
}
