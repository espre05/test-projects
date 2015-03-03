package della.swaf.form;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import jin.collection.core.Iter;
import jin.collection.core.Operation;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.beans.Model;
import com.jgoodies.binding.beans.PropertyConnector;
import com.jgoodies.binding.value.BufferedValueModel;
import com.jgoodies.forms.builder.ButtonBarBuilder;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.*;
import com.jgoodies.validation.formatter.EmptyNumberFormatter;

import della.swaf.gui.components.WidgetFactory;

public class FormBuilder {

   protected DefaultFormBuilder formBuilder;

   protected CellConstraints cc;

   private PresentationModel presentationModel;

   private final Form form;

   private List submitActions;

   private PanelBuilder panelBuilder;

   private ButtonBarBuilder buttonBarBuilder;

   /**
    * @param presentationModel2
    *           TODO: made default visibility
    */
   public FormBuilder(PresentationModel presentationModel) {
      this.presentationModel = presentationModel;
      submitActions = new ArrayList();
      form = new Form(this.presentationModel);
   }

   private void createEditorPanel() {
      FormLayout formLayout = new FormLayout("pref, 12px, 125px");
      formBuilder = new DefaultFormBuilder(formLayout);
      formBuilder.setDefaultDialogBorder();
      cc = new CellConstraints();
      FormLayout panelLayout = new FormLayout("fill:pref", "fill:pref, 3dlu, pref");
      panelBuilder = new PanelBuilder(panelLayout);
      panelBuilder.getPanel().setBorder(new EmptyBorder(18, 12, 12, 12));
   }

   public final Container getPanel() {
      JPanel formPanel = formBuilder.getPanel();
      JPanel buttonBar = buttonBarBuilder.getPanel();
      buttonBar.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
      panelBuilder.add(formPanel);
      panelBuilder.nextRow(2);
      panelBuilder.add(buttonBar);
      Container container = panelBuilder.getContainer();
      formPanel.setFocusCycleRoot(true);
      formPanel.setFocusTraversalPolicyProvider(true);
      return container;
   }

   protected void add(JComponent c, int row, int column) {
      formBuilder.add(c, cc.xy(column, row));
   }

   // TODO: should have default or protected visibility
   protected void add(JComponent c) {
      CellConstraints xy = cc.xy(formBuilder.getColumn(), formBuilder.getRow());
      formBuilder.add(c, xy);
   }

   public void appendColumn() {
      formBuilder.appendColumn(new ColumnSpec(ColumnSpec.DEFAULT, Sizes.DEFAULT, FormSpec.NO_GROW));
      formBuilder.appendColumn(new ColumnSpec(ColumnSpec.DEFAULT, Sizes.DLUX6, FormSpec.NO_GROW));
      formBuilder.appendColumn(new ColumnSpec(ColumnSpec.DEFAULT, Sizes.DEFAULT, FormSpec.NO_GROW));
   }

   public JComponent appendTextField(String boundProperty) {
      JComponent component = ComponentFactory.createTextField(presentationModel, boundProperty);
      appendLine(boundProperty, component);
      return component;
   }

   public void appendNumberField(String property) {
      JFormattedTextField component = BasicComponentFactory.createFormattedTextField(presentationModel
            .getBufferedModel(property), new EmptyNumberFormatter());
      appendLine(property, component);
   }

   public void appendYearField(String property) {
      NumberFormat format = NumberFormat.getIntegerInstance();
      format.setMinimumIntegerDigits(4);
      format.setMaximumIntegerDigits(4);
      JFormattedTextField component = BasicComponentFactory.createFormattedTextField(presentationModel
            .getBufferedModel(property), format);
      appendLine(property, component);
   }

   public void appendTextArea(String property) {
      appendLine(property, ComponentFactory.createTextArea(presentationModel, property));
   }

   public void appendLabel(String property) {
      JComponent component = ComponentFactory.createLabel(presentationModel, property);
      appendLine(property, component);
   }

   public void moveColumn(int n) {
      if (n < 0)
         throw new IllegalArgumentException("parameter must be a positive integer");
      formBuilder.nextColumn(n * 3);
   }

   public static FormBuilder newFormBuilder(Model model, PresentationModel presentationModel) {
      FormBuilder formBuilder = new FormBuilder(presentationModel);
      formBuilder.createEditorPanel();
      return formBuilder;
   }

   public static FormBuilder newFormBuilder(Model model) {
      PresentationModel presentationModel = new PresentationModel(model);
      return newFormBuilder(model, presentationModel);
   }

   public Form getForm() {
      return form;
   }

   public void appendCheckBox(String boundProperty) {
      appendLine("", ComponentFactory.createCheckBox(presentationModel, boundProperty));
   }

