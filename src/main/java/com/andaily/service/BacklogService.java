package com.andaily.service;

import com.andaily.domain.dto.developer.BacklogDownloadDocumentsDto;
import com.andaily.domain.dto.developer.BacklogFormDto;
import com.andaily.domain.dto.developer.BacklogOverviewDto;

/**
 * Date: 13-9-7
 *
 * @author Shengzhao Li
 */
public interface BacklogService {

    BacklogFormDto loadBacklogFormDto(String guid, String projectGuid);

    String persistBacklogFormDto(BacklogFormDto backlogFormDto);

    BacklogOverviewDto loadBacklogOverviewDto(BacklogOverviewDto backlogOverviewDto);

    //return the  backlog project guid
    String archiveBacklog(String guid);

    void removeDocument(String guid);

    BacklogDownloadDocumentsDto loadBacklogDownloadDocumentsDto(String guid);
}
