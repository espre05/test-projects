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

package net.della.mplatform.scenario.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.geom.AffineTransform;

import javax.swing.*;

import net.della.mplatform.gui.components.InternalFrame;
import net.della.stuff.gui.components.GradientPanel;
import net.della.stuff.gui.components.ShadowBorder;
import net.della.stuff.gui.swing.RaisedHeaderBorder;

import com.sun.scenario.scenegraph.*;

public class SGInternalFrame extends JSGPanel implements InternalFrame {

   private JLabel titleLabel;

   private GradientPanel gradientPanel;

   private JPanel headerPanel;

   private boolean isSelected;

   private SGGroup root;

   private SGGroup topGroup;

   private JToolBar toolbar;

   /**
    * Constructs a <code>SimpleInternalFrame</code> with the specified title.
    * 
    * @param title
    *           the initial title
    */
   public SGInternalFrame(String title) {
      this(null, title, null, null);
   }

   /**
    * Constructs a <code>SimpleInternalFrame</code> with the specified icon,
    * and title.
    * 
    * @param icon
    *           the initial icon
    * @param title
    *           the initial title
    */
   public SGInternalFrame(Icon icon, String title) {
      this(icon, title, null, null);
   }

   /**
    * Constructs a <code>SimpleInternalFrame</code> with the specified title,
    * tool bar, and content panel.
    * 
    * @param title
    *           the initial title
    * @param bar
    *           the initial tool bar
    * @param content
    *           the initial content pane
    */
   public SGInternalFrame(String title, JToolBar bar, JComponent content) {
      this(null, title, bar, content);
   }

   /**
    * Constructs a <code>SimpleInternalFrame</code> with the specified icon,
    * title, tool bar, and content panel.
    * 
    * @param icon
    *           the initial icon
    * @param title
    *           the initial title
    * @param bar
    *           the initial tool bar
    * @param content
    *           the initial content pane
    */
   public SGInternalFrame(Icon icon, String title, JToolBar bar, JComponent content) {
      this.isSelected = false;
      this.titleLabel = new JLabel(title, icon, SwingConstants.LEADING);
      topGroup = buildHeader(titleLabel, bar);
      // add(top, BorderLayout.NORTH);
      if (content != null) {
         setContent(content);
      }
      setBorder(new ShadowBorder());
      setSelected(true);
      // updateHeader();
   }

   public void setRootGroup(SGGroup rootNode) {
      super.setScene(rootNode);
      this.root = rootNode;
      root.add(topGroup);
   }

   /**
    * Creates and answers the header panel, that consists of: an icon, a title
    * label, a tool bar, and a gradient background.
    * 
    * @param label
    *           the label to paint the icon and text
    * @param bar
    *           the panel's tool bar
    * @return the panel's built header area
    */
   private SGGroup buildHeader(JLabel label, JToolBar bar) {
      gradientPanel = new GradientPanel(Color.BLUE);
      label.setOpaque(false);

      gradientPanel.add(label);
      gradientPanel.setBorder(BorderFactory.createEmptyBorder(3, 4, 3, 1));

      headerPanel = new JPanel();
      setToolBar(bar);
      headerPanel.setBorder(new RaisedHeaderBorder());
      headerPanel.setOpaque(false);
      SGGroup topGroup = new SGGroup();

      SGComponent gradientNode = new SGComponent();
      gradientNode.setComponent(gradientPanel);
      SGComponent headerNode = new SGComponent();
      headerNode.setComponent(headerPanel);

      topGroup.add(gradientNode);
      topGroup.add(headerNode);
      return topGroup;
   }

   @Override
   public Component getContent() {
      // TODO Auto-generated method stub
      return null;
   }

   /*
    * (non-Javadoc)
    * 
    * @see della.swaf.util.InternalFrame#getFrameIcon()
    */
   public Icon getFrameIcon() {
      return titleLabel.getIcon();
   }

   /*
    * (non-Javadoc)
    * 
    * @see della.swaf.util.InternalFrame#setFrameIcon(javax.swing.Icon)
    */
   public void setFrameIcon(Icon newIcon) {
      Icon oldIcon = getFrameIcon();
      titleLabel.setIcon(newIcon);
      firePropertyChange("frameIcon", oldIcon, newIcon);
   }

   /*
    * (non-Javadoc)
    * 
    * @see della.swaf.util.InternalFrame#getHeader()
    */
   public Component getHeader() {
      return headerPanel;
   }

   /*
    * (non-Javadoc)
    * 
    * @see della.swaf.util.InternalFrame#getTitle()
    */
   public String getTitle() {
      return titleLabel.getText();
   }

   /*
    * (non-Javadoc)
    * 
    * @see della.swaf.util.InternalFrame#setTitle(java.lang.String)
    */
   public void setTitle(String newText) {
      String oldText = getTitle();
      titleLabel.setText(newText);
      firePropertyChange("title", oldText, newText);
   }

   /*
    * (non-Javadoc)
    * 
    * @see della.swaf.util.InternalFrame#getToolBar()
    */
   public JToolBar getToolBar() {
      return toolbar;
   }

   /*
    * (non-Javadoc)
    * 
    * @see della.swaf.util.InternalFrame#setToolBar(javax.swing.JToolBar)
    */
   public void setToolBar(JToolBar newToolBar) {
      this.toolbar = newToolBar;
   }

   /*
    * (non-Javadoc)
    * 
    * @see della.swaf.util.InternalFrame#isSelected()
    */
   public boolean isSelected() {
      return isSelected;
   }

   @Override
   public void setSelected(boolean newValue) {
      isSelected = newValue;
   }

   @Override
   public void setContent(Component newContent) {
      SGComponent contentNode = new SGComponent();
      contentNode.setComponent(newContent);

      AffineTransform cpScaleLarge = AffineTransform.getTranslateInstance(30, 60);
      SGTransform componentScaler = SGTransform.createAffine(cpScaleLarge, contentNode);

      root.add(componentScaler);
   }

   @Override
   public void revalidateAndRepaint() {
      // no need to to anything
   }

}
