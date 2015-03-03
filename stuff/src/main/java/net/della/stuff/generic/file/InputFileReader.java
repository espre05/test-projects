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

package net.della.stuff.generic.file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class InputFileReader {

	public List load(String filename) {

		List lines = new ArrayList();
		File file = new File(filename);
		if (file.exists())
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = reader.readLine();
				while (line != null) {
					if (line.indexOf(':') == -1) {
						line = line.replaceAll("-", " ");
						line = line.trim();
						lines.add(parseLine(line));
					}
					line = reader.readLine();
				}
				reader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return lines;
	}

	public abstract Object parseLine(String line);
}
