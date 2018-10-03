package com.andaily.domain.developer.commons;

import com.andaily.domain.AbstractDomain;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.User;
import com.andaily.infrastructure.support.LogHelper;
import com.andaily.web.context.BeanProvider;

/**
 * @author Shengzhao Li
 */
public class Document extends AbstractDomain {

    protected static LogHelper log = LogHelper.create(Document.class);
    protected transient CommonsRepository commonsRepository = BeanProvider.getBean(CommonsRepository.class);

    protected User creator;
    protected GeckoFile file;

    public Document() {
    }

    public Document(GeckoFile file) {
        this.file = file;
    }

    public User creator() {
        return creator;
    }

    @SuppressWarnings("unchecked")
    public <T extends Document> T creator(User creator) {
        this.creator = creator;
        return (T) this;
    }

    public GeckoFile file() {
        return file;
    }

    @SuppressWarnings("unchecked")
    public <T extends Document> T file(GeckoFile file) {
        this.file = file;
        return (T) this;
    }


    /**
     * Delete document physically
     */
    public void delete() {
        file.delete();
        commonsRepository.deleteDocument(this);
        log.debug(SecurityUtils.currUser().displayName() + " delete Document[guid=" + this.guid + ",name=" + this.file.name() + "]");
    }
}