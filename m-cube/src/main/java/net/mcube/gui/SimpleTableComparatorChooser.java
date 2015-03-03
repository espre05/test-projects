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

package net.mcube.gui;

// the core Glazed Lists packages
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.gui.TableFormat;
import ca.odell.glazedlists.impl.sort.ComparableComparator;
import ca.odell.glazedlists.impl.sort.ComparatorChain;
import ca.odell.glazedlists.impl.sort.ReverseComparator;
import ca.odell.glazedlists.swing.EventTableModel;

/**
 * A TableComparatorChooser is a tool that allows the user to sort a ListTable by clicking
 * on the table's headers. It requires that the ListTable has a SortedList as
 * a source as the sorting on that list is used.
 *
 * <p>The TableComparatorChooser includes custom arrow icons that indicate the sort
 * order. The icons used are chosen based on the current Swing look and feel.
 * Icons are available for the following look and feels: Mac OS X, Metal, Windows.
 *
 * <p>The TableComparatorChooser supports multiple sort strategies for each
 * column, specified by having muliple comparators for each column. This may
 * be useful when you want to sort a single column in either of two ways. For
 * example, when sorting movie names, "The Phantom Menace" may be sorted under
 * "T" for "The", or "P" for "Phantom".
 *
 * <p>The TableComparatorChooser supports sorting multiple columns simultaneously.
 * In this mode, the user clicks a first column to sort by, and then the user
 * clicks subsequent columns. The list is sorted by the first column and ties
 * are broken by the second column.
 *
 * @see <a href="https://glazedlists.dev.java.net/issues/show_bug.cgi?id=4">Issue #4</a>
 * @see <a href="https://glazedlists.dev.java.net/issues/show_bug.cgi?id=31">Issue #31</a>
 *
 * @author <a href="mailto:jesse@odel.on.ca">Jesse Wilson</a>
 */
public final class SimpleTableComparatorChooser extends MouseAdapter {

    /** the table being sorted */
    private JTable table;
    private EventTableModel eventTableModel;

    /** the sorted list to choose the comparators for */
    private SortedList sortedList;

    /** the potentially foreign comparator associated with the sorted list */
    private Comparator sortedListComparator = null;

    /** the columns and their click counts */
    private ColumnClickTracker[] columnClickTrackers;
    /** the first comparator in the comparator chain */
    private int primaryColumn = -1;
    /** an array that contains all columns with non-zero click counts */
    private ArrayList recentlyClickedColumns = new ArrayList();

    /** the sorting style on a column is used for icon choosing */
    private static final int COLUMN_UNSORTED = 0;
    private static final int COLUMN_PRIMARY_SORTED = 1;
    private static final int COLUMN_PRIMARY_SORTED_REVERSE = 2;
    private static final int COLUMN_PRIMARY_SORTED_ALTERNATE = 3;
    private static final int COLUMN_PRIMARY_SORTED_ALTERNATE_REVERSE = 4;
    private static final int COLUMN_SECONDARY_SORTED = 5;
    private static final int COLUMN_SECONDARY_SORTED_REVERSE = 6;
    private static final int COLUMN_SECONDARY_SORTED_ALTERNATE = 7;
    private static final int COLUMN_SECONDARY_SORTED_ALTERNATE_REVERSE = 8;

    /** a map of look and feels to resource paths for icons */
    private static final String resourceRoot = "resources";
    private static final String defaultResourcePath = "aqua";
    private static final Map lookAndFeelResourcePathMap = new HashMap();
    static {
        lookAndFeelResourcePathMap.put("Mac OS X Aqua", "aqua");
        lookAndFeelResourcePathMap.put("Metal/Steel", "metal");
        lookAndFeelResourcePathMap.put("Metal/Ocean", "ocean");
        lookAndFeelResourcePathMap.put("Windows", "windows");
    }

