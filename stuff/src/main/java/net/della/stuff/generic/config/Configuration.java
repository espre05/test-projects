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

package net.della.stuff.generic.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author trz
 * @version
 */
public class Configuration extends ConfigElement {

	static String FILE_NAME = "config.xml";

	private static Configuration instance;

	public static Configuration getConfiguration(Document doc) throws Exception {
		return new Configuration(doc);
	}

	/**
	 * Carica da classpath
	 */
	public static Configuration getDefault() throws RuntimeException {

		return getConfiguration(FILE_NAME);
	}

	/**
	 * Carica da file
	 */
	public static Configuration getFromFile(String file) throws Exception {
		return new Configuration(file, true, false);
	}

	public static Configuration getFromFile(String file, boolean validate) throws Exception {
		return new Configuration(file, true, validate);
	}

	/**
	 * Carica da classpath
	 */
	private static Configuration getConfiguration(String fileName) {

		if (instance == null)
			instance = new Configuration(fileName, true, false);

		return instance;
	}

	/** Creates new Configuration */
	private Configuration(String fileName, boolean file, boolean validate) {

		Document doc;

		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			if (validate)
				dbf.setValidating(true);
			DocumentBuilder db = dbf.newDocumentBuilder();

			if (file) {
				InputStream fis = null;
				try {
					fis = new FileInputStream(fileName);
					doc = db.parse(fis);
				} finally {
					if (fis != null)
						fis.close();
				}
			} else { // resource
				// InputStream is =
				// ClassLoader.getSystemClassLoader().getResourceAsStream(FILE_NAME);
				InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
				doc = db.parse(is);
			}
		} catch (Exception e) {
			throw new RuntimeException("Errore caricando la configurazione da " + fileName, e);
		}

		root = getFirstValidNode(doc.getChildNodes());

	}

	private Configuration(Document doc) throws Exception {

		root = getFirstValidNode(doc.getChildNodes());
	}

	private Node getFirstValidNode(NodeList list) {

		Node firstValidNode = null;

		for (int i = 0; i < list.getLength(); i++) {
			Node currentNode = list.item(i);
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				firstValidNode = currentNode;
				break;
			}
		}
		return firstValidNode;

	}

	/**
	 * @param filename
	 *            TODO
	 */
	private Configuration(String filename) {

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder(); // parserconfexc

			Document doc = db.parse(filename); // saxexception
			root = doc.getFirstChild();

		} catch (ParserConfigurationException e) {
			throw new IllegalStateException("Errore caricando la configurazione: " + e);
		} catch (SAXException e) {
			throw new IllegalStateException("Errore caricando la configurazione: " + e);
		} catch (IOException e) {
			throw new IllegalStateException("Errore caricando la configurazione: " + e);
		}
	}

	public static Configuration getDefaultInFolder(String folderPath) {
		return new Configuration(folderPath + FILE_NAME);
	}

	/**
	 * @param string
	 */
	public static void setDefaultFile(String fileName) {
		FILE_NAME = fileName;
		instance = null;
	}

}
