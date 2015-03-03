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

/*
 * Created on 7-ago-2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

package net.mcube.datatypes;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.della.stuff.generic.file.FileUtils;



/**
 * @author Daniele
 * 
 */
public class ItemBuilderFactory {

    Map builders;

    private static ItemBuilderFactory instance;

    public static final String DEFAULT = "default";

    private ItemBuilderFactory() {
        builders = new HashMap();
        addBuilder(DEFAULT, new DefaultItemBuilder());
    }

    public DefaultItemBuilder getBuilderFor(File file) {
        String ext = FileUtils.getExtension(file);
        DefaultItemBuilder builder = (DefaultItemBuilder) builders.get(ext);
        if (builder != null)
            return builder;
        return (DefaultItemBuilder) builders.get(DEFAULT);
    }
    
    public DefaultItemBuilder getBuilderFor(String ext) {
        DefaultItemBuilder builder = (DefaultItemBuilder) builders.get(ext);
        if (builder != null)
            return builder;
        return (DefaultItemBuilder) builders.get(DEFAULT);
    }

    /**
     * @param key:
     *            file extension
     */
    public void addBuilder(String key, DefaultItemBuilder builder) {
        builders.put(key, builder);
    }

    public static ItemBuilderFactory getInstance() {
        if (instance == null)
            instance = new ItemBuilderFactory();
        return instance;
    }

}