package com.andaily.domain.log;

import com.andaily.domain.AbstractDomain;
import com.andaily.domain.user.Developer;
import com.andaily.web.context.BeanProvider;

/**
 * Date: 13-11-11
 * <p/>
 *
 * @author Shengzhao Li
 */
public class Log extends AbstractDomain {


    protected transient LogRepository logRepository = BeanProvider.getBean(LogRepository.class);

    /**
     * Log content
     */
    protected String content;

    /**
     * Who
     */
    protected Developer operator;

    public Log() {
    }


    public String content() {
        return content;
    }

    @SuppressWarnings("unchecked")
    public <T extends Log> T content(String content) {
        this.content = content;
        return (T) this;
    }

    public Developer operator() {
        return operator;
    }

    @SuppressWarnings("unchecked")
    public <T extends Log> T operator(Developer operator) {
        this.operator = operator;
        return (T) this;
    }

    public String displayContent() {
        return operator.displayName() + " " + content;
    }
}
