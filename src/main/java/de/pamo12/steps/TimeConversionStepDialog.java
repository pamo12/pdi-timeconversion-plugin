package de.pamo12.steps;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.*;
import org.pentaho.di.core.Const;
import org.pentaho.di.i18n.BaseMessages;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDialogInterface;
import org.pentaho.di.ui.core.widget.TextVar;
import org.pentaho.di.ui.trans.step.BaseStepDialog;

public class TimeConversionStepDialog extends BaseStepDialog implements StepDialogInterface {

    private TimeConversionStepMeta timeConversionStepMeta;
    private TextVar wnewField;

    public TimeConversionStepDialog(Shell parent, Object in, TransMeta tr, String sname) {
        super(parent, (BaseStepMeta) in, tr, sname);
        timeConversionStepMeta = (TimeConversionStepMeta) in;
    }

    @Override
    public String open() {
        Shell parent = getParent();
        Display display = parent.getDisplay();

        shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.RESIZE | SWT.MIN | SWT.MAX);
        props.setLook(shell);
        setShellImage(shell, timeConversionStepMeta);

        ModifyListener lsMod = new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                timeConversionStepMeta.setChanged();
            }
        };
        changed = timeConversionStepMeta.hasChanged();

        FormLayout formLayout = new FormLayout();
        formLayout.marginWidth = Const.FORM_MARGIN;
        formLayout.marginHeight = Const.FORM_MARGIN;

        shell.setLayout(formLayout);
        shell.setText("Timezone Converter");

        int middle = props.getMiddlePct();
        int margin = Const.MARGIN;

        // Step name
        wlStepname = new Label(shell, SWT.RIGHT);
        wlStepname.setText("Step Name");
        props.setLook(wlStepname);
        fdlStepname = new FormData();
        fdlStepname.left = new FormAttachment(0, 0);
        fdlStepname.right = new FormAttachment(middle, -margin);
        fdlStepname.top = new FormAttachment(0, margin);
        wlStepname.setLayoutData(fdlStepname);
        wStepname = new Text(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
        props.setLook(wStepname);
        wStepname.addModifyListener(lsMod);
        fdStepname = new FormData();
        fdStepname.left = new FormAttachment(middle, 0);
        fdStepname.top = new FormAttachment(0, margin);
        fdStepname.right = new FormAttachment(100, 0);
        wStepname.setLayoutData(fdStepname);
        Control lastWidget = wStepname;

        Label wlnewField = new Label(shell, SWT.RIGHT);
        wlnewField.setText("New Field[Type:String]");
        props.setLook(wlnewField);
        FormData fdlnewField = new FormData();
        fdlnewField.top = new FormAttachment(lastWidget, margin);
        fdlnewField.left = new FormAttachment(0, 0);
        fdlnewField.right = new FormAttachment(middle, -margin);
        wlnewField.setLayoutData(fdlnewField);
        wnewField = new TextVar(transMeta, shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
        props.setLook(wnewField);
        wnewField.addModifyListener(lsMod);
        FormData fdnewField = new FormData();
        fdnewField.top = new FormAttachment(lastWidget, margin);
        fdnewField.left = new FormAttachment(middle, 0);
        fdnewField.right = new FormAttachment(100, 0);
        wnewField.setLayoutData(fdnewField);
        lastWidget = wnewField;

        // Buttons
        wOK = new Button(shell, SWT.PUSH);
        wOK.setText(BaseMessages.getString("System.Button.OK")); //$NON-NLS-1$
        wCancel = new Button(shell, SWT.PUSH);
        wCancel.setText(BaseMessages.getString("System.Button.Cancel")); //$NON-NLS-1$

        setButtonPositions(new Button[]{wOK, wCancel}, margin, null);

        lsCancel = new Listener() {
            public void handleEvent(Event e) {
                cancel();
            }
        };
        lsOK = new Listener() {
            public void handleEvent(Event e) {
                ok();
            }
        };
        wCancel.addListener(SWT.Selection, lsCancel);
        wOK.addListener(SWT.Selection, lsOK);

        lsDef = new SelectionAdapter() {
            public void widgetDefaultSelected(SelectionEvent e) {
                ok();
            }
        };

        // Set the shell size, based upon previous time…
        setSize(shell, 200, 150, true);
        getData(timeConversionStepMeta, true);
        // consumerMeta.setChanged(changed);

        // setTableFieldCombo();

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        return stepname;
    }


    private void getData(TimeConversionStepMeta timeConversionStepMeta, boolean copyStepname) {
        if (copyStepname) {
            wStepname.setText(stepname);
            if (timeConversionStepMeta.getNewField() != null)
                wnewField.setText(timeConversionStepMeta.getNewField());
        }
    }

    private void setData(TimeConversionStepMeta timeConversionStepMeta) {
        stepname = wStepname.getText();
        timeConversionStepMeta.setNewField(wnewField.getText());
        timeConversionStepMeta.setChanged();
    }

    private void ok() {
        if (Const.isEmpty(wStepname.getText())) {
            return;
        }
        setData(timeConversionStepMeta);
        dispose();
    }

    private void cancel() {
        stepname = null;
        timeConversionStepMeta.setChanged(changed);
        dispose();
    }
}