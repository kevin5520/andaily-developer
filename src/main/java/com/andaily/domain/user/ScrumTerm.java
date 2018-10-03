package com.andaily.domain.user;

import com.andaily.domain.shared.security.SecurityUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Date: 13-9-22
 *
 * @author Shengzhao Li
 */
public enum ScrumTerm {

    SUPER_MAN("Super Man", "超级用户"),
    PRODUCT_OWNER("Product Owner", "产品负责人"),
    MASTER("Scrum Master", "Scrum Master"),
    MEMBER("Scrum Member", "Scrum Member");

    private String label;
    private String cnLabel;

    private ScrumTerm(String label, String cnLabel) {
        this.label = label;
        this.cnLabel = cnLabel;
    }

    public static List<ScrumTerm> scrumTerms() {
        return Arrays.asList(PRODUCT_OWNER, MASTER, MEMBER);
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

    public boolean isMaster() {
        return MASTER.equals(this);
    }

    public boolean isMember() {
        return MEMBER.equals(this);
    }

    public boolean isProductOwner() {
        return PRODUCT_OWNER.equals(this);
    }
}
