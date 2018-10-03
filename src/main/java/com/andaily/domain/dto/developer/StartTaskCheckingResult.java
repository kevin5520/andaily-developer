package com.andaily.domain.dto.developer;

import com.andaily.domain.dto.AbstractDTO;

/**
 * @author Shengzhao Li
 */
public class StartTaskCheckingResult extends AbstractDTO {

    private boolean allow;
    private String oldExecutor;
    private String newExecutor;

    public StartTaskCheckingResult() {
    }

    public boolean isAllow() {
        return allow;
    }

    public StartTaskCheckingResult setAllow(boolean allow) {
        this.allow = allow;
        return this;
    }

    public String getOldExecutor() {
        return oldExecutor;
    }

    public StartTaskCheckingResult setOldExecutor(String oldExecutor) {
        this.oldExecutor = oldExecutor;
        return this;
    }

    public String getNewExecutor() {
        return newExecutor;
    }

    public StartTaskCheckingResult setNewExecutor(String newExecutor) {
        this.newExecutor = newExecutor;
        return this;
    }
}