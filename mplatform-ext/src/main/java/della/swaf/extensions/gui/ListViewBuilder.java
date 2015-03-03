package della.swaf.extensions.gui;

import javax.swing.JPanel;

import net.della.mplatform.gui.renderer.DataRenderer;
import ca.odell.glazedlists.EventList;

public class ListViewBuilder {

   private DataRenderer dataRenderer;
   protected TableView view;

   public ListViewBuilder() {
      dataRenderer = new DefaultDataRenderer(new JPanel());
   }

   public TableView create(String viewId, EventList dataList) {
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

      view.build(dataList);
      view.pack(panelTableCellRenderer);
      return view;
   }

   public void setDataRenderer(DataRenderer dataRenderer) {
      this.dataRenderer = dataRenderer;
   }

}
