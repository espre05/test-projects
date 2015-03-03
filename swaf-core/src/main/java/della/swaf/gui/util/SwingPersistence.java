/*
 * Created on 4-gen-2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package della.swaf.gui.util;

import java.awt.Rectangle;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class SwingPersistence {

	public static void loadColumnModel(JTable table, InputStream inputStream) throws Exception {
		XMLDecoder dec;
		dec = new XMLDecoder(new BufferedInputStream(inputStream));
		try {
			loadColumnSettings(table, dec);
		} catch (Exception e) {
			throw e;
		} finally {
			dec.close();
		}
	}

	private static void loadColumnSettings(JTable table, XMLDecoder dec) {
		Integer n = (Integer) dec.readObject();
		for (int i = 0; i < n.intValue(); i++) {
			TableColumn column = (TableColumn) dec.readObject();
			table.getColumnModel().getColumn(i).setPreferredWidth(column.getPreferredWidth());
			table.getColumnModel().getColumn(i).setWidth(column.getWidth());
		}
	}

	public static void saveColumnModel(JTable table, File file) {
		XMLEncoder enc;
		TableColumnModel columnModel = table.getColumnModel();

		try {
			enc = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file.getPath())));
			enc.writeObject(new Integer(columnModel.getColumnCount()));
			for (int i = 0; i < columnModel.getColumnCount(); i++) {
				TableColumn column = columnModel.getColumn(i);
				enc.writeObject(column);
			}
			enc.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	public static void saveFrameBounds(JFrame newFrame, File file) {
		XMLEncoder enc;
		try {
			enc = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(file.getPath())));
			enc.writeObject(newFrame.getBounds());
			enc.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void loadFrameBounds(JFrame frame, InputStream inputStream) throws Exception {
		XMLDecoder dec;
		dec = new XMLDecoder(new BufferedInputStream(inputStream));
		try {
			loadFrameSettings(frame, dec);
		} catch (Exception e) {
			throw e;
		} finally {
			dec.close();
		}
	}

	private static void loadFrameSettings(JFrame frame, XMLDecoder dec) {
		Rectangle r = (Rectangle) dec.readObject();
		frame.setBounds(r);
	}

	public static void _loadFrameBounds(JFrame newFrame, File file) {
		XMLDecoder dec;
		try {
			dec = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
			Rectangle r = (Rectangle) dec.readObject();
			newFrame.setBounds(r);
			dec.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

}
