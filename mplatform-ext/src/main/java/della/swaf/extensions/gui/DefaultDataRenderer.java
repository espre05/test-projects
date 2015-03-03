/*
 * Created on 22-set-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package della.swaf.extensions.gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import net.della.mplatform.application.datatypes.Item;
import net.della.mplatform.gui.renderer.DataRenderer;
import net.della.stuff.gui.components.GradientPanel;
import net.della.stuff.gui.swing.SwingUtil;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;

/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class DefaultDataRenderer implements DataRenderer {

   protected PanelBuilder builder;

   protected CellConstraints cc;

   private JPanel contentPane;

   private GradientPanel backGroundPanel;

   private Color selectionColor;

   private DataRendererUpdater updater;

   private final java.util.List icons;

   private final Map iconsMap;

   public DefaultDataRenderer(JPanel panel) {
      this.contentPane = panel;
      cc = new CellConstraints();
      icons = new ArrayList();
      iconsMap = new HashMap();

      selectionColor = UIManager.getColor("Table.selectionBackground");
      selectionColor = new Color(selectionColor.getRed(), selectionColor.getGreen(),
            selectionColor.getBlue(), 30);

      Color tableColor = UIManager.getColor("Table.background");
      backGroundPanel = new GradientPanel(new BorderLayout(), tableColor.brighter()) {
         public String getToolTipText(MouseEvent event) {
            JComponent componentAt = (JComponent) contentPane.getComponentAt(event.getX(), event.getY());
            if (componentAt != null && componentAt.getToolTipText() != null)
               return componentAt.getToolTipText();
            return super.getToolTipText();
         }
      };
      backGroundPanel.setOrientation(GradientPanel.VERTICAL);
      backGroundPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
      backGroundPanel.add(contentPane, BorderLayout.CENTER);
      // backGroundPanel.setToolTipText("bk panel...");

   }

   public JComponent getComponentFor(Item album) {
      updater.updateFor(album);

      return backGroundPanel;
   }

   public void setSelected(boolean b) {
      if (b) {
         contentPane.setBackground(selectionColor);
         contentPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, selectionColor.darker(),
               selectionColor.darker()));

      } else {
         contentPane.setBorder(BorderFactory.createEmptyBorder());
         contentPane.setBackground(new Color(contentPane.getBackground().getRed(), contentPane
               .getBackground().getGreen(), contentPane.getBackground().getBlue(), 0));
      }
   }

   public int getRendererHeight() {
      return 30;
   }

   public Component add(Component comp) {
      return contentPane.add(comp);
   }

   public Component add(Component comp, int index) {
      return contentPane.add(comp, index);
   }

   public void add(Component comp, Object constraints) {
      contentPane.add(comp, constraints);
   }

   public void add(Component comp, Object constraints, int index) {
      contentPane.add(comp, constraints, index);
   }

   public void add(PopupMenu popup) {
      contentPane.add(popup);
   }

   public Component add(String name, Component comp) {
      return contentPane.add(name, comp);
   }

   public void setUpdater(DataRendererUpdater updater) {
      this.updater = updater;

   }

   public void setIcon(String id, Icon icon) {
      setIcon(id, icon, null);
   }

   public void setIcon(String id, Icon icon, String toolTipText) {
      JLabel label = getLabel(id, true);

      label.setIcon(icon);
      if (toolTipText != null)
         label.setToolTipText(toolTipText);

   }

   private JLabel getLabel(String id, boolean forceNewLabelCreation) {
      JLabel label = (JLabel) iconsMap.get(id);
      if (label == null && forceNewLabelCreation) {
         label = new JLabel();
         iconsMap.put(id, label);
         contentPane.add(label);
      }
      return label;
   }

   public void removeIcon(String iconKey) {
      if (!iconsMap.containsKey(iconKey))
         return;
      JLabel label = getLabel(iconKey, false);
      contentPane.remove(label);
      iconsMap.remove(iconKey);
   }

   public static DataRenderer buildDefaultDataRenderer(final int maxTextLength) {

      DefaultDataRenderer renderer = new DefaultDataRenderer(new JPanel());
      final JLabel label = new JLabel();
      label.setFont(label.getFont().deriveFont(Font.BOLD, 12));
      renderer.add(label);

      renderer.setUpdater(new DataRendererUpdater() {
         public void updateFor(Item item) {
            String text = (String) item.get(item.getMainAttribute());
            text = SwingUtil.clipText(label, text, maxTextLength);
            label.setText(text);
         }
      });

      return renderer;

   }

}