   /**
    * Observes the presentation model's <em>bufferedComposerEnabled</em>
    * property to update the composer field's enablement. Also, initializes the
    * field's enablement with the current value of the buffered composer enabled
    * property.
    * 
    * @param propertyName
    */
   public void connect(String propertyName, JComponent guiComponent) {
      PropertyConnector connector = PropertyConnector.connect(presentationModel, propertyName, guiComponent,
            "enabled");
      connector.updateProperty2();
   }

   public void connect(String propertyName1, String propertyName2) {
      BufferedValueModel bufferedModel = presentationModel.getBufferedModel(propertyName2);
      // how to find the guiComponent related to propertyName2???
      // JComponent guiComponent = ????
      // connect(propertyName1, guiComponent);
   }

   public void useApplyResetButtonBar() {

      onSubmitExecute(new FormAction() {
         public void execute() {
            form.save();
         }
      });

      final JButton applyButton = WidgetFactory.createButton(new AbstractAction("Apply") {
         public void actionPerformed(ActionEvent arg0) {
            Iter.forEach(submitActions, new Operation() {
               public void execute(Object element) {
                  ((FormAction) element).execute();
               }
            });
         }
      });
      final JButton resetButton = WidgetFactory.createButton(new AbstractAction("Reset") {
         public void actionPerformed(ActionEvent arg0) {
            form.reset();
         }
      });

      enableWhenSomethingModifiedBehavior(applyButton, resetButton);

      buttonBarBuilder = new ButtonBarBuilder();
      buttonBarBuilder.addGlue();
      buttonBarBuilder.addGriddedButtons(new JButton[] { applyButton, resetButton });
   }

   private void enableWhenSomethingModifiedBehavior(final JButton applyButton, final JButton resetButton) {
      applyButton.setEnabled(false);
      resetButton.setEnabled(false);
      presentationModel.addPropertyChangeListener(new PropertyChangeListener() {
         @Override
         public void propertyChange(PropertyChangeEvent evt) {
            if (!"buffering".equals(evt.getPropertyName())) {
               return;
            }
            boolean changing = (Boolean) evt.getNewValue();
            applyButton.setEnabled(changing);
            resetButton.setEnabled(changing);
         }
      });
   }

   public void nextColumn() {
      formBuilder.nextColumn(4);
   }

   public void reset() {
      formBuilder.setRow(1);
   }

   public void appendLineField(String string, String connectedProperty) {
      JComponent component = appendTextField(string);
      connect(connectedProperty, component);
   }

   public void onSubmitExecute(FormAction formAction) {
      submitActions.add(formAction);
   }

   public void appendButton(Action action) {
      JComponent component = WidgetFactory.createButton(action);
      appendNoLabelLine(component);
   }

   protected void appendNoLabelLine(JComponent component) {
      formBuilder.appendRow(FormFactory.DEFAULT_ROWSPEC);
      formBuilder.nextColumn(2);
      add(component);
      formBuilder.nextLine();
      formBuilder.appendRow(formBuilder.getLineGapSpec());
   }

   protected void appendLine(String labelText, JComponent component) {
      formBuilder.appendRow(FormFactory.DEFAULT_ROWSPEC);
      add(WidgetFactory.createLabel(labelText));
      formBuilder.nextColumn(2);
      add(component);
      formBuilder.nextLine(2);
      formBuilder.appendRow(formBuilder.getLineGapSpec());
   }

   public void appendSpace() {
      formBuilder.appendRow(FormFactory.DEFAULT_ROWSPEC);
      formBuilder.nextLine();
   }

   public void appendSeparator() {
      formBuilder.appendRow("9dlu");
      formBuilder.appendSeparator("");
      formBuilder.nextLine(2);
      formBuilder.appendRow(formBuilder.getLineGapSpec());
   }

   // public void appendLineChecked(String boundProperty) {
   //
   // panelBuilder.appendRow(new RowSpec(RowSpec.DEFAULT, Sizes.DEFAULT,
   // FormSpec.NO_GROW));
   //
   // add(ComponentFactory.createCheckBox(presentationModel, boundProperty
   // + AlbumModel.ENABLE_EDIT));
   //
   // panelBuilder.nextColumn(2);
   //
   // JComponent component = ComponentFactory.createTextField(
   // presentationModel, boundProperty);
   // BufferedValueModel bufferedModel = presentationModel
   // .getBufferedModel(boundProperty + AlbumModel.ENABLE_EDIT);
   // AlbumPresentationModel apm = (AlbumPresentationModel) presentationModel;
   // bufferedModel.addValueChangeListener(apm.new ChangeHandler(
   // boundProperty));
   // add(component);
   // panelBuilder.nextLine();
   // }

}
