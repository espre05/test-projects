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

package net.mcube.util.xml;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLUTil {

	public static Document createDocument(BufferedReader reader) {
		InputSource inputSource = new InputSource(reader);
		
		return createDocument(inputSource);
	}

	private static Document createDocument(InputSource inputSource) {
		try {		
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//			factory.setCoalescing(true);
			DocumentBuilder db = factory.newDocumentBuilder();
			Document d = db.parse(inputSource);

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

	static Document createDocument(String value) {
		InputSource inputSource = new InputSource(value);
		return createDocument(inputSource);
	}

	static void writeToFile(BufferedReader reader, String filename) throws IOException {
		BufferedWriter os = new BufferedWriter(new FileWriter(filename));
		String line = reader.readLine();
		while (line != null) {
			os.write(line);
			line = reader.readLine();
		}
		os.close();
	}

	static void print(BufferedReader reader) throws IOException {
		String line = reader.readLine();
		while (line != null) {
			System.out.println(line);
			line = reader.readLine();
		}
	}

}
