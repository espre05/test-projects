package della.gui;

import java.util.ArrayList;
import java.util.Collection;

import ca.odell.glazedlists.GlazedLists;
import della.application.application.*;
import della.application.gui.ApplicationWindow;
import della.docking.application.DockingBootLoader;
import della.swaf.extensions.gui.TableView;
import della.swaf.extensions.gui.ViewBuilder;
import della.swaf.extensions.util.glazedlists.BasicTableFormat;
import della.util.NullComparator;

public class TableApp implements Extension {

	public static void main(String[] args) throws Exception {

		new TableApp().start();
	}

	public void init(Application application) {
		ApplicationWindow window = application.getWindow();
		TableView tableView = new TableView();
		String viewId = "jackrabbit";
		window.registerView(viewId, tableView);
		Collection  dataList = new ArrayList();
		ViewBuilder.initTableView(tableView, GlazedLists
				.eventList(dataList), new BasicTableFormat(viewId),
				new NullComparator(), viewId);
	}

	public String getID() {
		return "default";
	}

	private void start() throws Exception {

		BootLoader bootLoader = new DockingBootLoader();
		DefaultApplication application = (DefaultApplication) bootLoader
				.create();
		application.registerExtension(this);
		bootLoader.init();
		bootLoader.post();

	}

}
