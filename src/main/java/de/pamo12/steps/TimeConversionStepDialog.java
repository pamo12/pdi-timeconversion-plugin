package de.pamo12.steps;

import org.eclipse.swt.widgets.Shell;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDialogInterface;
import org.pentaho.di.ui.trans.step.BaseStepDialog;

public class TimeConversionStepDialog extends BaseStepDialog implements StepDialogInterface {

    public TimeConversionStepDialog(Shell parent, BaseStepMeta baseStepMeta, TransMeta transMeta, String stepname) {
        super(parent, baseStepMeta, transMeta, stepname);
    }

    @Override
    public String open() {
        return null;
    }
}