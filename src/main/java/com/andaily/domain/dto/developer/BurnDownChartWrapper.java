package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.burndown.BurnDown;
import com.andaily.domain.dto.AbstractDTO;

import java.math.BigDecimal;

/**
 * Date: 13-9-29
 *
 * @author Shengzhao Li
 */
public class BurnDownChartWrapper extends AbstractDTO {

    private BurnDown burnDown;

    /*
    *  Chart parameters
    */
    //Default steps 10
    private int chartSteps = 10;
    //Default step width 10
    private int chartStepWidth = 10;
    //Default item step 1
    private int itemStep = 1;

    public BurnDownChartWrapper() {
    }

    public BurnDownChartWrapper(BurnDown burnDown) {
        this.burnDown = burnDown;
        this.initialChartParams();
    }

    protected void initialChartParams() {
        //steps = expect hours / default step width + 1   (less than 5 steps)
        this.chartSteps = new BigDecimal(this.burnDown.getEstimateTimesAsHours())
                .divide(new BigDecimal(chartStepWidth), BigDecimal.ROUND_HALF_EVEN).intValue() + 1;
        if (this.chartSteps < 5) {
            this.chartSteps = 5;
        }

        //steps = labels size / 10    (less than show 10 days)
        this.itemStep = new BigDecimal(this.burnDown.getLabels().size())
                .divide(BigDecimal.TEN, BigDecimal.ROUND_HALF_EVEN).intValue();
        if (this.itemStep < 1) {
            this.itemStep = 1;
        }
    }

    public BurnDown getBurnDown() {
        return burnDown;
    }

    public void setBurnDown(BurnDown burnDown) {
        this.burnDown = burnDown;
    }

    public int getChartSteps() {
        return chartSteps;
    }

    public void setChartSteps(int chartSteps) {
        this.chartSteps = chartSteps;
    }

    public int getChartStepWidth() {
        return chartStepWidth;
    }

    public void setChartStepWidth(int chartStepWidth) {
        this.chartStepWidth = chartStepWidth;
    }

    public int getItemStep() {
        return itemStep;
    }

    public void setItemStep(int itemStep) {
        this.itemStep = itemStep;
    }
}
