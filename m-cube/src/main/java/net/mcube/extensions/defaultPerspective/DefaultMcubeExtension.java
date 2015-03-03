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

/*
 * Created on 13-feb-2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */

package net.mcube.extensions.defaultPerspective;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JTextField;

import net.della.mplatform.application.core.Application;
import net.della.mplatform.application.core.Extension;
import net.della.mplatform.application.gui.KeyEventListener;
import net.della.mplatform.application.gui.structure.*;
import net.della.stuff.generic.loader.TextLoader;
import net.della.stuff.gui.swing.util.SwingUtil;
import net.mcube.library.LibraryImpl;



/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class DefaultMcubeExtension implements Extension {

   public static final String ID = "ThreePanePerspective";

   private AbstractPage page;

   private Application application;

   private ApplicationWindow appWindow;

   public static final String PAGE_ID = "ThreePanePerspective";

   public DefaultMcubeExtension() {

   }

   public void init(Application application) {
      this.application = application;
      this.appWindow = application.getWindow();

      initKeyboardShortcut();

      // page = new DockablePage();
      page = (AbstractPage) appWindow.getPage(della.extensions.defaultPage.DefaultPageExtension.PAGE_ID);

      page.setId(PAGE_ID);
      page.setName("default perspective");

      JLabel label = new JLabel("Search");
      LibraryImpl library = (LibraryImpl) application.getLibrary();
      final JTextField filterEdit = library.getFilterEdit();
      filterEdit.setColumns(10);
      filterEdit.addFocusListener(SwingUtil.addSelectAllOnEnterBehavior(filterEdit));
      page.setStartComponent(filterEdit);
      application.getWindow().addKeyEventListener(new KeyEventListener() {

         public void keyEventHappened(KeyEvent e) {
            if (e.isControlDown() && (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_L)) {
               filterEdit.requestFocusInWindow();
            }
         }

      });

      ApplicationWidget labelWidget = new ApplicationWidget(getId() + ".filterLabel", label);
      ApplicationWidget textFieldWidget = new ApplicationWidget(getId() + ".filterEditBox", filterEdit);

      application.getWindow().registerTopPanelWidget(labelWidget);
      application.getWindow().registerTopPanelWidget(textFieldWidget);

      page.useTopPanelComponent(getId() + ".filterLabel");
      page.useTopPanelComponent(getId() + ".filterEditBox");

      application.getWindow().registerPage(page.getID(), page);
   }

   private void initKeyboardShortcut() {
      appWindow.addKeyEventListener(new KeyEventListener() {

         public void keyEventHappened(KeyEvent e) {
            if (isEscapeEvent(e)) {
               handleEscapeKey();
               return;
            }
            if (isTABEvent(e)) {
               if (isKeyReleased(e))
                  handleTABKey();
               e.consume();
               return;
            }
            // if (isF5Released(e))
            // reloadViewsStyles();
         }

         private boolean isKeyReleased(KeyEvent e) {
            if (e.getID() != KeyEvent.KEY_RELEASED)
               return false;
            return true;
         }

         private boolean isTABEvent(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_TAB)
               return true;
            if (e.getKeyChar() == '\t')
               return true;
            return false;
         }

         private boolean isF5Released(KeyEvent e) {
            if (e.getID() != KeyEvent.KEY_RELEASED)
               return false;
            return e.getKeyCode() == KeyEvent.VK_F5;
         }

         private boolean isEscapeEvent(KeyEvent e) {
            if (e.getKeyCode() != KeyEvent.VK_ESCAPE)
               return false;
            if (e.getID() != KeyEvent.KEY_RELEASED)
               return false;
            return true;
         }
      });
   }

   protected void handleTABKey() {
      Page actualPerspective = application.getWindow().getCurrentPage();
      // actualPerspective.setFocusOnNextView(actualPerspective.getFocusedView());
      actualPerspective.setFocusOnNextView();

   }

   protected void reloadViewsStyles() {
      // tracksView.reloadStyles();
      // albumView.reloadStyles();
      // artistView.reloadStyles();

      Properties config = loadConfig();

      try {
         appWindow.setNewLookAndFeel(config.getProperty("laf"));
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * @return
    */
   private Properties loadConfig() {
      Properties config = new Properties();
      TextLoader loader = new TextLoader();
      String configPath = System.getProperty("user.dir") + File.separator + "config.txt";
      List rowConfig = loader.loadStringList(new File(configPath));
      for (Iterator it = rowConfig.iterator(); it.hasNext();) {
         String line = (String) it.next();
         StringTokenizer tokenizer = new StringTokenizer(line, "=");
         config.setProperty(tokenizer.nextToken(), tokenizer.nextToken());
      }
      return config;
   }

   protected void handleEscapeKey() {
      View activeView = application.getWindow().getCurrentPage().getFocusedView();
      if (activeView != null)
         activeView.removeSelection();
      // perspective.setFocusOnPreviousView(activeView);
      page.setFocusOnPreviousView();
   }

   protected void handleEscapeKey2() {
      page.setFocusOnPreviousComponent();
   }

   public String getId() {
      return ID;
   }

}