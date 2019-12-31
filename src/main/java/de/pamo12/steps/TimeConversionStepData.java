package de.pamo12.steps;

import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.trans.step.BaseStepData;
import org.pentaho.di.trans.step.StepDataInterface;

public class TimeConversionStepData extends BaseStepData implements StepDataInterface {
    public RowMetaInterface outputRowMeta;

    public TimeConversionStepData() {
        super();
    }
}