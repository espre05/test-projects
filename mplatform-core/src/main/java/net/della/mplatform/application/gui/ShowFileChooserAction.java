/**
 * Copyright (C) 2008 Daniele Dellafiore <daniele.dellafiore@gmail.com>
 * 
 * The contents of this file are subject to the Mozilla Public
 * License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.mozilla.org/MPL/MPL-1.1.html
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied.
 * See the License for the specific language governing rights and
 * limitations under the License.
 */

package net.della.mplatform.application.gui;

import java.io.File;

import javax.swing.Action;
import javax.swing.JFileChooser;

import net.della.mplatform.application.core.Application;
import net.della.mplatform.application.gui.structure.ApplicationWindow;
import net.della.stuff.gui.components.WidgetFactory;


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
