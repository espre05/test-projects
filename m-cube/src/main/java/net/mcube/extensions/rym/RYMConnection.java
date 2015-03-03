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

package net.mcube.extensions.rym;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.della.stuff.generic.config.Configuration;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



public class RYMConnection {
	
	
	
	private static RYMConnection instance;
	private final String user;
	private final String appkey;
	private final String userkey;
	private final HttpClient client;
	private final String host;
	
	private RYMConnection(String user) {
		this.user = user;
		appkey = "07CE23B4-9752-4f6a-8AE8-C4E1E583561A";
		userkey = "userkey";
		host = "http://rateyourmusic.com/api/albumsearch";
		client = new HttpClient();
	}

	Configuration getRemoteData(String artist, String album) {
		PostMethod postMethod = new PostMethod(host);
		postMethod.addParameter("artist", artist);
		postMethod.addParameter("album", album);
		postMethod.addParameter("user", user);
		postMethod.addParameter(userkey, userkey);
		postMethod.addParameter("appkey", appkey);
		try {
			client.executeMethod(postMethod);
			InputStream in = postMethod.getResponseBodyAsStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			Document doc = createDocument(reader);
			Configuration mainElement = Configuration.getConfiguration(doc);			
			postMethod.releaseConnection();
			in.close();
			try {
				mainElement.getElement("channel.item");	
			} catch (NoSuchElementException e) {
				return null;
			}			
			return mainElement;
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Document createDocument(BufferedReader reader) {
//		String line = reader.readLine();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			Document d = db.parse(new InputSource(reader));
			return d;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	static RYMConnection getDefault(String username) {
		if (instance == null)
			instance = new RYMConnection(username);
		return instance;
	}


}
