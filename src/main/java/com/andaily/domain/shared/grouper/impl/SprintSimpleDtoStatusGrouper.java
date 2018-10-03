package com.andaily.domain.shared.grouper.impl;

import com.andaily.domain.developer.SprintStatus;
import com.andaily.domain.dto.developer.SprintSimpleDto;
import com.andaily.domain.shared.grouper.AbstractGrouper;

import java.util.List;

/**
 * Date: 13-8-29
 *
 * @author Shengzhao Li
 */
public class SprintSimpleDtoStatusGrouper extends AbstractGrouper<SprintStatus, SprintSimpleDto> {

    public SprintSimpleDtoStatusGrouper(List<SprintSimpleDto> elements) {
        super(elements);
    }

    @Override
    public SprintStatus groupKey(SprintSimpleDto element) {
        return element.getStatus();
    }
}
