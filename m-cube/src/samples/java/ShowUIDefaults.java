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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.IconUIResource;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

public class ShowUIDefaults extends JFrame implements ActionListener
{
	JFrame frame;
	JTabbedPane tabbedPane;
	JButton metal;
	JButton windows;
	JButton motif;
	SampleRenderer sampleRenderer;

	public ShowUIDefaults(String title)
	{
		super(title);
		frame = this;

		getContentPane().setLayout( new BorderLayout() );
		tabbedPane = getTabbedPane();
		getContentPane().add( tabbedPane );

		JPanel buttons = new JPanel();
		buttons.setLayout( new GridLayout( 1, 3) );
		getContentPane().add(buttons, BorderLayout.SOUTH);

		metal = new JButton( "Metal" );
		metal.addActionListener( this );
		buttons.add(metal);

		windows = new JButton( "Windows" );
		windows.addActionListener( this );
		buttons.add(windows);

		motif = new JButton( "Motif" );
		motif.addActionListener( this );
		buttons.add(motif);
	}

	public void actionPerformed(ActionEvent e)
	{
		String laf = "";
		Object o = e.getSource();

		if (o == metal)
			laf = "javax.swing.plaf.metal.MetalLookAndFeel";
		else if (o == windows)
			laf = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		else if (o == motif)
			laf = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";

		try
		{
       		UIManager.setLookAndFeel(laf);
		}
		catch (Exception e2)
		{
			System.out.println(e2);
		}

		getContentPane().remove(tabbedPane);
		tabbedPane = getTabbedPane();
		getContentPane().add( tabbedPane );
		SwingUtilities.updateComponentTreeUI(frame);
		frame.pack();
	}

	private JTabbedPane getTabbedPane()
	{
		Map components = new TreeMap();

		UIDefaults defaults = UIManager.getDefaults();

		//  Build of Map of attributes for each component

		for ( Enumeration keys = defaults.keys(); keys.hasMoreElements(); )
		{
			Object key = keys.nextElement();
			Object value = defaults.get( key );

			Map componentMap = getComponentMap( components, key.toString() );

			if ( componentMap != null )
				componentMap.put( key, value );
		}

		JTabbedPane pane = new JTabbedPane( SwingConstants.BOTTOM );
		pane.setPreferredSize( new Dimension( 800, 400 ) );
		addComponentTabs( pane, components );

		return pane;
	}

	private Map getComponentMap( Map components, String key )
	{
		if ( key.startsWith("class") | key.startsWith("javax") )
			return null;

		//  Component name is found before the first "."

		String componentName;

		int pos = key.indexOf( "." );

		if (pos == -1)
			if (key.endsWith( "UI" ) )
				componentName = key.substring( 0, key.length() - 2 );
			else
				componentName = "System Colors";
		else
			componentName = key.substring( 0, pos );

		//  Get the Map for this particular component

		Object componentMap = components.get( componentName );

		if (componentMap == null)
		{
			componentMap = new TreeMap();
			components.put( componentName, componentMap );
		}

		return (Map)componentMap;
	}

	private void addComponentTabs(JTabbedPane pane, Map components)
	{
		sampleRenderer = new SampleRenderer();

		String[ ] colName = {"Key", "Value", "Sample"};
		Set c = components.keySet();

		for (Iterator ci = c.iterator(); ci.hasNext();)
		{
			String component = (String)ci.next();
			Map attributes = (Map)components.get( component );

			Object[ ][ ] rowData = new Object[ attributes.size() ][ 3 ];
			int i = 0;

			Set a = attributes.keySet();

			for(Iterator ai = a.iterator(); ai.hasNext(); i++)

			{
				String attribute = (String)ai.next();
				rowData[ i ] [ 0 ] = attribute;
				Object o = attributes.get(attribute);

				if (o != null)
				{
					rowData[ i ] [ 1 ] = o.toString();
					rowData[ i ] [ 2 ] = "";

					if (o instanceof Font)
						rowData[ i ] [ 2 ] = (Font)o;

					if (o instanceof Color)
						rowData[ i ] [ 2 ] = (Color)o;

					if (o instanceof IconUIResource)
						rowData[ i ] [ 2 ] = (Icon)o;
				}
				else
				{
					rowData[ i ] [ 1 ] = "";
					rowData[ i ] [ 2 ] = "";
				}

			}

			MyTableModel myModel = new MyTableModel( rowData, colName );
			JTable table = new JTable( myModel );
			table.setDefaultRenderer(sampleRenderer.getClass(), sampleRenderer);
			table.getColumnModel().getColumn(0).setPreferredWidth(250);
			table.getColumnModel().getColumn(1).setPreferredWidth(500);
			table.getColumnModel().getColumn(2).setPreferredWidth(50);

			pane.addTab( component, new JScrollPane( table ) );
		}
	}

	class MyTableModel extends AbstractTableModel
	{
		private String[] columnNames;
		private Object[][] rowData;

		public MyTableModel(Object[][] rowData, String[] columnNames)
		{
			this.rowData = rowData;
			this.columnNames = columnNames;
		}

		public int getColumnCount()
		{
			return columnNames.length;
		}

		public int getRowCount()
		{
			return rowData.length;
		}

		public String getColumnName(int col)
		{
			return columnNames[col];
		}

		public Object getValueAt(int row, int col)
		{
			return rowData[row][col];
		}

		public Class getColumnClass(int c)
		{
			Object o;

			if ( c == 2 )
				o = sampleRenderer;
			else
				o = getValueAt(0, c);

			return o.getClass();
		}

		public void setValueAt(Object value, int row, int col)
		{
			rowData[row][col] = value;
			fireTableCellUpdated(row, col);
		}
	}

	class SampleRenderer extends JLabel implements TableCellRenderer
	{
		public SampleRenderer()
		{
			super();
			setHorizontalAlignment( SwingConstants.CENTER );
			setOpaque(true); //MUST do this for background to show up.
		}

		public Component getTableCellRendererComponent(JTable table,
														 Object sample,
														 boolean isSelected,
														 boolean hasFocus,
														 int row,
														 int column)
		{
			setBackground( null );
			setIcon( null );
			setText( "" );

			if ( sample instanceof Color )
			{
				setBackground( (Color)sample );
			}

			if ( sample instanceof Font )
			{
				setText( "Sample" );
				setFont( (Font)sample );
			}

			if ( sample instanceof Icon )
			{
				setIcon( (Icon)sample );
			}

			return this;
		}
	}

	public static void main(String[] args)
	{
		//  Set the Look and Feel

		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) { }

//		UIManager.put("TabbedPane.selected", new Color( 255, 0, 0) );
//		UIManager.put("ScrollBar.width", new Integer(24) );

		// The attributes of each component will be shown on a separate tab

		JFrame f = new ShowUIDefaults( "UI Defaults" );
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
	}

}
