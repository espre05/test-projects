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

package net.mcube.extensions.tracksView;

import java.util.LinkedList;

import net.della.mplatform.application.datatypes.ObservableItem;
import net.mcube.extensions.songs.SongAttributes;
import net.mcube.extensions.songs.SongAttributesAliases;

/*
 * Created on 24-dic-2003
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */

/**
 * @author Daniele
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */

public class TracksTableFormat implements PersistentTableFormat {

    protected LinkedList visibleColumns;

    public TracksTableFormat() {
        visibleColumns = new LinkedList();
        createStandardColumns();
    }

    protected void createStandardColumns() {
        //visibleColumns.add(SongAttributes.ARTIST);
        //visibleColumns.add(SongAttributes.ALBUM);
        visibleColumns.add(SongAttributes.TRACK_NUMBER);
        visibleColumns.add(SongAttributes.TITLE);
        visibleColumns.add(SongAttributes.LENGTH_STRING);
        //visibleColumns.add(SongAttributes.YEAR);
        //visibleColumns.add(SongAttributes.GENRE);
    }

    public int getColumnCount() {
        return visibleColumns.size();
    }

    public String getColumnName(int column) {
        String columnId = (String) visibleColumns.get(column);
        return SongAttributesAliases.getInstance().aliasFor(columnId);
    }

    public String getColumnId(int column) {
        String columnId = (String) visibleColumns.get(column);
        return columnId;
    }

    public Object getColumnValue(Object baseObject, int column) {
        ObservableItem item = (ObservableItem) baseObject;
        return item.getString(getColumnId(column));
    }

	public String getPersistenceFilename() {
		return "tracks.xml";
	}

}