    /** the icons to use for indicating sort order */
    private static Icon[] icons = new Icon[] { null, null, null, null, null, null, null, null, null };
    private static String[] iconFileNames = new String[] {
        "unsorted.png", "primary_sorted.png", "primary_sorted_reverse.png",
        "primary_sorted_alternate.png", "primary_sorted_alternate_reverse.png",
        "secondary_sorted.png", "secondary_sorted_reverse.png",
        "secondary_sorted_alternate.png", "secondary_sorted_alternate_reverse.png"
    };
    /** load the icons at classloading time */
    static {
        loadIcons();
    }

    /** whether to support sorting on single or multiple columns */
    private boolean multipleColumnSort;

    /**
     * Creates a new TableComparatorChooser that responds to clicks
     * on the specified table and uses them to sort the specified list.
     *
     * @param table the table with headers that can be clicked on.
     * @param sortedList the sorted list to update.
     * @param multipleColumnSort <code>true</code> to sort by multiple columns
     *      at a time, or <code>false</code> to sort by a single column. Although
     *      sorting by multiple columns is more powerful, the user interface is
     *      not as simple and this strategy should only be used where necessary.
     */
    public SimpleTableComparatorChooser(JTable table, SortedList sortedList, boolean multipleColumnSort) {
        this.table = table;
        this.sortedList = sortedList;
        this.multipleColumnSort = multipleColumnSort;

        // get the table model from the table
        try {
            eventTableModel = (EventTableModel)table.getModel();
        } catch(ClassCastException e) {
            throw new IllegalArgumentException("Can not apply TableComparatorChooser to a table whose table model is not an EventTableModel");
        }

        // set up the column click listeners
        rebuildColumns();

        // set the table header
        //table.getTableHeader().setDefaultRenderer(new SortArrowHeaderRenderer());

        // listen for events on the specified table
        //table.setColumnSelectionAllowed(false);
        //table.getTableHeader().addMouseListener(this);
    }

    /**
     * When the column model is changed, this resets the column clicks and
     * comparator list for each column.
     */
    private void rebuildColumns() {
        // build the column click managers
        columnClickTrackers = new ColumnClickTracker[table.getColumnCount()];
        for(int i = 0; i < columnClickTrackers.length; i++) {
            columnClickTrackers[i] = new ColumnClickTracker(i);
        }
        primaryColumn = -1;
        recentlyClickedColumns.clear();
    }


    /**
     * Gets the list of comparators for the specified column. The user is
     * free to add comparators to this list or clear the list if the specified
     * column cannot be sorted.
     */
    public List getComparatorsForColumn(int column) {
        return columnClickTrackers[column].getComparators();
    }

    /**
     * When the mouse is clicked, this selects the next comparator in
     * sequence for the specified table. This will re-sort the table
     * by a new criterea.
     *
     * This code is based on the Java Tutorial's TableSorter
     * @see <a href="http://java.sun.com/docs/books/tutorial/uiswing/components/table.html#sorting">The Java Tutorial</a>
     */
    public void _mouseClicked(MouseEvent e) {
        TableColumnModel columnModel = table.getColumnModel();
        int viewColumn = columnModel.getColumnIndexAtX(e.getX());
        int column = table.convertColumnIndexToModel(viewColumn);
        int clicks = e.getClickCount();
        if(clicks >= 1 && column != -1) {
            columnClicked(column, clicks);
        }
    }
    
    public void mouseClicked(MouseEvent e) {
        int column = table.convertColumnIndexToModel(0);
        int clicks = e.getClickCount();
        if(clicks >= 1 && column != -1) {
            columnClicked(column, clicks);
        }
    }
    
    /**
     * Get the columns that the TableComparatorChooser is sorting by.
     *
     * @return a List of Integers. The first Integer is the primary sorting column,
     *      the second is the secondary, etc. This list may be empty but never null.
     */
    public List getSortingColumns() {
        List sortingColumns = new ArrayList();
        for(int c = 0; c < recentlyClickedColumns.size(); c++) {
            ColumnClickTracker clickedColumn = (ColumnClickTracker)recentlyClickedColumns.get(c);
            sortingColumns.add(new Integer(clickedColumn.getColumn()));
        }
        return sortingColumns;
    }
    
