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

package net.mcube.extensions.edit;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import net.della.mplatform.application.core.Application;
import net.della.mplatform.application.core.Extension;
import net.della.mplatform.application.datatypes.ItemSet;
import net.della.mplatform.application.gui.ActionFactory;
import net.della.mplatform.application.gui.structure.View;
import net.della.stuff.gui.swing.util.SwingUtil;
import net.mcube.extensions.album.AlbumExtension;
import net.mcube.extensions.songs.SongSet;

import com.jgoodies.binding.beans.Model;


/*
 * Created on 28-dic-2003
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */

public class EditExtension implements Extension {

   private static final String ID = "edit";

   private Application application;

   private View albumView;

   public EditExtension() {

   }

   public void init(Application application) {

      this.application = application;

      albumView = application.getWindow().getView(AlbumExtension.VIEW_ID);
      addExtensionsToView(albumView);

   }

   private void addExtensionsToView(View view) {
      Action action = new AbstractAction() {

         public void actionPerformed(ActionEvent e) {
            openDialog((ItemSet) albumView.getSelection().get(0));
         }
      };
      InputMap inputMap = view.getInputMap();
      ActionMap actionMap = view.getActionMap();
      action.putValue(Action.NAME, "Edit...");

      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK), action
            .getValue(Action.NAME));
      actionMap.put(action.getValue(Action.NAME), action);

      inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), action.getValue(Action.NAME));
      actionMap.put(action.getValue(Action.NAME), action);

      JMenuItem menuItem = new JMenuItem(action);
      menuItem.setName("Edit...");
      menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
      view.addToPopup(View.CONTEXT_MENU, menuItem);
   }

   private static void openDialog(ItemSet itemSet) {
      EditDialogBuilder dialogBuilder = new EditDialogBuilder();
      dialogBuilder.setUseButtonBar(true);

      AlbumModelFactory factory = new AlbumModelFactory();
      Model model = factory.createModel(itemSet);
      AlbumPresentationModel presentationModel = factory.createPresentationModel(model);
      ItemPropertyListenerFactory propertyListenerFactory = new ItemPropertyListenerFactory();
      model.addPropertyChangeListener(AlbumPresentationModel.UPDATE_FILE_TAG, new PropertyChangeListener() {

         public void propertyChange(PropertyChangeEvent evt) {
            SongSet.updateID3 = (Boolean) evt.getNewValue();
         }
      });
      model.addPropertyChangeListener(propertyListenerFactory.newChangeAdapter(itemSet));
      model.addPropertyChangeListener(propertyListenerFactory.newLogAdapter());

      dialogBuilder.setOkAction(ActionFactory.createOkAction(presentationModel));
      dialogBuilder.setResetAction(ActionFactory.createResetAction(presentationModel));
      dialogBuilder.setCancelAction(ActionFactory.createCancelAction(presentationModel));

      AbstractEditFormBuilder formBuilder = new AlbumEditFormBuilder();
      formBuilder.setUseEnableCheckBox(true);
      dialogBuilder.buildDialog(formBuilder.buildEditorPanel(presentationModel));
      JDialog editDialog = dialogBuilder.getDialog();
      SwingUtil.locateOnScreenCenter(editDialog);
      editDialog.setAlwaysOnTop(true);
      editDialog.pack();
      editDialog.setVisible(true);
   }

   public String getId() {
      return ID;
   }

}