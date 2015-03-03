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
import java.util.Iterator;

import net.della.stuff.generic.config.ConfigElement;
import net.della.stuff.generic.config.Configuration;
import net.mcube.util.xml.SimpleXMLParser;
import net.mcube.util.xml.XMLUTil;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;



public class RYMTest {

	public static void main(String[] args) {
		new RYMTest().getRemoteData();
	}

	private Configuration getRemoteData() {
		HttpClient client = new HttpClient();
		String host = "http://rateyourmusic.com/api/albumsearch";
		PostMethod postMethod = new PostMethod(host);
		postMethod.addParameter("artist", "adam green");
		postMethod.addParameter("album", "gemstones");
		postMethod.addParameter("user", "ildella");
		postMethod.addParameter("userkey", "userkey");
		postMethod.addParameter("appkey", "07CE23B4-9752-4f6a-8AE8-C4E1E583561A");
		try {
			client.executeMethod(postMethod);
			InputStream in = postMethod.getResponseBodyAsStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			Document doc = XMLUTil.createDocument(reader);
			Configuration mainElement = Configuration.getConfiguration(doc);
			ConfigElement next = (ConfigElement) mainElement.getElementsByType("channel.item")
					.next();
			ConfigElement itemElement = mainElement.getElement("channel.item");
			ConfigElement description = itemElement.getElement("description");
			CDATASection cdata = (CDATASection) description.getNode().getFirstChild();
//			Document descrDoc = XMLUTil.createDocument(description.getValue());
			cdata.getChildNodes().item(1);
		
			SimpleXMLParser parser = new SimpleXMLParser();
			Iterator elements = parser.getElements(description.getValue());
			for (; elements.hasNext();) {
				String element = (String) elements.next();
				System.out.println(parser.getValue(element));
			}
			System.out.println(parser.getElementAt(description.getValue(), 7));

			// try {
			// Source source = new DOMSource(doc);
			//	    	    
			// File file = new File("temp2.xml");
			// Result result = new StreamResult(file);
			//	    
			// Transformer xformer =
			// TransformerFactory.newInstance().newTransformer();
			// xformer.transform(source, result);
			// } catch (TransformerConfigurationException e) {
			// } catch (TransformerException e) {
			// }

			postMethod.releaseConnection();
			in.close();
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
