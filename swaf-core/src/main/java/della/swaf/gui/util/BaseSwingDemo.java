package della.swaf.gui.util;

import java.awt.BorderLayout;

import javax.swing.*;

public abstract class BaseSwingDemo {

   protected JFrame frame;
   private JToolBar toolbar;

   public BaseSwingDemo() {
      initFrame();
   }

   protected JFrame initFrame() {
      frame = new JFrame("");
      frame.getContentPane().setLayout(new BorderLayout());
      frame.setBounds(200, 200, 300, 800);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      toolbar = buildToolBar();
      frame.getContentPane().add(toolbar, BorderLayout.NORTH);
      return frame;
   }

   private JToolBar buildToolBar() {
      JToolBar toolBar = new JToolBar();
      toolBar.setFloatable(false);
      toolBar.putClientProperty("JToolBar.isRollover", Boolean.TRUE);
      return toolBar;
   }

   protected void addButton(Action action) {
      toolbar.add(action);
   }

   protected void addContent(JComponent table) {
      frame.getContentPane().add(table, BorderLayout.CENTER);
   }

   protected void show() {
      frame.setVisible(true);
   }

   protected abstract void start();

}
