package della.swaf.extensions.gui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.*;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import jin.collection.core.Iter;
import jin.collection.core.Operation;
import net.della.mplatform.application.datatypes.Item;
import net.della.mplatform.application.gui.structure.View;
import net.della.mplatform.application.gui.structure.ViewChangeListener;
import net.della.mplatform.docking.DockableAbstractView;
import net.della.stuff.gui.dnd.DropHandler;
import net.della.stuff.gui.dnd.transfer.FileTransferHandler;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;
import della.swaf.extensions.gui.dnd.ListViewItemDragger;
import della.swaf.extensions.util.glazedlists.CustomFilter;

public abstract class AbstractListView extends DockableAbstractView {

	private DragSource dragSource;
	private ListSelectionModel selectionModel;
	private ComplexList dataStructure;
	protected DropHandler dropHandler;
	protected Map comparators;

	public AbstractListView() {
		comparators = new HashMap();
	}

	public final void removeCustomFilter(CustomFilter filter, boolean forceRefresh) {
		dataStructure.removeCustomFilter(filter, forceRefresh);
	}

	public void removeCustomFilter(CustomFilter filter) {
		dataStructure.removeCustomFilter(filter);
	}

	public void addCustomFilter(CustomFilter filter) {
		dataStructure.addCustomFilter(filter);
	}

	protected boolean hasFilters() {
		return dataStructure.hasFilters();
	}

	public void addCustomFilter(CustomFilter filter, boolean forceRefresh) {
		dataStructure.addCustomFilter(filter, forceRefresh);
	}

	protected final ListSelectionModel initSelectionList() {
		selectionModel = dataStructure.createSelectionModel();
		dataStructure.addSelectionEventListener(new ListEventListener() {
			public void listChanged(ListEvent listChanges) {
				firePropertyChange(new PropertyChangeEvent(this, View.SELECTION_CHANGED, null,
						getSelection()));
			}
		});
		return selectionModel;
	}

	public List<? extends Item> getSelection() {
		return dataStructure.getSelection();
	}

	public Item getSelected() {
		return dataStructure.getSelected();
	}

	void enableCenterSelectionWithRightClick() {
		getMainComponent().addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3)
					handleRightClick(e);
			}
		});
	}

	public void addListSelectionListener(ListSelectionListener listener) {
		getSelectionModel().addListSelectionListener(listener);

	}

	protected ListSelectionModel getSelectionModel() {
		return selectionModel;
	}

	protected final List getSourceList() {
		return dataStructure.getHigherList();
	}

	public void removeSelection() {
		getSelectionModel().clearSelection();
	}

	public List getDisplayedList() {
		return dataStructure.actual();
	}

	/**
	 * @param childs
	 */
	protected final void addAll(List childs) {
		dataStructure.addAll(childs);
	}

	/**
	 * 
	 */
	protected final void clear() {
		dataStructure.clear();
	}

	public abstract void addChangeListener(final ViewChangeListener listener);

	public void associate(List itemList, int index) {
		final Item targetItem = this.itemAt(index);
		Iter.forEach(itemList, new Operation() {

			public void execute(Object element) {
				Item item = (Item) element;
				targetItem.addChild(item);
			}
		});
	}

	protected Item itemAt(int index) {
		return dataStructure.get(index);
	}

	public String getComparatorName() {
		return dataStructure.getComparatorName();
	}

	public void changeComparator(Comparator comparator) {
		dataStructure.changeComparator(comparator);
	}

	public int numberOfElements() {
		return dataStructure.size();
	}

	public void setSelectionInterval(int selectedRowIndex, int selectedRowIndex2) {
		getSelectionModel().setSelectionInterval(selectedRowIndex, selectedRowIndex2);
	}

	public void selectRow(int rowIndex) {
		setSelectionInterval(rowIndex, rowIndex);
	}

	public void addSelectionListener(ListSelectionListener listener) {
		getSelectionModel().addListSelectionListener(listener);
	}

	public void selectAll() {
		setSelectionInterval(0, getDisplayedList().size() - 1);
	}

	public void addTextFilter(String propTags) {
		dataStructure.addTextFilter(propTags);
	}

	public void setDataStructure(ComplexList complexList) {
		this.dataStructure = complexList;
	}

	protected void enableDragAndDrop() {
		getMainComponent().setTransferHandler(new FileTransferHandler());
		dragSource = new DragSource();
		new ListViewItemDragger(this, dragSource, getMainComponent());

		DropTarget target = new DropTarget(getMainComponent(), new FileDropTargetListener());
		target.setDefaultActions(DnDConstants.ACTION_COPY);
	}

	public void setDropHandler(DropHandler dropHandler) {
		this.dropHandler = dropHandler;
	}

	protected abstract int rowAtPoint(MouseEvent e);

	public abstract int rowAtPoint(DropTargetDropEvent evt);

	public void useComparator(String comparatorName) {
		changeComparator((Comparator) comparators.get(comparatorName));
		// album.storePrefs(AlbumExtension.COMPARATOR_NAME);
	}

	public void addComparator(String id, Comparator c) {
		comparators.put(id, c);
	}

	public abstract void centerOnSelection();

	public abstract void scroll(int oldIndex, int newIndex);

	protected final void handleRightClick(MouseEvent e) {
		if (getSelection().size() > 1)
			return;
		int row = rowAtPoint(e);
		setSelectionInterval(row, row);
		centerOnSelection();
	}

	public void enableTextFiltering(TextComponentMatcherEditor textComponentMatcherEditor) {
		dataStructure.setTextMatcher(textComponentMatcherEditor);
	}

	public abstract void build(EventList list);

	final class FileDropTargetListener extends DropTargetAdapter {

		public void drop(DropTargetDropEvent evt) {
			try {
				Transferable t = evt.getTransferable();
				if (t.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
					evt.acceptDrop(DnDConstants.ACTION_COPY);
					List folderList = (List) t.getTransferData(DataFlavor.javaFileListFlavor);
					evt.getDropTargetContext().dropComplete(true);
					int rowAtPoint = rowAtPoint(evt);
					dropHandler.handleDrop(folderList, rowAtPoint);
				} else {
					evt.rejectDrop();
				}
			} catch (IOException e) {
				evt.rejectDrop();
			} catch (UnsupportedFlavorException e) {
				evt.rejectDrop();
			}
		}

	}

	protected ComplexList getDataStructure() {
		return dataStructure;
	}

}
