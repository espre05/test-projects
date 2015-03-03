package della.swaf.extensions.gui;

import javax.swing.JPanel;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;

import com.sourcesense.stuff.util.NullComparator;

import della.swaf.extensions.util.glazedlists.BasicTableFormat;
import della.swaf.gui.renderer.DataRenderer;

public class ListViewBuilder {

   private DataRenderer dataRenderer;
   protected TableView view;

   public ListViewBuilder() {
      dataRenderer = new DefaultDataRenderer(new JPanel());
   }

   public TableView create(String viewId, EventList dataList,
         TextComponentMatcherEditor textComponentMatcherEditor) {
      view = new TableView();
      view.setId(viewId);
      view.setName(viewId);
      view.enablePopupForMain();
      view.enableCenterSelectionWithRightClick();
      view.enableDragAndDrop();

      view.setTableHeader(null);
      SwitcherTableCellRenderer panelTableCellRenderer = new SwitcherTableCellRenderer(dataRenderer);
      view.addListSelectionListener(panelTableCellRenderer);
      view.setRowHeight(30);

      FilterList textFilteredList = new FilterList(dataList, textComponentMatcherEditor);
      ComplexList dataStructure = new ComplexList(textFilteredList, new NullComparator());
      dataStructure.setTextMatcher(textComponentMatcherEditor);
      view.setDataStructure(dataStructure);
      view.setTableModel(dataStructure.createTableModel(new BasicTableFormat(viewId)));
      view.enableTypeAhed();

      view.setCellRenderer(panelTableCellRenderer);

      return view;
   }

   public void setDataRenderer(DataRenderer dataRenderer) {
      this.dataRenderer = dataRenderer;
   }

}
