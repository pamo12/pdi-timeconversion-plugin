package de.pamo12.steps;

import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStep;
import org.pentaho.di.trans.step.StepDataInterface;
import org.pentaho.di.trans.step.StepInterface;
import org.pentaho.di.trans.step.StepMeta;

public class TimeConversionStep extends BaseStep implements StepInterface {

    public TimeConversionStep(StepMeta stepMeta, StepDataInterface stepDataInterface, int copyNr, TransMeta transMeta,
                              Trans trans) {
        super(stepMeta, stepDataInterface, copyNr, transMeta, trans);
    }
}