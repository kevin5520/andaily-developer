package com.andaily.service.impl;

import com.andaily.domain.developer.Backlog;
import com.andaily.domain.developer.BacklogDocument;
import com.andaily.domain.developer.BacklogRepository;
import com.andaily.domain.developer.commons.CommonsRepository;
import com.andaily.domain.developer.loader.BacklogFormDtoLoader;
import com.andaily.domain.developer.loader.BacklogOverviewDtoLoader;
import com.andaily.domain.developer.operation.BacklogDownloadDocumentsDtoGenerator;
import com.andaily.domain.developer.operation.BacklogFormPersister;
import com.andaily.domain.dto.developer.BacklogDownloadDocumentsDto;
import com.andaily.domain.dto.developer.BacklogFormDto;
import com.andaily.domain.dto.developer.BacklogOverviewDto;
import com.andaily.service.BacklogService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Date: 13-9-7
 *
 * @author Shengzhao Li
 */
public class BacklogServiceImpl implements BacklogService {


    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private CommonsRepository commonsRepository;

    @Override
    public BacklogFormDto loadBacklogFormDto(String guid, String projectGuid) {
        BacklogFormDtoLoader backlogFormDtoLoader = new BacklogFormDtoLoader(guid, projectGuid);
        return backlogFormDtoLoader.load();
    }

    @Override
    public String persistBacklogFormDto(BacklogFormDto backlogFormDto) {
        BacklogFormPersister backlogFormPersister = new BacklogFormPersister(backlogFormDto);
        return backlogFormPersister.persist();
    }

    @Override
    public BacklogOverviewDto loadBacklogOverviewDto(BacklogOverviewDto backlogOverviewDto) {
        BacklogOverviewDtoLoader loader = new BacklogOverviewDtoLoader(backlogOverviewDto);
        return loader.load();
    }

    @Override
    public String archiveBacklog(String guid) {
        Backlog backlog = backlogRepository.findByGuid(guid);
        backlog.archive().saveOrUpdate();
        return backlog.project().guid();
    }

    @Override
    public void removeDocument(String guid) {
        BacklogDocument document = (BacklogDocument) commonsRepository.findDocumentByGuid(guid);
        document.delete();
    }

    @Override
    public BacklogDownloadDocumentsDto loadBacklogDownloadDocumentsDto(String guid) {
        BacklogDownloadDocumentsDtoGenerator generator = new BacklogDownloadDocumentsDtoGenerator(guid);
        return generator.generate();
    }
}
