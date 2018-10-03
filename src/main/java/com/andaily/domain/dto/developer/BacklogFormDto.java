package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.Backlog;
import com.andaily.domain.developer.BacklogType;
import com.andaily.domain.developer.SprintPriority;
import com.andaily.domain.developer.commons.GeckoFile;
import com.andaily.infrastructure.mybatis.data.SimpleProjectData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 13-9-5
 *
 * @author Shengzhao Li
 */
public class BacklogFormDto extends BacklogDto {

    private boolean addNext;
    private String projectGuid;

    private List<SimpleProjectData> availableProjects = new ArrayList<>();

    private MultipartFile file1;
    private MultipartFile file2;
    private MultipartFile file3;

    public BacklogFormDto() {
    }

    public BacklogFormDto(Backlog backlog) {
        super(backlog);
        this.projectGuid = backlog.project().guid();
    }

    public BacklogFormDto(String guid, String projectGuid) {
        this.guid = guid;
        this.projectGuid = projectGuid;
    }

    @Override
    public boolean isNewly() {
        return super.isNewly() || "create".equalsIgnoreCase(guid);
    }

    public boolean isAddNext() {
        return addNext;
    }

    public void setAddNext(boolean addNext) {
        this.addNext = addNext;
    }

    public BacklogType[] getAvailableTypes() {
        return BacklogType.values();
    }

    public SprintPriority[] getAvailablePriorities() {
        return SprintPriority.values();
    }

    public String getProjectGuid() {
        return projectGuid;
    }

    public void setProjectGuid(String projectGuid) {
        this.projectGuid = projectGuid;
    }

    public List<SimpleProjectData> getAvailableProjects() {
        return availableProjects;
    }

    public void setAvailableProjects(List<SimpleProjectData> availableProjects) {
        this.availableProjects = availableProjects;
    }

    public MultipartFile getFile1() {
        return file1;
    }

    public void setFile1(MultipartFile file1) {
        this.file1 = file1;
    }

    public MultipartFile getFile2() {
        return file2;
    }

    public void setFile2(MultipartFile file2) {
        this.file2 = file2;
    }

    public MultipartFile getFile3() {
        return file3;
    }

    public void setFile3(MultipartFile file3) {
        this.file3 = file3;
    }

    private GeckoFile newFile(MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }
        try {
            return new GeckoFile(multipartFile.getOriginalFilename(), multipartFile.getBytes());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<GeckoFile> retrieveFiles() {
        List<GeckoFile> list = new ArrayList<>();
        addNewFile(list, file1);
        addNewFile(list, file2);
        addNewFile(list, file3);
        return list;
    }

    private void addNewFile(List<GeckoFile> list, MultipartFile file1) {
        GeckoFile file = newFile(file1);
        if (file != null) {
            list.add(file);
        }
    }
}
