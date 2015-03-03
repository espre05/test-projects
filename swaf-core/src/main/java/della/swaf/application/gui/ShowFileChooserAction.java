package della.swaf.application.gui;

import java.io.File;

import javax.swing.Action;
import javax.swing.JFileChooser;

import della.swaf.application.application.Application;
import della.swaf.application.gui.structure.ApplicationWindow;
import della.swaf.gui.components.WidgetFactory;

@SuppressWarnings("serial")
public class ShowFileChooserAction extends ApplicationAction {

   ShowFileChooserAction(Application application, Action action) {
      super(application, action);
   }

   private JFileChooser fc;

   private String dialogTitle;

   private String approveButtonText;

   private String tooltipText;

   protected int getApproveCode() {
      return JFileChooser.APPROVE_OPTION;
   }

   protected int showDialog() {
      fc = WidgetFactory.getInstance().createFolderChooser();
      fc.setDialogTitle(dialogTitle);
      fc.setApproveButtonText(approveButtonText);
      fc.setApproveButtonToolTipText(tooltipText);
      ApplicationWindow window = application.getWindow();
      if (window == null)
         return fc.showDialog(null, approveButtonText);
      return window.showFileChooser(fc);
   }

   protected Object parseUserInput() {
      File newFolder = fc.getSelectedFile();
      fc.setCurrentDirectory(newFolder);
      return newFolder;
   }

   protected JFileChooser getFileChooser() {
      return fc;
   }

   public final String getApproveButtonText() {
      return approveButtonText;
   }

   public final void setApproveButtonText(String approveButtonText) {
      this.approveButtonText = approveButtonText;
   }

   public final String getDialogTitle() {
      return dialogTitle;
   }

   public final void setDialogTitle(String dialogTitle) {
      this.dialogTitle = dialogTitle;
   }

   public final String getTooltipText() {
      return tooltipText;
   }

   public final void setTooltipText(String tooltipText) {
      this.tooltipText = tooltipText;
   }

}
