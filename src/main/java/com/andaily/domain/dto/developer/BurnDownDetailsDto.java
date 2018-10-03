package com.andaily.domain.dto.developer;

import com.andaily.domain.dto.AbstractDTO;

/**
 * Date: 13-9-29
 *
 * @author Shengzhao Li
 */
public class BurnDownDetailsDto extends AbstractDTO {

    private BurnDownChartWrapper burnDownWrapper;

    public BurnDownDetailsDto(BurnDownChartWrapper burnDownWrapper) {
        this.burnDownWrapper = burnDownWrapper;
    }

    public BurnDownChartWrapper getBurnDownWrapper() {
        return burnDownWrapper;
    }
}