    /**
     * Gets the index comparator in use for the specified column. This comparator
     * may be retrieved using {@link #getComparatorsForColumn(int)}.
     *
     * @return the comparator index for the specified column, or -1 if that column
     *      is not being used to sort.
     */
    public int getColumnComparatorIndex(int column) {
        return columnClickTrackers[column].getComparatorIndex();
    }
    
    /**
     * Gets whether the comparator in use for the specified column is reverse.
     */
    public boolean isColumnReverse(int column) {
        return columnClickTrackers[column].isReverse();
    }

    /**
     * Handle a column being clicked by sorting that column.
     */
    private void columnClicked(int column, int clicks) {
        System.out.println("column " + column + " clicked");
        ColumnClickTracker currentTracker = columnClickTrackers[column];

        // on a double click, clear the click counts
        if(clicks == 2) {
            for(Iterator i = recentlyClickedColumns.iterator(); i.hasNext(); ) {
                ColumnClickTracker columnClickTracker = (ColumnClickTracker)i.next();
                columnClickTracker.resetClickCount();
            }
            primaryColumn = -1;
            recentlyClickedColumns.clear();
        // if we're only sorting one column at a time, clear other columns
        } else if(!multipleColumnSort) {
            for(Iterator i = recentlyClickedColumns.iterator(); i.hasNext(); ) {
                ColumnClickTracker columnClickTracker = (ColumnClickTracker)i.next();
                if(columnClickTracker != currentTracker) {
                    columnClickTracker.resetClickCount();
                }
            }
            primaryColumn = -1;
            recentlyClickedColumns.clear();
        }

        // add a click to the newly clicked column if it has any comparators
        if(!currentTracker.getComparators().isEmpty()) {
            currentTracker.addClick();
            if(recentlyClickedColumns.isEmpty()) {
                recentlyClickedColumns.add(currentTracker);
                primaryColumn = column;
            } else if(!recentlyClickedColumns.contains(currentTracker)) {
                recentlyClickedColumns.add(currentTracker);
            }
        }

        // build a new comparator
        if(recentlyClickedColumns.size() > 0) {
            List comparators = new ArrayList();
            for(Iterator i = recentlyClickedColumns.iterator(); i.hasNext(); ) {
                ColumnClickTracker columnClickTracker = (ColumnClickTracker)i.next();
                Comparator comparator = columnClickTracker.getComparator();
                comparators.add(comparator);
            }
            ComparatorChain comparatorChain = new ComparatorChain(comparators);

            // select the new comparator
            sortedListComparator = comparatorChain;
            sortedList.setComparator(comparatorChain);
        }

        // force the table header to redraw itself
        table.getTableHeader().revalidate();
        table.getTableHeader().repaint();
    }

    /**
     * Gets the sorting style currently applied to the specified column.
     */
    private int getSortingStyle(int column) {
        int modelColumn = table.convertColumnIndexToModel(column);
        return columnClickTrackers[modelColumn].getSortingStyle();
    }

    /**
     * A ColumnClickTracker monitors the clicks on a specified column
     * and provides access to the most appropriate comparator for that
     * column.
     */
    private class ColumnClickTracker {

        /** the column for this comparator */
        private int column = 0;
        /** the number of repeated clicks on this column header */
        private int clickCount = 0;
        /** the sequence of comparators for this column */
        private List comparators = new ArrayList();

        /**
         * Creates a new ColumnClickTracker for the specified column.
         */
        public ColumnClickTracker(int column) {
            this.column = column;
            // add a default comparator
            comparators.add(new TableColumnComparator(eventTableModel.getTableFormat(), column));
        }

        /**
         * Adds a single click to this column.
         */
        public void addClick() {
            clickCount++;
        }

