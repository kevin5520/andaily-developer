package com.andaily.domain.dto;

import org.apache.commons.lang.StringUtils;

/**
 * @author Shengzhao Li
 */
public abstract class AbstractDTO {

    protected String guid;

    protected AbstractDTO() {
    }

    protected AbstractDTO(String guid) {
        this.guid = guid;
    }

    public boolean isNewly() {
        return StringUtils.isEmpty(guid);
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}
