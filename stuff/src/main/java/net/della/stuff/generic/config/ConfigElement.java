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

import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author trz
 * @version
 */
public class ConfigElement {

	final static String ID = "id";

	protected Node root;

	protected ConfigElement() {
	}

	private ConfigElement(Node root) {
		this.root = root;
	}

	/**
	 * Ritorna il nome di questo tag
	 */
	public String getType() {
		return root.getNodeName();
	}

	/**
	 * Ritorna il valore di questo tag
	 */
	public String getValue() {
		// codice per gestire diversamente il cdata
		// StringBuffer sb = new StringBuffer();
		// for (int i = 0; i < root.getChildNodes().getLength(); i++) {
		// sb.append(root.getChildNodes().item(i).getNodeValue());
		// }
		// return sb.toString();
		if (root.getFirstChild() != null) {
			return root.getFirstChild().getNodeValue();
		}
		return null;
	}

	/**
	 * Ritorna il valore di un sotto tag
	 */
	public String getValue(String name) {

		ConfigElement elem = getElement(name);
		return elem != null ? elem.getValue() : null;
	}

	/**
	 * ritorna un attributo di questo elemento o di uno dei sottoelementi
	 */
	public String getAttribute(String name) {

		SearchStatus status = handleNestedNodes(name);

		if (status.node != null)
			return readAttribute(status.node, status.lastName);

		return null;
	}

	/**
	 * Ritorna un sotto element del tipo indicato
	 */
	public ConfigElement getElement(String tagType) {

		SearchStatus status = handleNestedNodes(tagType);

		Iterator iter = getElementsByType(status.node, status.lastName);
		Node node = (Node) iter.next();

		if (iter.hasNext()) {
			System.out.println("Warning: trovato piu' di un SubElement di tipo " + status.lastName);
		}

		if (node != null)
			return new ConfigElement(node);

		return null;
	}

	public ConfigElement getElementById(String tagType, String id) {

		SearchStatus status = handleNestedNodes(tagType);

		Node node = getElementByTypeAndId(status.node, status.lastName, id);

		if (node != null)
			return new ConfigElement(node);

		return null;
	}

	/**
	 * ritorna i sotto tag del tipo passato
	 */
	public Iterator getElementsByType(String tagType) {

		SearchStatus status = handleNestedNodes(tagType);

		if (status.node != null) {

			Iterator iter = getElementsByType(status.node, status.lastName);

			ArrayList list = new ArrayList();
			while (iter.hasNext()) {
				list.add(new ConfigElement((Node) iter.next()));
			}
			return list.iterator();
		}

		return null;
	}

	/**
	 * ritorna un attributo di questo elemento o di uno dei sottoelementi come
	 * float
	 */
	public boolean getBooleanAttribute(String name) {

		String str = getAttribute(name);
		if (str == null) {
			System.out.println("Warning: attribute " + name + " non trovato sotto "
					+ root.getNodeName() + ": impossibile convertirlo in un boolean");
			return false;
		}
		return Boolean.valueOf(str).booleanValue();
	}

	/**
	 * ritorna un attributo di questo elemento o di uno dei sottoelementi come
	 * float
	 */
	public float getFloatAttribute(String name) {

		String str = getAttribute(name);
		if (str == null) {
			System.out.println("Warning: attribute " + name + " non trovato sotto "
					+ root.getNodeName() + ": impossibile convertirlo in un float");
			return Float.MIN_VALUE;
		}
		return Float.parseFloat(str);
	}

	/**
	 * ritorna i sotto tag
	 */
	public Iterator getElements() {
		return getElementsByType("");
	}

	// -------------------------------

	/**
	 * Esegue una search per trovare il nodo indicato nel nome Se ne trova
	 * percorre tutto il path e scrive in SearchStatus.node il penultimo nodo
	 * Altrimenti mette in SearchStatus.node la root SearchStatus.lastName
	 * contiene l' ultima parte delle stringa
	 */
	private SearchStatus handleNestedNodes(String name) {

		SearchStatus status = new SearchStatus(root, name);

		getNestedNode(status);

		return status;
	}

	private void getNestedNode(SearchStatus status) {

		int idx = status.lastName.indexOf(".");
		if (idx == -1) {
			return;
		}

		String tagType = status.lastName.substring(0, idx);
		Iterator iter = getElementsByType(status.node, tagType);
		if (!iter.hasNext()) {
			System.out.println("Warning: nodo di tipo " + tagType + " non trovato cercando "
					+ status.fullName);

			status.node = null;
			return;
		}

		status.node = (Node) iter.next();
		if (iter.hasNext()) {
			System.out.println("Warning: trovato piu' di un nodo di tipo " + tagType + " cercando "
					+ status.fullName);
		}

		status.lastName = status.lastName.substring(idx + 1);
		getNestedNode(status);
	}

	/**
	 * cerca un figlio del nodo passato di tipo tagType e con l' attributo
	 * "name" col valore name passato
	 */
	private static Node getElementByTypeAndId(Node root, String tagType, String id) {

		// filtrare parametri

		Iterator list = getElementsByType(root, tagType);

		Node node;
		while (list.hasNext()) {

			node = (Node) list.next();

			String attVal = readAttribute(node, ID);

			if (attVal != null && attVal.equals(id)) {
				return node;
			}
		}

		return null;
	}

	/**
	 * cerca i figli del nodo passato di tipo tagType escludendo quelli di tipo
	 * TextNode
	 */
	private static Iterator getElementsByType(Node root, String tagType) {

		ArrayList outList = new ArrayList();

		NodeList list = root.getChildNodes();

		Node node;
		for (int i = 0; i < list.getLength(); i++) {

			node = list.item(i);

			if ((node.getNodeType() != Node.TEXT_NODE)
					&& (tagType.length() == 0 || node.getNodeName().equals(tagType))) {
				outList.add(node);
			}

		}

		return outList.iterator();
	}

	/**
	 * ritorna il valore dell' attributo name sul nodo passato
	 */
	private static String readAttribute(Node node, String name) {

		Node attNode;
		NamedNodeMap map = node.getAttributes();
		if (map != null && (attNode = map.getNamedItem(name)) != null)
			return attNode.getNodeValue();

		return null;
	}

	/**
	 * inner class SerachStatus
	 */
	private static class SearchStatus {

		final String fullName; // la stringa cercata inizialmente (per debug)

		String lastName; // l' ultima parte della stringa

		Node node;

		SearchStatus(Node nodeVal, String fullNameVal) {
			node = nodeVal;
			fullName = fullNameVal;
			lastName = fullNameVal;
		}

	}

	public String toString() {
		return "[" + getType() + "]";
	}

	public Node getNode() {
		return root;
	}

}