        /**
         * Resets the count of clicks on this column.
         */
        public void resetClickCount() {
            clickCount = 0;
        }

        /**
         * Gets the column for this ColumnComparator.
         */
        public int getColumn() {
            return column;
        }

        /**
         * Sets the sort order to be reverse or not.
         */
        public void setReverse(boolean reverse) {
            if(isReverse() != reverse) {
                if(reverse) {
                    clickCount++;
                } else {
                    clickCount--;
                }
            }
        }

        /**
         * Returns true if this column is sorted in reverse order.
         */
        public boolean isReverse() {
            return (clickCount % 2 == 0);
        }

        /**
         * Gets the index of the comparator to use for this column.
         */
        private void setComparatorIndex(int comparatorIndex) {
            assert(comparatorIndex < comparators.size());
            boolean wasReverse = isReverse();
            clickCount = (comparatorIndex * 2) + 1;
            if(!wasReverse) clickCount = clickCount + 1;
        }

        /**
         * Gets the index of the comparator to use for this column.
         */
        private int getComparatorIndex() {
            if(comparators.size() == 0 || clickCount == 0) return -1;
            return ((clickCount-1) / 2) % comparators.size();
        }

        /**
         * Gets the list of comparators for this column.
         */
        public List getComparators() {
            return comparators;
        }

        /**
         * Gets the current best comparator to sort this column.
         */
        public Comparator getComparator() {
            Comparator comparator = (Comparator)comparators.get(getComparatorIndex());
            if(isReverse()) comparator = new ReverseComparator(comparator);
            return comparator;
        }

        /**
         * Gets the sorting style for this column.
         */
        public int getSortingStyle() {
            if(clickCount == 0) return COLUMN_UNSORTED;

            if(column == primaryColumn) {
                if(!isReverse()) {
                    if(getComparatorIndex() == 0) return COLUMN_PRIMARY_SORTED;
                    else return COLUMN_PRIMARY_SORTED_ALTERNATE;
                } else {
                    if(getComparatorIndex() == 0) return COLUMN_PRIMARY_SORTED_REVERSE;
                    else return COLUMN_PRIMARY_SORTED_ALTERNATE_REVERSE;
                }
            } else {
                if(!isReverse()) {
                    if(getComparatorIndex() == 0) return COLUMN_SECONDARY_SORTED;
                    else return COLUMN_SECONDARY_SORTED_ALTERNATE;
                } else {
                    if(getComparatorIndex() == 0) return COLUMN_SECONDARY_SORTED_REVERSE;
                    else return COLUMN_SECONDARY_SORTED_ALTERNATE_REVERSE;
                }
            }
        }
    }

    /**
     * Loads the set of icons used to be consistent with the current UIManager.
     */
    private static void loadIcons() {
        // look up the icon resource path based on the current look and feel
        String lookAndFeelName = UIManager.getLookAndFeel().getName();
        if(lookAndFeelName.equals("Metal")) lookAndFeelName = getMetalTheme();
        String resourcePath = (String)lookAndFeelResourcePathMap.get(lookAndFeelName);
        if(resourcePath == null) resourcePath = defaultResourcePath;

        // use the classloader to look inside this jar file
        ClassLoader jarLoader = SimpleTableComparatorChooser.class.getClassLoader();

        // load each icon as a resource from the source .jar file
        for(int i = 0; i < icons.length; i++) {
            URL iconLocation = jarLoader.getResource(resourceRoot + "/" + resourcePath + "/" + iconFileNames[i]);
            if(iconLocation != null) icons[i] = new ImageIcon(iconLocation);
            else icons[i] = null;
        }
    }
    /**
     * Workaround method to get the metal theme, either "Steel" or "Ocean". This is because the
     * Metal look and feel's getName() property does not distinguish between versions correctly.
     * A bug has been submitted to the Sun Java bug database and will be reviewed. If fixed, we
     * can get rid of this ugly hack method.
     */
    private static String getMetalTheme() {
        try {
            MetalLookAndFeel metalLNF = (MetalLookAndFeel)UIManager.getLookAndFeel();
            Method getCurrentTheme = metalLNF.getClass().getMethod("getCurrentTheme", new Class[0]);
            MetalTheme currentTheme = (MetalTheme)getCurrentTheme.invoke(metalLNF, new Object[0]);
            return "Metal/" + currentTheme.getName();
        } catch(NoSuchMethodException e) {
            // must be Java 1.4 because getCurrentTheme() method does not exist
            // therefore the theme of interest is "Steel"
            return "Metal/Steel";
        } catch(Exception e) {
            e.printStackTrace();
            return "Metal/Steel";
        }
    }

