package della.swaf.gui.components;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JToolBar;

public interface InternalFrame {

   /**
    * Returns the frame's icon.
    * 
    * @return the frame's icon
    */
   public Icon getFrameIcon();

   /**
    * Sets a new frame icon.
    * 
    * @param newIcon
    *           the icon to be set
    */
   public void setFrameIcon(Icon newIcon);

   /**
    * Returns the frame's title text.
    * 
    * @return String the current title text
    */
   public String getTitle();

   /**
    * Sets a new title text.
    * 
    * @param newText
    *           the title text tp be set
    */
   public void setTitle(String newText);

   /**
    * Returns the current toolbar, null if none has been set before.
    * 
    * @return the current toolbar - if any
    */
   public JToolBar getToolBar();

   /**
    * Sets a new tool bar in the header.
    * 
    * @param newToolBar
    *           the tool bar to be set in the header
    */
   public void setToolBar(JToolBar newToolBar);

   /**
    * Returns the content - null, if none has been set.
    * 
    * @return the current content
    */
   public Component getContent();

   /**
    * Sets a new panel content; replaces any existing content, if existing.
    * 
    * @param newContent
    *           the panel's new content
    */
   public void setContent(Component newContent);

   /**
    * Answers if the panel is currently selected (or in other words active) or
    * not. In the selected state, the header background will be rendered
    * differently.
    * 
    * @return boolean a boolean, where true means the frame is selected
    *         (currently active) and false means it is not
    */
   public boolean isSelected();

   /**
    * This panel draws its title bar differently if it is selected, which may be
    * used to indicate to the user that this panel has the focus, or should get
    * more attention than other simple internal frames.
    * 
    * @param newValue
    *           a boolean, where true means the frame is selected (currently
    *           active) and false means it is not
    */
   public void setSelected(boolean newValue);

   /**
    * Updates the UI. In addition to the superclass behavior, we need to update
    * the header component.
    */
   public void updateUI();

   /**
    * 
    * @deprecated
    */
   public Component getHeader();

   public void revalidateAndRepaint();

}