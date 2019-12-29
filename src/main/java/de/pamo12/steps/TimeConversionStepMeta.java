package de.pamo12.steps;

import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.*;

public class TimeConversionStepMeta  extends BaseStepMeta implements StepMetaInterface {

    @Override
    public void setDefault() {

    }

    @Override
    public StepInterface getStep(StepMeta stepMeta, StepDataInterface stepDataInterface, int i, TransMeta transMeta,
                                 Trans trans) {
        return null;
    }

    @Override
    public StepDataInterface getStepData() {
        return null;
    }
}