package com.andaily.domain;

import com.andaily.domain.shared.DateUtils;
import com.andaily.domain.shared.GuidGenerator;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Shengzhao Li
 */
public abstract class AbstractDomain implements Serializable {

    /**
     * Database id
     */
    protected int id;

    protected boolean archived;
    /**
     * Domain business guid.
     */
    protected String guid = GuidGenerator.generate();

    /**
     * The domain create time.
     */
    protected Date createTime = DateUtils.now();

    public AbstractDomain() {
    }

    public int id() {
        return id;
    }

    public void id(int id) {
        this.id = id;
    }

    public boolean archived() {
        return archived;
    }

    public AbstractDomain archived(boolean archived) {
        this.archived = archived;
        return this;
    }

    public String guid() {
        return guid;
    }

    public void guid(String guid) {
        this.guid = guid;
    }

    public Date createTime() {
        return createTime;
    }

    public boolean isNewly() {
        return id == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractDomain)) {
            return false;
        }
        AbstractDomain that = (AbstractDomain) o;
        return guid.equals(that.guid);
    }

    @Override
    public int hashCode() {
        return guid.hashCode();
    }

    //For subclass override it
    public void saveOrUpdate() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("id", id).
                append("archived", archived).
                append("guid", guid).
                append("createTime", createTime).
                toString();
    }
}
