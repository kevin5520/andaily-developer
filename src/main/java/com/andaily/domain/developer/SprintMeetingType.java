package com.andaily.domain.developer;

import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.Language;
import com.andaily.domain.user.User;

/**
 * Date: 13-9-22
 *
 * @author Shengzhao Li
 */
public enum SprintMeetingType {

    DAILY_STANDING("Daily standing", "站立会议"),
    SPRINT_PLANNING("Sprint planning", "计划会议"),
    SPRINT_REVIEW("Sprint review", "评审会议"),
    RETROSPECTIVE("Retrospective", "回顾会议");

    private String label;
    private String cnLabel;

    private SprintMeetingType(String label, String cnLabel) {
        this.label = label;
        this.cnLabel = cnLabel;
    }

    public String getLabel() {
        final User user = SecurityUtils.currUser();
        if (user != null && user instanceof Developer) {
            final Language language = user.language();
            return language.isChinese() ? cnLabel : label;
        }
        return label;
    }

    public String getValue() {
        return name();
    }
}
