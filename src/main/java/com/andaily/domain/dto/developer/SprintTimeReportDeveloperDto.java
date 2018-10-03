package com.andaily.domain.dto.developer;

import com.andaily.domain.dto.AbstractDTO;
import com.andaily.domain.dto.user.DeveloperDto;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.Developer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class SprintTimeReportDeveloperDto extends AbstractDTO {

    private DeveloperDto developerDto;
    private List<SprintTimeReportDeveloperDayDto> shellTimes = new ArrayList<>();


    public SprintTimeReportDeveloperDto() {
    }

    public SprintTimeReportDeveloperDto(Developer developer) {
        super(developer.guid());
        this.developerDto = new DeveloperDto(developer);
    }

    public String getTotalEstimateTimes() {
        BigDecimal total = BigDecimal.ZERO;
        for (SprintTimeReportDeveloperDayDto shellTime : shellTimes) {
            total = total.add(shellTime.getEstimateTime());
        }
        return total.toString();
    }

    public boolean isCurrentDeveloper() {
        return (developerDto != null
                && developerDto.getGuid().equals(SecurityUtils.currentUserGuid()));
    }

    public DeveloperDto getDeveloperDto() {
        return developerDto;
    }

    public void setDeveloperDto(DeveloperDto developerDto) {
        this.developerDto = developerDto;
    }

    public List<SprintTimeReportDeveloperDayDto> getShellTimes() {
        return shellTimes;
    }

    public void setShellTimes(List<SprintTimeReportDeveloperDayDto> shellTimes) {
        this.shellTimes = shellTimes;
    }

    public void addShellTime(BigDecimal estimate, BigDecimal actual) {
        final SprintTimeReportDeveloperDayDto reportDeveloperDayDto = new SprintTimeReportDeveloperDayDto()
                .setActualUsedTime(actual)
                .setEstimateTime(estimate);
        this.shellTimes.add(reportDeveloperDayDto);
    }
}