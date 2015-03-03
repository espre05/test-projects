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

package net.mcube.extensions.export;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import net.della.stuff.generic.file.FileUtils;
import net.mcube.extensions.mp3.MP3;



/*
 * Created on 28-dic-2003
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */

class ExportPlaylistAction extends AbstractAction {

	private JFileChooser fc;

	ExportPlaylistAction() {
		super("Export Playlist...");

		fc = new JFileChooser();		
		fc.setApproveButtonToolTipText("Copy all selected files to target directory");
	}

	public void actionPerformed(ActionEvent e) {
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = fc.showDialog(null, "Choose Playlist");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File m3uFile = fc.getSelectedFile();
			Playlist playList = new Playlist();
			try {
				playList.loadFromFile(m3uFile, Playlist.WINAMP_FORMAT);
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
                System.out.println(playList.size());
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			returnVal = fc.showDialog(null, "Copy Files");
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File targetFolder = fc.getSelectedFile();
				Iterator it = playList.iterator();
				while (it.hasNext()) {					
					MP3 mp3 = (MP3)it.next();
					String path = mp3.getPath();
					File oldFile = new File(path);
					
					String newFileName = targetFolder.getPath() + "/" + oldFile.getName();
					File newFile = new File(newFileName);
					
					FileUtils.copy(oldFile, newFile);
				}
			}
		}
	}

}