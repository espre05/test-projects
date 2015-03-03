/**
 * Copyright (C) 2003-2008 Daniele Dellafiore <daniele.dellafiore@gmail.com>
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package old;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

import net.della.stuff.generic.event.Event;
import net.della.stuff.generic.event.EventFactory;
import net.mcube.ApplicationEvents;
import net.mcube.extensions.album.AlbumExtension;
import net.mcube.extensions.externalPlayer.ExternalPlayerExtension;
import net.mcube.extensions.songs.Song;
import net.mcube.extensions.songs.SongAttributes;
import net.mcube.library.LibraryImpl;
import net.mcube.util.Delayer;
import net.mcube.util.Validator;
import net.mcube.util.query.QueryList;

import org.flexdock.docking.DockingManager;
import org.jdesktop.swingworker.SwingWorker;


import romainguy.cdshelf.CDShelf;
import romainguy.cdshelf.GradientPanel;
import romainguy.cdshelf.StackLayout;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.event.ListEvent;
import ca.odell.glazedlists.event.ListEventListener;
import della.application.application.Application;
import della.application.application.Extension;
import della.application.datatypes.Item;
import della.application.gui.ApplicationWindow;
import della.application.gui.ViewChangeListener;
import della.application.persistence.BasicLibrary;
import della.docking.DockableAbstractView;
import della.swaf.extensions.gui.AbstractCellListView;
import della.swaf.extensions.gui.AbstractListView;
import della.util.Executor;
import della.util.MathUtil;
import della.util.background.TaskListenerFactory;

public class CDShelfExtension implements Extension {

	private final class DefaultListView extends AbstractListView {
		public DefaultListView(EventList albumList) {
			setSourceEventList(albumList);
		}

		public InputMap getInputMap() {
			// TODO Auto-generated method stub
			return null;
		}

		public ActionMap getActionMap() {
			// TODO Auto-generated method stub
			return null;
		}

		protected void handleRightClick(MouseEvent e) {
			// TODO Auto-generated method stub
		
		}

		public void addChangeListener(ViewChangeListener listener) {
			// TODO Auto-generated method stub
		
		}

      protected int rowAtPoint(MouseEvent e) {
         return 0;
      }

      public int rowAtPoint(DropTargetDropEvent evt) {
         return 0;
      }

      public void centerOnSelection() {
      }

      public void scroll(int oldIndex, int newIndex) {
      }

      protected void build(SwitcherTableCellRenderer cellRenderer, EventList list) {
         build(list);
      }

      protected void build(EventList list) {
      }
	}

	private class BindedAction implements ActionListener {

		private JTextField field;

		private Validator validator;

		private Executor executor;

		private BindedAction(JTextField field, Validator validator, Executor executor) {
			super();
			this.field = field;
			this.validator = validator;
			this.executor = executor;
		}

		public void actionPerformed(ActionEvent e) {
			String text = field.getText();
			if (validator.validate(text))
				executor.execute(text);
			else {
				// JOptionPane.show validator.getError();
			}
		}

	}

	private final class ShelfUpdater extends Delayer implements ViewChangeListener, ListEventListener {
		private final CDShelf shelf;

		private ShelfUpdater(int delay, CDShelf shelf) {
			super(delay);
			this.shelf = shelf;
		}

		protected void refresh() {
			AbstractCellListView albumView = (AbstractCellListView) application.getWindow()
					.getView(AlbumExtension.VIEW_ID);
			shelf.loadAvatars(albumView.getDisplayedList());
		}

		public void viewContentChanged() {
			if (shelfView.isEnabled())
				handleUpdate();
		}

		public void listChanged(ListEvent listChanges) {
			viewContentChanged();
		}
	}

	private Application application;

	private AbstractListView shelfView;

	public void init(Application application) {
		this.application = application;
		final CDShelf shelf = new CDShelf();
		ApplicationWindow mainWindow = application.getWindow();
		final AbstractCellListView albumView = (AbstractCellListView) mainWindow.getView(AlbumExtension.VIEW_ID);
		final ShelfUpdater shelfUpdater = new ShelfUpdater(500, shelf);
//		albumView.addChangeListener(shelfUpdater);

		LibraryImpl library = (LibraryImpl) BasicLibrary.getDefault();
		final QueryList albumList = library.getQueryResponse(SongAttributes.ALBUM);
		
		albumView.addListEventListener(new ListEventListener() {
		
			public void listChanged(ListEvent listChanges) {
				int size = albumView.getDisplayedList().size();				
				double delay = Math.log10(size);
				if (delay < 1)
					delay = 1;
				shelfUpdater.setDelay((int) (delay*12));
			}		
		});
		albumView.addListEventListener(shelfUpdater);

		shelfView = new DefaultListView(albumList);
		
		JPanel shelfStackPanel = new JPanel();
		shelfStackPanel.setLayout(new StackLayout());
		shelfStackPanel.add(new GradientPanel(), StackLayout.BOTTOM);
		shelfStackPanel.add(shelf, StackLayout.TOP);
		JPanel mainPanel = new JPanel(new BorderLayout());
		JProgressBar progressBar = new JProgressBar();

		mainPanel.add(shelfStackPanel);
		mainPanel.add(progressBar, BorderLayout.SOUTH);
		progressBar.setBackground(Color.BLACK);

		shelfView.setId(getID());
		shelfView.setContent(mainPanel);
		mainWindow.registerView(getID(), shelfView);

		// final JTextField amountTextField = new JTextField(shelf.getAmount() +
		// "");
		// amountTextField.addActionListener(new BindedAction(amountTextField,
		// createAmountValidator(), createAmountExecutor(shelf)));
		// shelfView.addToolbarComponent(amountTextField);

		shelfView.addToolbarComponent(new JLabel("number of CDs: "));
		final JTextField totalTextfield = new JTextField(shelf.getAvatarsToLoad() + "");
		totalTextfield.addActionListener(new BindedAction(totalTextfield, createTotalValidator(),
				createTotalExecutor(shelf)));
		shelfView.addToolbarComponent(totalTextfield);

		shelf.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {
				if (e.getClickCount() == 2) {
					playSelectedAlbum(shelf, albumView, shelfView);
				}
			}
		});

		shelf.addTaskListener(TaskListenerFactory.createProgressBarListener(progressBar));
		shelf.addTaskListener(new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent evt) {
				if ("state".equals(evt.getPropertyName())) {
					Object newValue = evt.getNewValue();
					if (SwingWorker.StateValue.STARTED.equals(newValue)) {
						// infiniteProgressPanel.start();
					}
					if (SwingWorker.StateValue.DONE.equals(newValue)) {

						if (shelf.countAvatars() > 0) {
							while (shelf.isExecutingPainting())
								try {
									Thread.sleep(50);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							shelf.setAvatarIndex(0);
							shelf.startFader();
						}
						shelf.setLoading(false);
						shelf.setLoadingDone(true);
						// infiniteProgressPanel.stop();
					}
				}

			}

		});

	}

	public boolean isEnabled() {
		return DockingManager.isDocked(shelfView.getDockableComponent());
	}

	private Executor createAmountExecutor(final CDShelf shelf) {
		return new Executor() {

			public void execute(String text) {
				shelf.setAmount(Integer.parseInt(text));
			}
		};
	}

	private Validator createAmountValidator() {
		return new Validator() {

			public boolean validate(String text) {
				if (MathUtil.isInteger(text)) {
					int number = Integer.parseInt(text);
					if (number < 9 && number > 3)
						return true;
				}
				return false;
			}

		};
	}

	private Executor createTotalExecutor(final CDShelf shelf) {
		return new Executor() {

			public void execute(String text) {
				shelf.setAvatarsToLoad(Integer.parseInt(text));
			}
		};
	}

	private Validator createTotalValidator() {
		return new Validator() {

			public boolean validate(String text) {
				if (MathUtil.isInteger(text)) {
					int number = Integer.parseInt(text);
					if (number < 50 && number > 10)
						return true;
				}
				return false;
			}

		};
	}

	public String getID() {
		return "cd shelf";
	}

	void playSelectedAlbum(final CDShelf shelf, final AbstractCellListView albumView,
			final DockableAbstractView shelfView) {
		int avatarIndex = shelf.getAvatarIndex();
		Item item = (Item) albumView.getDisplayedList().get(avatarIndex);
		Event event = EventFactory.createEvent(shelfView, ApplicationEvents.PLAY_REQUEST);
		ArrayList songsList = new ArrayList();
		for (Iterator it = item.childIterator(); it.hasNext();) {
			Song song = (Song) it.next();
			songsList.add(song.getPath());
		}
		event.set(ExternalPlayerExtension.SONGS_TO_PLAY, songsList);
		fireEvent(event);
	}

	public void fireEvent(Event event) {
		application.fireEvent(event);
	}

}
