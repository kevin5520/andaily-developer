package com.andaily.domain.developer;

import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.User;

/**
 * Date: 13-8-4
 *
 * @author Shengzhao Li
 */
public enum SprintPriority {

    HIGH("High", "高"),
    DEFAULT("Default", "默认"),
    LOW("Low", "低");

    private String label;
    private String cnLabel;

    private SprintPriority(String label, String cnLabel) {
        this.label = label;
        this.cnLabel = cnLabel;
    }

    public String getValue() {
        return name();
    }

    public String getLabel() {
        final User user = SecurityUtils.currUser();
        if (user != null && user instanceof Developer) {
            return user.language().isChinese() ? cnLabel : label;
        }
        return label;
    }
}
