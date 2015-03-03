/**
 * Copyright (C) 2003-2008 Daniele Dellafiore <daniele.dellafiore@gmail.com>
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package old.form;

import javax.swing.JDialog;

import net.della.mplatform.application.gui.View;
import net.della.mplatform.core.datatypes.ObservableItem;
import net.della.mplatform.core.util.swing.SwingUtil;
import net.mcube.gui.GUIUtil;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.beans.Model;


public class FormDialogShower {

   private PropertyListenerFactory propertyListenerFactory;

   public FormDialogShower() {
      propertyListenerFactory = new ItemPropertyListenerFactory();
   }

   public void openDialog(String bindProperty, View focusedView, ModelBuilder modelBuilder) {

      ObservableItem item = GUIUtil.recoverSelectedItem(focusedView, bindProperty);
      if (item == null)
         return;

      final JDialog editDialog = createDialog(item, bindProperty, modelBuilder);

      showDialog(editDialog);
   }

   public void showDialog(final JDialog editDialog) {
      editDialog.setUndecorated(true);
      SwingUtil.locateOnScreenCenter(editDialog);
      editDialog.setAlwaysOnTop(true);
      editDialog.pack();
      editDialog.setVisible(true);
   }

   private JDialog createDialog(ObservableItem item, String bindProperty, ModelBuilder builder) {
      FormBuilder formBuilder = new SingleTextFieldFormBuilder(bindProperty);

      final Model model = builder.createModel(item);

      model.addPropertyChangeListener(propertyListenerFactory.newChangeAdapter(item));
      model.addPropertyChangeListener(propertyListenerFactory.newLogAdapter());

      final PresentationModel presentationModel = builder.createPresentationModel(model);
      // presentationModel
      // .addPropertyChangeListener(propertyListenerFactory.newUpdaterAdapter(item));

      DataInputForm form = formBuilder.buildForm(presentationModel);
      form.setBackground(form.getBackground().darker());

      final JDialog editDialog = new JDialog();
      editDialog.add(form);

      form.addListener(new FormListener() {

         public void okEventHappened() {
            editDialog.dispose();
            presentationModel.triggerCommit();
         }

         public void cancelEventHappened() {
            editDialog.dispose();
            presentationModel.triggerFlush();
         }
      });
      return editDialog;
   }

   public void setPropertyListenerFactory(ItemPropertyListenerFactory propertyListenerFactory) {
      this.propertyListenerFactory = propertyListenerFactory;
   }

}
