package com.andaily.domain.developer;

import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.User;

/**
 * Date: 13-8-5
 *
 * @author Shengzhao Li
 */
public enum BacklogType {

    NEEDS("Needs", "需求"),
    BUGS("Bug", "Bug"),
    SUGGESTIONS("Suggestions", "建议");

    private String label;
    private String cnLabel;

    private BacklogType(String label, String cnLabel) {
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
