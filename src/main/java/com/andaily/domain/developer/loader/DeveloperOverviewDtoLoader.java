package com.andaily.domain.developer.loader;

import com.andaily.domain.developer.Sprint;
import com.andaily.domain.developer.SprintMeeting;
import com.andaily.domain.developer.SprintTask;
import com.andaily.domain.developer.SprintTaskStatus;
import com.andaily.domain.developer.burndown.BurnDown;
import com.andaily.domain.developer.burndown.BurnDownGenerator;
import com.andaily.domain.dto.developer.*;
import com.andaily.domain.shared.paginated.PaginatedLoader;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Date: 13-8-8
 *
 * @author Shengzhao Li
 */
public class DeveloperOverviewDtoLoader extends AvailableSprintsLoader {

    private DeveloperOverviewDto overviewDto;

    public DeveloperOverviewDtoLoader(DeveloperOverviewDto overviewDto) {
        this.overviewDto = overviewDto;
    }

    public DeveloperOverviewDto load() {
        final List<SprintSimpleDto> sprintSimpleDtos = loadAvailableSprints();
        overviewDto.setSprintDtos(sprintSimpleDtos);

        loadCurrentSprint();
        loadTasks();

        loadTotalTasks();
        loadBurnDown();
        loadLatestMeetings();
        return overviewDto;
    }

    private void loadLatestMeetings() {
        if (!hasCurrentSprintDto()) {
            return;
        }
        String sprintGuid = overviewDto.getCurrentSprint().getGuid();
        List<SprintMeeting> meetings = sprintRepository.findLatestMeetings(sprintGuid, 5);
        overviewDto.setLatestMeetings(SprintMeetingDto.toDtos(meetings));
    }

    private void loadBurnDown() {
        if (!hasCurrentSprintDto()) {
            return;
        }
        String sprintGuid = overviewDto.getCurrentSprint().getGuid();
        Sprint sprint = sprintRepository.findByGuid(sprintGuid);

        BurnDownGenerator burnDownGenerator = new BurnDownGenerator(sprint);
        BurnDown burnDown = burnDownGenerator.generate();
        overviewDto.setBurnDownWrapper(new BurnDownChartWrapper(burnDown));
    }

    private void loadTotalTasks() {
        if (!hasCurrentSprintDto()) {
            return;
        }
        String sprintGuid = overviewDto.getCurrentSprint().getGuid();
        overviewDto.setTotalCreatedTasks(sprintRepository.totalTasksBySprintAndStatus(sprintGuid, SprintTaskStatus.CREATED));
        overviewDto.setTotalPendingTasks(sprintRepository.totalTasksBySprintAndStatus(sprintGuid, SprintTaskStatus.PENDING));
        overviewDto.setTotalFinishedTasks(sprintRepository.totalTasksBySprintAndStatus(sprintGuid, SprintTaskStatus.FINISHED));
        overviewDto.setTotalCanceledTasks(sprintRepository.totalTasksBySprintAndStatus(sprintGuid, SprintTaskStatus.CANCELED));
    }

    private void loadTasks() {
        final Map<String, Object> map = overviewDto.queryParams();
        overviewDto = overviewDto.load(new PaginatedLoader<SprintTaskDto>() {
            @Override
            public List<SprintTaskDto> loadList() {
                List<SprintTask> tasks = sprintRepository.findSprintTasks(map);
                return SprintTaskDto.toDtos(tasks);
            }

            @Override
            public int loadTotalSize() {
                return sprintRepository.totalSprintTasks(map);
            }
        });
    }


    private void loadCurrentSprint() {
        if (hasCurrentSprintDto()) {
            String guid = overviewDto.getCurrentSprint().getGuid();
            loadAndSetCurrentSprint(guid);
        } else {
            final SprintSimpleDto defaultSprint = findDefaultSprint();
            if (defaultSprint != null) {
                loadAndSetCurrentSprint(defaultSprint.getGuid());
            }
        }
    }

    private SprintSimpleDto findDefaultSprint() {
        List<SprintSimpleDto> sprintDtos = overviewDto.getSprintDtos();
        SprintSimpleDto sprintSimpleDto = null;
        for (SprintSimpleDto sprintDto : sprintDtos) {
            if (sprintDto.isDefaultSprint()) {
                sprintSimpleDto = sprintDto;
                break;
            }
        }
        if (sprintSimpleDto == null && !sprintDtos.isEmpty()) {
            sprintSimpleDto = sprintDtos.get(0);
        }
        return sprintSimpleDto;
    }

    private boolean hasCurrentSprintDto() {
        SprintDto sprintDto = overviewDto.getCurrentSprint();
        return sprintDto != null && StringUtils.isNotEmpty(sprintDto.getGuid());
    }

    private void loadAndSetCurrentSprint(String guid) {
        Sprint currentSprint = sprintRepository.findByGuid(guid);
        overviewDto.setCurrentSprint(new SprintDto(currentSprint));
    }
}
