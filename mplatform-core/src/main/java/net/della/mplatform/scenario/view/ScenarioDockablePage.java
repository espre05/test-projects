/**
 * Copyright (C) 2008 Daniele Dellafiore <daniele.dellafiore@gmail.com>
 * 
 * The contents of this file are subject to the Mozilla Public
 * License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.mozilla.org/MPL/MPL-1.1.html
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied.
 * See the License for the specific language governing rights and
 * limitations under the License.
 */

package net.della.mplatform.scenario.view;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.io.IOException;

import javax.swing.*;

import net.della.mplatform.application.gui.structure.AbstractPage;
import net.della.mplatform.application.gui.structure.View;
import net.della.mplatform.docking.DockableAbstractView;
import net.della.mplatform.docking.DockingAdapter;
import net.della.stuff.generic.util.Command;
import net.della.stuff.gui.scenario.*;

import org.flexdock.docking.Dockable;
import org.flexdock.docking.DockingManager;
import org.flexdock.docking.DockingPort;
import org.flexdock.docking.defaults.DefaultDockingPort;
import org.flexdock.docking.event.DockingEvent;
import org.flexdock.docking.state.PersistenceException;

import com.sun.scenario.animation.Clip;
import com.sun.scenario.animation.Composer;
import com.sun.scenario.scenegraph.*;

/**
 * @author Della
 * 
 */
public class ScenarioDockablePage extends AbstractPage {

	private DockingPort dockingPort;
	private NodeFader mainFader;
	private SGGroup rootNode;
	private NodeFader overlayFader;

	public static class ShowOverlayCommand implements Command {

		private final ScenarioDockablePage window;

		public ShowOverlayCommand(ScenarioDockablePage window) {
			this.window = window;
		}

		@Override
		public Object run() {
			window.toggleDialog();
			return null;
		}
	}

	static {
		Composer.register(AffineTransform.class, TransformComposer.class);
	}

	public ScenarioDockablePage() {
		dockingPort = new DefaultDockingPort();
		dockingPort.addDockingListener(new DockingAdapter() {

			public void undockingComplete(DockingEvent evt) {
				storeDockingLayout();
			}

			public void dockingComplete(DockingEvent evt) {
				storeDockingLayout();
			}

		});
		System.out
				.println(dockingPort.getClass().getName() + "@" + Integer.toHexString(hashCode()));

	}

	public void toggleDialog() {
		mainFader.toggle();
		overlayFader.toggle();
	}

	public void addView(View view, boolean floating) {
		super.addView(view, floating);
		DockableAbstractView dockableView = (DockableAbstractView) view;
		DockingManager.display(dockableView.getId());
	}

	public void removeView(View view) {
		super.removeView(view);
		DockableAbstractView dockableView = (DockableAbstractView) view;
		Dockable dockable = DockingManager.getDockable(dockableView.getId());
		DockingManager.close(dockable);
	}

	public JComponent getComponent() {

		rootNode = new SGGroup();
		JSGPanel sgPanel = new JSGPanel();
		sgPanel.setOpaque(true);
		sgPanel.setBackground(Color.BLUE.brighter());
		sgPanel.setScene(rootNode);
		createMainPanel();
		createOverlayPanel();

		return sgPanel;
	}

	private void createMainPanel() {
		JComponent dockingComponent = (JComponent) dockingPort;
		SGComponent dockingNode = new SGComponent();
		dockingNode.setComponent(dockingComponent);

		float minOpacity = .2f;
		float maxOpacity = 1f;
		SGComposite faderNode = new SGComposite();
		faderNode.setOpacity(maxOpacity);
		faderNode.setChild(dockingNode);
		FaderBuilder faderBuilder = new FaderBuilder(500, minOpacity, maxOpacity);
		Clip clipIn = ClipFactory.makeVisible(dockingNode);
		clipIn.addEndAnimation(faderBuilder.fadeIn(faderNode));
		Clip clipOut = faderBuilder.fadeOut(faderNode);
		mainFader = new NodeFader(clipIn, clipOut);
		mainFader.setVisible(true);

		SGComponent fakeNode = new SGComponent();
		JPanel panel = new JPanel();
		panel.add(new JLabel("I am the main panel just over the JSGPanel"));
		fakeNode.setComponent(panel);
		rootNode.add(dockingNode);
	}

	private SGComponent createOverlayPanel() {
		JPanel overlayPanel = new JPanel();
		overlayPanel.add(new JLabel("search: "));
		overlayPanel.add(new JTextField("type text here..."));
		overlayPanel.add(new JButton("Search"));
		overlayPanel.setBackground(Color.BLACK);
		overlayPanel.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.WHITE));

		SGComponent componentNode = new SGComponent();
		componentNode.setComponent(overlayPanel);

		AffineTransform cpScaleLarge = AffineTransform.getTranslateInstance(30, 60);
		cpScaleLarge.scale(1.5d, 1.5d);
		SGTransform componentScaler = SGTransform.createAffine(cpScaleLarge, componentNode);
		Clip componentScaleIn = Clip.create(500, componentScaler, "affine", cpScaleLarge);

		float minOpacity = 0f;
		float maxOpacity = .7f;
		SGComposite faderNode = new SGComposite();
		faderNode.setOpacity(minOpacity);
		faderNode.setChild(componentScaler);
		FaderBuilder faderBuilder = new FaderBuilder(500, minOpacity, maxOpacity);
		Clip clipIn = ClipFactory.makeVisible(componentNode);
		clipIn.addEndAnimation(faderBuilder.fadeIn(faderNode));
		Clip clipOut = faderBuilder.fadeOut(faderNode);
		clipOut.addEndAnimation(ClipFactory.makeNonVisible(componentNode));
		overlayFader = new NodeFader(clipIn, clipOut);

		rootNode.add(faderNode);
		return componentNode;
	}

	public void revalidateAndRepaint() {
		JComponent c = (JComponent) dockingPort;
		c.revalidate();
		c.repaint();
	}

	void storeDockingLayout() {
		try {
			DockingManager.storeLayoutModel();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PersistenceException e) {
			e.printStackTrace();
		}
	}
}
