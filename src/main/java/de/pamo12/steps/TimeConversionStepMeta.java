package de.pamo12.steps;

import org.pentaho.di.core.annotations.Step;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.*;


@Step(
        id = "TimeConversionStep",
        name = "TimeConversionStep.Name",
        description = "TimeConversionStep.TooltipDesc",
        image = "de/pamo12/steps/resources/timeconversion.svg",
        categoryDescription = "i18n:org.pentaho.di.trans.step:BaseStep.Category.Transform",
        i18nPackageName = "de.pamo12.steps",
        documentationUrl = "TimeConversionStep.DocumentationURL",
        casesUrl = "TimeConversionStep.CasesURL"
)
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