package com.andaily.domain.shared.grouper.impl;

import com.andaily.domain.dto.user.DeveloperDto;
import com.andaily.domain.shared.grouper.AbstractGrouper;
import com.andaily.domain.user.ScrumTerm;

import java.util.List;

/**
 * Date: 13-12-18
 *
 * @author Shengzhao Li
 */
public class DeveloperDtoScrumTermGrouper extends AbstractGrouper<ScrumTerm, DeveloperDto> {

    public DeveloperDtoScrumTermGrouper(List<DeveloperDto> elements) {
        super(elements);
    }

    @Override
    public ScrumTerm groupKey(DeveloperDto element) {
        return element.getScrumTerm();
    }
}
