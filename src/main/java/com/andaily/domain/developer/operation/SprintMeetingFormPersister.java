package com.andaily.domain.developer.operation;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.SprintMeeting;
import com.andaily.domain.developer.SprintRepository;
import com.andaily.domain.dto.developer.SprintMeetingFormDto;
import com.andaily.domain.log.LogHandler;
import com.andaily.domain.shared.DateUtils;
import com.andaily.web.context.BeanProvider;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Date: 13-10-1
 *
 * @author Shengzhao Li
 */
public class SprintMeetingFormPersister {

    private static Logger logger = Logger.getLogger(SprintMeetingFormPersister.class);
    private transient SprintRepository sprintRepository = BeanProvider.getBean(SprintRepository.class);
    private SprintMeetingFormDto sprintMeetingFormDto;

    public SprintMeetingFormPersister(SprintMeetingFormDto sprintMeetingFormDto) {
        this.sprintMeetingFormDto = sprintMeetingFormDto;
    }

    public String persist() {
        if (sprintMeetingFormDto.isNewly()) {
            return createMeeting();
        }
        return updateMeeting();
    }

    private String updateMeeting() {
        SprintMeeting existMeeting = sprintRepository.findMeetingByGuid(sprintMeetingFormDto.getGuid());
        existMeeting.updateContent(sprintMeetingFormDto.getContent())
                .updateMeetingDate(DateUtils.getDate(sprintMeetingFormDto.getMeetingDate()))
                .updateType(sprintMeetingFormDto.getType()).saveOrUpdate();
        logger.info("Update meeting[" + existMeeting.guid() + "] successful.");
        return existMeeting.guid();
    }

    private String createMeeting() {
        Sprint sprint = sprintRepository.findByGuid(sprintMeetingFormDto.getSprintDto().getGuid());
        Date date = DateUtils.getDate(sprintMeetingFormDto.getMeetingDate());

        SprintMeeting meeting = new SprintMeeting(date, sprintMeetingFormDto.getType(), sprintMeetingFormDto.getContent());
        meeting.updateSprint(sprint).saveOrUpdate();
        logger.info("Create meeting[" + meeting.guid() + "] successful.");
        LogHandler.createSprintActivityLog("add meeting [" + meeting.content() + "]", sprintRepository.findMeetingByGuid(meeting.guid()));
        return meeting.guid();
    }
}
