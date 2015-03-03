/*
 * Created on 14-dic-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package della.swaf.gui.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SwingUtil {

   public static String clipText(final JLabel label, String text, int max) {
      text = clipStringIfNecessary(label, label.getFontMetrics(label.getFont()), text, max);
      return text;
   }

   public static String clipStringIfNecessary(JComponent c, FontMetrics fm, String string,
         int availTextWidth) {
      if ((string == null) || (string.equals(""))) {
         return "";
      }
      int textWidth = stringWidth(c, fm, string);
      if (textWidth > availTextWidth) {
         return clipString(c, fm, string, availTextWidth);
      }
      return string;
   }

   public static String clipString(JComponent c, FontMetrics fm, String string, int availTextWidth) {
      // c may be null here.
      String clipString = "...";
      int width = stringWidth(c, fm, clipString);
      // NOTE: This does NOT work for surrogate pairs and other fun
      // stuff
      int nChars = 0;
      for (int max = string.length(); nChars < max; nChars++) {
         width += fm.charWidth(string.charAt(nChars));
         if (width > availTextWidth) {
            break;
         }
      }
      string = string.substring(0, nChars) + clipString;
      return string;
   }

   public static int stringWidth(JComponent c, FontMetrics fm, String string) {
      return fm.stringWidth(string);
   }

   public static void locateOnScreenCenter(Component component) {
      Dimension paneSize = component.getSize();
      Dimension screenSize = component.getToolkit().getScreenSize();
      component.setLocation((screenSize.width - paneSize.width) / 2,
            (screenSize.height - paneSize.height) / 2);
   }

   public static FocusAdapter createSelectAllOnEnterBehavior(final JTextComponent filterEdit) {
      return new FocusAdapter() {
         public void focusGained(FocusEvent evt) {
            filterEdit.setSelectionStart(0);
            filterEdit.setSelectionEnd(filterEdit.getText().length());
         }
      };
   }

   public static List listAllComponents(JComponent frame) {
      Component[] file = frame.getComponents();
      List subComponents = new LinkedList();
      if (file != null)
         for (int i = 0; i < file.length; i++) {
            subComponents.add(file[i]);
         }
      return subComponents;
   }

   public static List listSubComponents(JComponent componentToScan, boolean recursive) {
      if (!recursive)
         return listAllComponents(componentToScan);

      List returnList = new LinkedList();
      List foldersToScan = new LinkedList();
      foldersToScan.add(componentToScan);
      while (foldersToScan.size() > 0) {
         Object object = foldersToScan.get(0);
         if (object instanceof JComponent) {
            JComponent folder = (JComponent) object;
            foldersToScan.addAll(listAllComponents(folder));
            returnList.add(folder);
         }
         foldersToScan.remove(0);
      }
      returnList.remove(0);

      return returnList;
   }

   public static void addSelectAllOnFocusBehavior(JTextField filterEdit) {
      filterEdit.addFocusListener(createSelectAllOnEnterBehavior(filterEdit));
   }

}
