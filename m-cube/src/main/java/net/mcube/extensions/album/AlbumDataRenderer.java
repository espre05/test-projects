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
 * Created on 22-set-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package net.mcube.extensions.album;

import java.awt.Color;
import java.awt.Image;
import java.awt.dnd.DragSource;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import net.della.mplatform.application.datatypes.ObservableItem;
import net.della.mplatform.gui.renderer.BasicDataRenderer;
import net.della.stuff.generic.util.MathUtil;
import net.mcube.datatypes.ImageProperty;
import net.mcube.extensions.songs.SongAttributes;
import net.mcube.util.ImageIconScalable;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;


/**
 * @author Daniele
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AlbumDataRenderer extends BasicDataRenderer {

	protected PanelBuilder builder;

	protected CellConstraints cc;

	private JLabel albumLabel;

	private JLabel imageLabel;

	private JLabel thirdRowLabel;

	private JLabel durationLabel;

	private JLabel artistLabel;

	private Color selectionColor;

	private JLabel notificationLabel;

	private int rendererHeight;

	public AlbumDataRenderer() {
		cc = new CellConstraints();
		rendererHeight = 55;
		initPanel();
	}

	private void initPanel() {
		FormLayout layout = new FormLayout("p, 3dlu, p, 3dlu, p", "p, 2dlu, p, 2dlu, p, 2dlu, p");
		selectionColor = new Color(90, 150, 215, 50);
		this.setBackground(selectionColor);
		builder = new PanelBuilder(layout, this);
		builder.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, selectionColor
				.darker(), selectionColor.darker()));
		imageLabel = builder.addLabel("", cc.xywh(1, 1, 1, 5, CellConstraints.DEFAULT,
				CellConstraints.TOP));
		artistLabel = builder.addLabel("", cc.xy(3, 1));
		albumLabel = builder.addLabel("", cc.xy(3, 3));
		thirdRowLabel = builder.addLabel("", cc.xy(3, 5));
		notificationLabel = builder.addLabel("", cc.xy(5, 3));
		DragSource dragSource = new DragSource();
//		albumItemDragger = new AlbumItemDragger(dragSource);
//		dragSource.createDefaultDragGestureRecognizer(imageLabel, DnDConstants.ACTION_COPY,
//				albumItemDragger);
		
		// durationLabel = builder.addLabel("", cc.xy(5, 5));

	}

	public JComponent getComponentFor(final ObservableItem album) {

		artistLabel.setText(album.getString(SongAttributes.ARTIST));

		ImageProperty imageProperty = (ImageProperty) album
				.getProperty(SongAttributes.ALBUM_COVER_FRONT_THUMBNAIL);
		String propertyName = SongAttributes.ALBUM_COVER_FRONT_THUMBNAIL_PATH;
		String imagePath = album.getString(propertyName);
		Image thumbnailImage = null;
		try {
			thumbnailImage = imageProperty.getValue(imagePath);						
		} catch (IllegalArgumentException e) {
//			album.putData(SongAttributes.ALBUM_COVER_FRONT_THUMBNAIL, ImageProperty.DEFAULT_CD_COVER_THUMBNAIL_FILE_NAME);
//			album.update(propertyName);
//			imagePath = album.getString(propertyName);
			imagePath = ImageProperty.DEFAULT_CD_COVER_THUMBNAIL_FILE_NAME;
			thumbnailImage = imageProperty.getValue(imagePath);
		}

		ImageIconScalable imageIcon = new ImageIconScalable(thumbnailImage);
		imageIcon.setScaledSize(50, 50);
		imageLabel.setIcon(imageIcon);
		albumLabel.setText(album.getString(SongAttributes.ALBUM));

		StringBuffer sb = new StringBuffer();
		String year = album.getString(SongAttributes.YEAR);
		sb.append(year);
		String albumType = album.getString(SongAttributes.ALBUM_TYPE);
		if (albumType.length() > 0)
			sb.append(", " + albumType);

		sb.append(", ");
		String length = album.getString(AlbumAttributes.LENGTH);
		String minSec = MathUtil.secondsToMinSec(length);
		String totalTracksNumber = album.getString(SongAttributes.TOTAL_TRACKS);

		sb.append(totalTracksNumber + " tracks,  " + minSec + " min.");

		thirdRowLabel.setText(sb.toString());

		return this;
	}

	public void setSelected(boolean b) {
		if (b)
			builder.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, selectionColor
					.darker(), selectionColor.darker()));
		else
			builder.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, selectionColor
					.brighter(), selectionColor.brighter()));

	}	

	public void setIcon(String id, Icon icon) {
		// TODO Auto-generated method stub

	}

	public void removeIcon(String string) {
		// TODO Auto-generated method stub

	}

	public void setIcon(String id, Icon icon, String toolTipText) {
		// TODO Auto-generated method stub

	}

	public JLabel getNotificationLabel() {
		return notificationLabel;
	}

	public int getRendererHeight() {
		return rendererHeight;
	}

	public void setRendererHeight(int rendererHeight) {
		this.rendererHeight = rendererHeight;
	}

}