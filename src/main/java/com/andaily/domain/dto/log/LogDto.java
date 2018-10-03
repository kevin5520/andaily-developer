package com.andaily.domain.dto.log;

import com.andaily.domain.dto.AbstractDTO;
import com.andaily.domain.log.Log;
import com.andaily.domain.log.SprintActivityLog;
import com.andaily.domain.shared.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class LogDto extends AbstractDTO {


    protected String createTime;
    protected String content;
    protected String displayContent;

    public LogDto() {
    }

    public LogDto(Log log) {
        super(log.guid());
        this.createTime = DateUtils.toDateTime(log.createTime());
        this.content = log.content();
        this.displayContent = log.displayContent();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDisplayContent() {
        return displayContent;
    }

    public void setDisplayContent(String displayContent) {
        this.displayContent = displayContent;
    }

    public static List<LogDto> toSprintActivityDtos(List<SprintActivityLog> logs) {
        List<LogDto> dtos = new ArrayList<>(logs.size());
        for (SprintActivityLog log : logs) {
            dtos.add(new LogDto(log));
        }
        return dtos;
    }
}