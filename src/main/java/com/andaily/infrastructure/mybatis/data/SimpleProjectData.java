package com.andaily.infrastructure.mybatis.data;

import com.andaily.domain.dto.SimpleDto;

/**
 * Date: 13-11-6
 *
 * @author Shengzhao Li
 */
public class SimpleProjectData extends SimpleDto {

    private String code;

    public SimpleProjectData() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