    /**
     * The SortArrowHeaderRenderer simply delegates most of the rendering
     * to the previous renderer, and adds an icon to indicate sorting
     * direction. This eliminates the hassle of setting the border and
     * background colours.
     *
     * <p>This class fails to add indicator arrows on tables where the
     * renderer does not extend DefaultTableCellRenderer.
     */
    class SortArrowHeaderRenderer implements TableCellRenderer {

        /** the renderer to delegate */
        private TableCellRenderer delegateRenderer;

        /** whether we can inject icons into this renderer */
        private boolean iconInjection = false;

        /**
         * Creates a new SortArrowHeaderRenderer that delegates most drawing
         * to the tables current header renderer.
         */
        public SortArrowHeaderRenderer() {
            // find the delegate
            this.delegateRenderer = table.getTableHeader().getDefaultRenderer();

            // determine if we can inject icons into the delegate
            iconInjection = (delegateRenderer instanceof DefaultTableCellRenderer);
        }

        /**
         * Renders the header in the default way but with the addition of an icon.
         */
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
            if(iconInjection) {
                DefaultTableCellRenderer jLabelRenderer = (DefaultTableCellRenderer)delegateRenderer;
                Icon iconToUse = icons[getSortingStyle(column)];
                jLabelRenderer.setIcon(iconToUse);
                jLabelRenderer.setHorizontalTextPosition(jLabelRenderer.LEADING);
                return delegateRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            } else {
                return delegateRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        }
    }
}

/**
 * A comparator that sorts a table by the column that was clicked.
 */
class TableColumnComparator implements Comparator {

    /** the table format knows to map objects to their fields */
    private TableFormat tableFormat;

    /** the field of interest */
    private int column;

    /** comparison is delegated to a ComparableComparator */
    private static ComparableComparator comparableComparator = new ComparableComparator();

    /**
     * Creates a new TableColumnComparator that sorts objects by the specified
     * column using the specified table format.
     */
    public TableColumnComparator(TableFormat tableFormat, int column) {
        this.column = column;
        this.tableFormat = tableFormat;
    }

    /**
     * Compares the two objects, returning a result based on how they compare.
     */
    public int compare(Object alpha, Object beta) {
        Comparable alphaField = (Comparable) tableFormat.getColumnValue(alpha, column);
        Comparable betaField = (Comparable) tableFormat.getColumnValue(beta, column);
        try {
            return comparableComparator.compare(alphaField, betaField);
        // throw a 'nicer' exception if the class does not implement Comparable
        } catch(ClassCastException e) {
            IllegalStateException illegalStateException = new IllegalStateException("TableComparatorChooser can not sort objects \"" + alphaField + "\", \"" + betaField + "\" that do not implement Comparable");
            illegalStateException.initCause(e);
            throw illegalStateException;
        }
    }

    /**
     * Test if this TableColumnComparator is equal to the other specified
     * TableColumnComparator.
     */
    public boolean equals(Object other) {
        if(!(other instanceof TableColumnComparator)) return false;

        TableColumnComparator otherTableColumnComparator = (TableColumnComparator)other;
        if(!otherTableColumnComparator.tableFormat.equals(tableFormat)) return false;
        if(otherTableColumnComparator.column != column) return false;

        return true;
    }
}
