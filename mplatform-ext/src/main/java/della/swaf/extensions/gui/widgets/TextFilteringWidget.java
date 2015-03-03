package della.swaf.extensions.gui.widgets;

import javax.swing.JTextField;

import net.della.mplatform.application.gui.structure.ApplicationWidget;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;
import della.swaf.extensions.gui.TableView;
import della.swaf.extensions.util.glazedlists.PropertyTextFilterator;

public class TextFilteringWidget extends ApplicationWidget {

   public TextFilteringWidget(String id, JTextField textField) {
      super(id, textField);
   }

   public TextComponentMatcherEditor attachToFiltering(String propertyName) {
      JTextField filterEdit = (JTextField) getComponent();
      TextComponentMatcherEditor textComponentMatcherEditor = new TextComponentMatcherEditor(filterEdit,
            new PropertyTextFilterator(propertyName));
      return textComponentMatcherEditor;
   }

   public void bindView(TableView view) {
      view.enableTextFiltering(createTextMatcher());
      view.pack();
   }

   private TextComponentMatcherEditor createTextMatcher() {
      JTextField filterEdit = (JTextField) getComponent();
      TextComponentMatcherEditor textComponentMatcherEditor = new TextComponentMatcherEditor(filterEdit,
            new PropertyTextFilterator());
      return textComponentMatcherEditor;
   }

}
