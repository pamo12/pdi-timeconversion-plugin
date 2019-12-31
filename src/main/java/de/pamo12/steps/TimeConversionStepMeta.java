package de.pamo12.steps;

import org.pentaho.di.core.CheckResult;
import org.pentaho.di.core.CheckResultInterface;
import org.pentaho.di.core.Counter;
import org.pentaho.di.core.annotations.Step;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleStepException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.core.row.ValueMeta;
import org.pentaho.di.core.row.ValueMetaInterface;
import org.pentaho.di.core.variables.VariableSpace;
import org.pentaho.di.core.xml.XMLHandler;
import org.pentaho.di.repository.ObjectId;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.*;
import org.w3c.dom.Node;

import java.util.List;
import java.util.Map;


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

    private String newField;

    public TimeConversionStepMeta() {
        super(); // allocate BaseStepInfo
    }

    public String getNewField() {
        return newField;
    }

    public void setNewField(String newField) {
        this.newField = newField;
    }

    @Override
    public StepInterface getStep(StepMeta stepMeta, StepDataInterface stepDataInterface, int cnr, TransMeta transMeta,
                                 Trans trans) {
        return new TimeConversionStep(stepMeta, stepDataInterface, cnr, transMeta, trans);
    }

    @Override
    public StepDataInterface getStepData() {
        return new TimeConversionStepData();
    }

    @Override
    public void setDefault() {
// TODO Auto-generated method stub
    }

    public void loadXML(Node stepnode, List<DatabaseMeta> databases, Map<String, Counter> counters)
            throws KettleXMLException {

        try {
            newField = XMLHandler.getTagValue(stepnode,"NEWFIELD");
        } catch (Exception e) {
            throw new KettleXMLException("Load XML: Excption ", e);
        }
    }

    public String getXML() throws KettleException {
        StringBuilder retVal = new StringBuilder();
        if (newField != null) {
            retVal.append("    ").append(XMLHandler.addTagValue("NEWFIELD", newField));
        }
        return retVal.toString();
    }

    public void readRep(Repository rep, ObjectId stepId, List<DatabaseMeta> databases, Map<String, Counter> counters)
            throws KettleException {
        try {
            newField = rep.getStepAttributeString(stepId, "NEWFIELD");
        } catch (Exception e) {
            throw new KettleException("Unexpected error reading step Sample Plug-In from the repository", e);
        }
    }

    public void saveRep(Repository rep, ObjectId transformationId, ObjectId stepId) throws KettleException {
        try {
            if (newField != null) {
                rep.saveStepAttribute(transformationId, stepId, "NEWFIELD", newField);
            }
        } catch (Exception e) {
            throw new KettleException("Unexpected error saving step Sample Plug-In from the repository", e);
        }
    }

    public void getFields(RowMetaInterface rowMeta, String origin, RowMetaInterface[] info, StepMeta nextStep,
                          VariableSpace space) throws KettleStepException {

        ValueMetaInterface newFieldMeta = new ValueMeta(getNewField(), ValueMetaInterface.TYPE_STRING);
        newFieldMeta.setName("NewField");
        newFieldMeta.setOrigin(origin);
        rowMeta.addValueMeta(newFieldMeta);
    }

    public void check(List<CheckResultInterface> remarks, TransMeta transMeta, StepMeta stepMeta, RowMetaInterface prev,
                      String input[], String output[], RowMetaInterface info) {
        CheckResult cr;
        if (prev == null || prev.size() == 0) {
            cr = new CheckResult(CheckResult.TYPE_RESULT_WARNING, "Not receiving any fields from previous steps!",
            stepMeta);
            remarks.add(cr);
        }
    }
}