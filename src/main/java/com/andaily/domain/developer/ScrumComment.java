package com.andaily.domain.developer;

import com.andaily.domain.AbstractDomain;
import com.andaily.domain.user.Developer;

import java.util.Date;

/**
 * Date: 13-9-22
 *
 * @author Shengzhao Li
 */
public class ScrumComment extends AbstractDomain {

    //who
    private Developer developer;
    //when
    private Date commentTime;
    //do what
    private String content;

    public ScrumComment() {
    }

    public ScrumComment(Developer developer, Date commentTime, String content) {
        this.developer = developer;
        this.commentTime = commentTime;
        this.content = content;
    }

    public Developer developer() {
        return developer;
    }

    public Date commentTime() {
        return commentTime;
    }

    public String content() {
        return content;
    }
}
