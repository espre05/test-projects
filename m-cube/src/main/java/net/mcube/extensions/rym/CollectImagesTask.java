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

/**
 * 
 */
package net.mcube.extensions.rym;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import javax.imageio.ImageIO;

import net.della.mplatform.application.datatypes.ItemSet;
import net.della.mplatform.background.swing.CollectionBackgroundTask;
import net.della.stuff.generic.config.ConfigElement;
import net.della.stuff.generic.config.Configuration;
import net.mcube.datatypes.ImageProperty;
import net.mcube.extensions.songs.SongAttributes;
import net.mcube.library.LibraryImpl;
import net.mcube.util.xml.SimpleXMLParser;

import org.apache.commons.logging.LogFactory;



final class CollectImagesTask extends CollectionBackgroundTask {
	/**
	 * 
	 */
	private final RYMConnection rym;

	private final SimpleXMLParser parser;

	private String artworkFolder;

	CollectImagesTask(LibraryImpl library, RYMConnection rym) {
		super();
		this.rym = rym;
		parser = new SimpleXMLParser();
		String databaseHome = library.getDatabaseHome();
		artworkFolder = library.getPicturesHome();
		File folder = new File(artworkFolder);
		folder.mkdir();
	}

	protected void applyTo(Object singleElement) {
		LogFactory.getLog(this.getClass()).info("fetch image from RYM at " + new Date());
		ItemSet item = (ItemSet) singleElement;
		String artist = item.getString(SongAttributes.ARTIST);
		String album = item.getString(SongAttributes.ALBUM);

		Configuration mainElement = rym.getRemoteData(artist, album);
		if (mainElement == null) {
			LogFactory.getLog(this.getClass()).info(
					"no matches found on RateYourMusic for: " + artist + " - " + album);
			return;
		}
		item.put(RateYourMusicExtension.RYM, "y");
		ConfigElement itemElement = mainElement.getElement("channel.item");
		ConfigElement description = itemElement.getElement("description");
		String xml = description.getValue().trim();
		String albumIdElement = parser.getElement(xml, "albumID").trim();
		String albumId = parser.getValue(albumIdElement);
		String ext = ".jpg";
		String filename = albumId + ext;

		String localCoverPath = artworkFolder + File.separator + filename;
		String localThumbnailPath = artworkFolder + File.separator + "s" + filename;
		
		String thumbnailElement = parser.getElement(xml, "thumbnail");
		if (!"".equals(thumbnailElement)) {
			LogFactory.getLog(this.getClass()).info("saving image: " + localThumbnailPath);
			String remoteURL = parser.getValue(thumbnailElement);
			saveToLibrary(remoteURL, localThumbnailPath);
			item.setData(SongAttributes.ALBUM_COVER_FRONT_THUMBNAIL_PATH, localThumbnailPath, true);
			ImageProperty imageProperty = ImageProperty.getInstance();
//			imageProperty.setUseCache(false);
			item.setData(SongAttributes.ALBUM_COVER_FRONT_THUMBNAIL, imageProperty, true);
		}
		String coverElement = parser.getElement(xml, "image");
		if (!"".equals(coverElement)) {
			LogFactory.getLog(this.getClass()).info("saving image: " + localCoverPath);
			String remoteURL = parser.getValue(coverElement);
			saveToLibrary(remoteURL, localCoverPath);
			item.setData(SongAttributes.ALBUM_COVER_FRONT_PATH, localCoverPath, true);
			item.setData(SongAttributes.ALBUM_COVER_FRONT, ImageProperty.getInstance(), true);
		}

	}

	private void saveToLibrary(String remoteURL, String localPath) {
		BufferedImage image = null;
		try {
			image = getRemoteImage(remoteURL);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (image != null) {
			// TODO: scale rovina l'immagine, prova a salvarle lisce per
			// sicurezza
			// Image scaledThumbnail = scale(thumbnailImage, 50, 50);
			// Image scaledCover = scale(coverImage, 250, 250);

			Image scaledImage = image;
			try {
				writeImageOnDisk((RenderedImage) scaledImage, localPath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return;
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
	}

	private void writeImageOnDisk(RenderedImage image, String localThumbnailPath)
			throws IOException, FileNotFoundException {
		ImageIO.write(image, "jpeg", new BufferedOutputStream(new FileOutputStream(
				localThumbnailPath)));
	}

	private BufferedImage getRemoteImage(String remoteURL) throws MalformedURLException,
			IOException {
		URL url = new URL(remoteURL);
		URLConnection connection = url.openConnection();
		connection.setRequestProperty("http-referer", "http://rateyourmusic.com/api/albumsearch");
		InputStream inputStream = connection.getInputStream();
		BufferedImage image = ImageIO.read(inputStream);
		return image;
	}
}