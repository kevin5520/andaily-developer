package com.andaily.domain.developer;

import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.Language;
import com.andaily.domain.user.User;

/**
 * Date: 13-8-3
 *
 * @author Shengzhao Li
 */
public enum SprintTaskStatus {

    CREATED("Created", "已创建"),
    PENDING("Pending", "进行中"),
    FINISHED("Finished", "已完成"),
    CANCELED("Canceled", "已取消");

    private String label;
    private String cnLabel;

    SprintTaskStatus(String label, String cnLabel) {
        this.label = label;
        this.cnLabel = cnLabel;
    }

    public String getValue() {
        return name();
    }

    public String getLabel() {
        final User user = SecurityUtils.currUser();
        if (user != null && user instanceof Developer) {
            final Language language = user.language();
            return language.isChinese() ? cnLabel : label;
        }
        return label;
    }

    public boolean isCanceled() {
        return CANCELED.equals(this);
    }

    public boolean isCreated() {
        return CREATED.equals(this);
    }

    public boolean isFinished() {
        return FINISHED.equals(this);
    }

    public boolean isPending() {
        return PENDING.equals(this);
    }

}
