/*
 * Created on 11-gen-2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package della.swaf.gui.components;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import java.util.Map;

import javax.swing.*;

import com.jgoodies.looks.Options;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.windows.WindowsLookAndFeel;
import com.sourcesense.stuff.file.FileUtils;

import della.swaf.gui.Settings;
import della.swaf.util.Command;

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class WidgetFactory {

   private static Map desktopHints;

   {
      Toolkit tk = Toolkit.getDefaultToolkit();
      desktopHints = (Map) (tk.getDesktopProperty("awt.font.desktophints"));
   }

   private static WidgetFactory instance;

   public static JLabel createLabel(String text) {

      JLabel label = new JLabel() {
         @Override
         protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d
                  .setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.addRenderingHints(desktopHints);
            super.paintComponent(g);
         }
      };
      label.setText(text);
      label.setToolTipText(text);
      label.setForeground(Color.BLACK);
      return label;
   }

   public static JMenuItem createItem(Action action, int keyCode) {
      return WidgetFactory.createItem(action, keyCode, 0);
   }

   public static JMenuItem createItem(Action action, int keyCode, int modifiers) {
      JMenuItem item = new JMenuItem(action);
      item.setAccelerator(KeyStroke.getKeyStroke(keyCode, modifiers));
      return item;
   }

   public static JMenuItem createItem(Action action, int keyCode, int modifiers, String text) {
      JMenuItem item = createItem(action, keyCode, modifiers);
      item.setText(text);
      return item;
   }

   public static JMenuItem createItem(ActionListener actionListener) {
      return createItem(actionListener, "");
   }

   public static JMenuItem createItem(ActionListener actionListener, String text) {
      JMenuItem item = new JMenuItem(text);
      item.addActionListener(actionListener);
      return item;
   }

   public static JMenuItem createItem(Action removeAction, int keyCode, String text) {
      JMenuItem menuItem = createItem(removeAction, keyCode);
      menuItem.setText(text);
      return menuItem;
   }

   public static JMenuItem createItem(Action action, char c) {
      JMenuItem item = new JMenuItem(action);
      item.setAccelerator(KeyStroke.getKeyStroke(c));
      return item;
   }

   public static JMenuItem createItem(Action action, char c, int modifiers) {
      JMenuItem item = new JMenuItem(action);
      item.setAccelerator(KeyStroke.getKeyStroke(c, modifiers));
      return item;
   }

   private JFileChooser sharedFolderChooser;
   private JFileChooser sharedFileChooser;

   public JFileChooser createFolderChooser() {
      if (sharedFolderChooser == null) {
         sharedFolderChooser = new JFileChooser(FileUtils.getCurrentFolderAbsolutePath());
         sharedFolderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      }
      return sharedFolderChooser;
   }

   public JFileChooser createFileChooser() {
      if (sharedFileChooser == null) {
         sharedFileChooser = new JFileChooser(FileUtils.getCurrentFolderAbsolutePath());
      }
      return sharedFileChooser;
   }

   /**
    * 
    * @param command
    *           to be executed
    * @param actionName
    *           of the action (that will be used as label in a JButton,
    *           JMenuItem and so on...
    * @return
    */
   public static AbstractAction actionFrom(final Command command, String actionName) {
      AbstractAction action = new AbstractAction() {
         public void actionPerformed(ActionEvent e) {
            command.run();
         }
      };
      action.putValue(Action.NAME, actionName);
      return action;
   }

   public static FileDialog createNativeFileChooser(JFrame parentFrame) {
      FileDialog fileDialog = new FileDialog(parentFrame, "Open File (Native)");
      return fileDialog;
   }

   public static JSplitPane createSplitPane(Component first, Component second, int separationType) {
      JSplitPane splitPane = new JSplitPane(separationType, first, second);
      splitPane.setContinuousLayout(true);
      splitPane.setOneTouchExpandable(false);
      splitPane.setDividerLocation(250);
      splitPane.setDividerSize(2);
      splitPane.setBorder(null);
      splitPane.setBackground(Color.WHITE);
      return splitPane;
   }

   public static JTable createTable() {
      JTable table = new JTable();

      table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
      table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
      table.setShowHorizontalLines(false);
      table.setShowVerticalLines(false);
      table.setShowGrid(false);
      table.getTableHeader().setReorderingAllowed(false);

      Color selectionColor = UIManager.getColor("Table.selectionBackground");
      selectionColor = new Color(selectionColor.getRed(), selectionColor.getGreen(),
            selectionColor.getBlue(), 30);
      table.setSelectionBackground(selectionColor);

      Color foregroundColor = UIManager.getColor("Table.selectionBackground");
      foregroundColor = foregroundColor.darker();
      table.setSelectionForeground(foregroundColor);

      return table;

   }

   /**
    * Builds, configures and returns the toolbar. Requests HeaderStyle,
    * look-specific BorderStyles, and Plastic 3D Hint from Launcher.
    */
   public static JToolBar buildToolBar() {
      JToolBar toolBar = new JToolBar();
      toolBar.setFloatable(false);
      toolBar.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
      Settings settings = Settings.createDefault();
      // Swing
      toolBar.putClientProperty(Options.HEADER_STYLE_KEY, settings.getToolBarHeaderStyle());
      toolBar.putClientProperty(PlasticLookAndFeel.BORDER_STYLE_KEY, settings.getToolBarPlasticBorderStyle());
      toolBar.putClientProperty(WindowsLookAndFeel.BORDER_STYLE_KEY, settings.getToolBarWindowsBorderStyle());
      toolBar.putClientProperty(PlasticLookAndFeel.IS_3D_KEY, settings.getToolBar3DHint());

      return toolBar;
   }

   /**
    * Creates and returns a <code>JButton</code> configured for use in a
    * JToolBar.
    * <p>
    * 
    * This is a simplified method that is overriden by the Looks Demo. The full
    * code uses the JGoodies UI framework's ToolBarButton that better handles
    * platform differences.
    */
   public static AbstractButton _createToolBarButton(String iconName) {
      JButton button = new JButton(readImageIcon(iconName));
      button.setFocusable(false);
      return button;
   }

   /**
    * Creates and returns a <code>JToggleButton</code> configured for use in a
    * JToolBar.
    * <p>
    * 
    * This is a simplified method that is overriden by the Looks Demo. The full
    * code uses the JGoodies UI framework's ToolBarButton that better handles
    * platform differences.
    */
   protected static AbstractButton createToolBarRadioButton(String iconName) {
      JToggleButton button = new JToggleButton(readImageIcon(iconName));
      button.setFocusable(false);
      return button;
   }

   /*
    * Looks up and answers an icon for the specified filename suffix. <p>
    */
   protected static ImageIcon readImageIcon(String filename) {
      URL url = WidgetFactory.class.getClassLoader().getResource("images/" + filename);
      if (url == null)
         return null;
      return new ImageIcon(url);
   }

   public static AbstractButton createToolBarButton(String imagePath, String actionCommand,
         String toolTipText, String altText) {

      AbstractButton button = WidgetFactory.createButton();
      configureButton(imagePath, actionCommand, toolTipText, altText, button);
      return button;
   }

   public static AbstractButton createToggleToolBarButton(String imagePath, String actionCommand,
         String toolTipText, String altText) {

      AbstractButton button = new JToggleButton();
      configureButton(imagePath, actionCommand, toolTipText, altText, button);
      return button;
   }

   private static void configureButton(String imagePath, String actionCommand, String toolTipText,
         String altText, AbstractButton button) {
      button.setFocusable(false);
      button.setActionCommand(actionCommand);
      button.setToolTipText(toolTipText);
      // button.addActionListener(new ToolBarActionListener());
      File file = new File(imagePath);
      if (file.exists()) { // image found
         button.setIcon(new ImageIcon(imagePath, altText));
      } else { // no image found
         button.setText(altText);
         System.out.println("Resource not found: " + imagePath);
      }
   }

   public static AbstractButton createCheckMenuItem(String imagePath, String actionCommand,
         String toolTipText, String altText) {
      AbstractButton button = new JCheckBoxMenuItem();
      configureButton(imagePath, actionCommand, toolTipText, altText, button);
      return button;
   }

   public static JButton createMenuButton(String label, final JPopupMenu popupMenu) {
      final JButton configurationButton = new JButton(label);
      configurationButton.addMouseListener(new MouseAdapter() {
         public void mouseReleased(MouseEvent e) {
            if (e.getButton() == 1) {
               popupMenu.show(e.getComponent(), configurationButton.getX() - 100, configurationButton.getY()
                     + configurationButton.getHeight());
            }
         }
      });
      return configurationButton;
   }

   public static AbstractButton createMenuItem(String imagePath, String actionCommand, String toolTipText,
         String altText) {
      AbstractButton button = new JMenuItem();
      configureButton(imagePath, actionCommand, toolTipText, altText, button);
      return button;
   }

   public static JPanel createLightPanel() {
      return new JPanel() {

         public void paint(Graphics g) {
            // TODO Auto-generated method stub
            // super.paint(g);
         }

         public void update(Graphics g) {
            // TODO Auto-generated method stub
            // super.update(g);
         }

         public void repaint() {
            // TODO Auto-generated method stub
            // super.repaint();
         }

      };
   }

   public static JProgressBar createProgressBar() {
      JProgressBar progressBar = new JProgressBar();
      progressBar.setStringPainted(true);
      progressBar.setString("");
      return progressBar;

   }

   public static JButton createButton(String text) {
      JButton button = createButton();
      button.setText(text);
      return button;
   }

   private static JButton createButton() {
      return new JButton() {
         @Override
         public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.addRenderingHints(desktopHints);
            super.paint(g);
         }
      };
   }

   public static WidgetFactory getInstance() {
      if (instance == null) {
         instance = new WidgetFactory();
      }
      return instance;
   }

   /**
    * @param action
    * @return
    */
   public static JButton createButton(Action action) {

      JButton button = new JButton(action);
      button.setFocusable(false);
      return button;
      // button.setActionCommand(actionCommand);
      // button.setToolTipText(toolTipText);
      // button.addActionListener(new ToolBarActionListener());
      // File file = new File(imagePath);
      // if (file.exists()) { // image found
      // button.setIcon(new ImageIcon(imagePath, altText));
      // } else { // no image found
      // button.setText(altText);
      // System.out.println("Resource not found: " + imagePath);
      // }
   }

   /**
    * @param pauseCommand
    * @param string
    * @param string2
    */
   public static AbstractAction actionFrom(final Command command, final String firstText,
         final String secondText) {
      AbstractAction action = new AbstractAction() {
         public void actionPerformed(ActionEvent e) {
            Boolean b = (Boolean) command.run();
            if (b)
               putValue(Action.NAME, secondText);
            else
               putValue(Action.NAME, firstText);
         }
      };
      action.putValue(Action.NAME, firstText);
      return action;
   }

   /**
    * @param command
    * @param label
    * @return
    */
   public static JButton createButton(Command command, String label) {
      return createButton(actionFrom(command, label));
   }

   /**
    * @param startCommand
    * @param string
    * @return
    */
   public static Component createCheckBox(Command startCommand, String string) {
      JCheckBox checkBox = new JCheckBox(actionFrom(startCommand, string));
      return checkBox;
   }

   public static JTextField createTextField() {
      return new JTextField() {
         @Override
         protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d
                  .setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.addRenderingHints(desktopHints);
            super.paintComponent(g);
         }
      };
   }

}