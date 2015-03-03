/**
 * 
 */
package della.swaf.form;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.*;
import com.jgoodies.binding.value.ValueModel;

/**
 * @author Daniele
 * 
 */
public class ComponentFactory {

   private static final class LiveCommit implements ActionListener, ChangeListener, FocusListener {
      private final PresentationModel model;

      LiveCommit(PresentationModel model) {
         super();
         this.model = model;
      }

      public void actionPerformed(ActionEvent e) {
         model.triggerCommit();
      }

      public void stateChanged(ChangeEvent e) {
         model.triggerCommit();
      }

      public void focusGained(FocusEvent arg0) {
      }

      public void focusLost(FocusEvent arg0) {
         model.triggerCommit();
      }
   }

   public static JCheckBox createCheckBox(final PresentationModel presentationModel, String bindProperty) {
      final ValueModel valueModel = presentationModel.getBufferedModel(bindProperty);
      JCheckBox checkBox = BasicComponentFactory.createCheckBox(valueModel, bindProperty);
      // checkBox.addActionListener(new LiveCommit(presentationModel));
      return checkBox;
   }

   public static AbstractButton createToggleButton(final PresentationModel presentationModel,
         String bindProperty) {
      final ValueModel valueModel = presentationModel.getBufferedModel(bindProperty);
      AbstractButton button = createToggleButton(valueModel, bindProperty);
      button.addActionListener(new LiveCommit(presentationModel));
      return button;

   }

   private static AbstractButton createToggleButton(final ValueModel valueModel, String bindProperty) {
      AbstractButton button = new JToggleButton(bindProperty);
      boolean enabled = button.getModel().isEnabled();
      button.setModel(new ToggleButtonAdapter(valueModel));
      button.setEnabled(enabled);
      Bindings.addComponentPropertyHandler(button, valueModel);
      return button;
   }

   /**
    * @param presentationModel
    * @param influence_weight
    */
   public static JSpinner createSpinner(PresentationModel presentationModel, String bindProperty) {
      final ValueModel valueModel = presentationModel.getBufferedModel(bindProperty);
      JSpinner button = createSpinner(valueModel);
      button.addChangeListener(new LiveCommit(presentationModel));
      return button;
   }

   private static JSpinner createSpinner(final ValueModel valueModel) {
      JSpinner button = new JSpinner();
      button.setModel(SpinnerAdapterFactory.createNumberAdapter(valueModel, new Double(1), new Double(-100),
            new Double(100), new Double(0.1)));
      Bindings.addComponentPropertyHandler(button, valueModel);
      return button;
   }

   public static JTextField createTextField(PresentationModel presentationModel, String bindProperty) {
      JTextField field = BasicComponentFactory.createTextField(presentationModel
            .getBufferedModel(bindProperty));
      // field.addFocusListener(new LiveCommit(presentationModel));
      return field;
   }

   public static JTextArea createTextArea(PresentationModel presentationModel, String bindProperty) {
      JTextArea area = BasicComponentFactory.createTextArea(presentationModel.getBufferedModel(bindProperty));
      return area;
   }

   public static JComponent createLabel(PresentationModel presentationModel, String bindProperty) {
      JLabel label = BasicComponentFactory.createLabel(presentationModel.getBufferedModel(bindProperty));
      return label;
   }

}
