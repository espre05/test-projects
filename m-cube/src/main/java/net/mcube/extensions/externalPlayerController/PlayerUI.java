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

/*
 * Created on 22-feb-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.mcube.extensions.externalPlayerController;

import javax.swing.*;

import net.della.stuff.generic.util.MathUtil;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;

/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class PlayerUI extends JPanel {

	private static final int MAX_VOLUME = 10;

	private static final int MAX_WINAMP_VALUE = 10;

	private JLabel titleLabel;

	private CellConstraints cc;

	private PanelBuilder panelBuilder;

	private JSlider volumeSlider;

	public PlayerUI(final IMediaPlayer player) {
		titleLabel = new JLabel();

		FormLayout layout = new FormLayout("pref, pref", "pref, pref");
		panelBuilder = new PanelBuilder(layout, this);
		cc = new CellConstraints();
		panelBuilder.add(titleLabel, cc.xy(1, 2));
//		volumeSlider = new JSlider(SwingConstants.VERTICAL, 0, 100, 30);

//		volumeSlider.setValue(0);
		// volumeSlider.setMajorTickSpacing(10);
		// volumeSlider.setMinorTickSpacing(1);
//		volumeSlider.setPaintTicks(true);
//		volumeSlider.addChangeListener(new ChangeListener() {
//
//			public void stateChanged(ChangeEvent e) {
//				if (volumeSlider.getValueIsAdjusting())
//					return;
//				LogFactory.getLog(this.getClass()).info("volume: " + volumeSlider.getValue());
//				player.setVolume(volumeSlider.getValue() / 10);
//			}
//
//		});
//		panelBuilder.add(volumeSlider, cc.xy(2, 1));
	}

	public void setTitle(String songTitle) {
		titleLabel.setText(songTitle);
		titleLabel.repaint();
	}

	public void setVolume(float nativePlayerVolume) {
		int volume = MathUtil.normalize(MAX_VOLUME, (int) nativePlayerVolume, MAX_WINAMP_VALUE);
		volumeSlider.setValue(volume);
	}

	public void setButtonBar(JComponent component) {
		panelBuilder.add(component, cc.xy(1, 1));
	}

}
