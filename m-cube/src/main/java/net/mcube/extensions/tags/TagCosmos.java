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

package net.mcube.extensions.tags;

import java.awt.event.MouseEvent;
import java.io.File;
import java.util.*;
import java.util.concurrent.Executors;

import javax.swing.*;

import jin.collection.core.Criteria;
import jin.collection.core.Iter;
import net.della.mplatform.application.persistence.BasicLibrary;
import net.della.mplatform.background.swing.CollectionBackgroundAction;
import net.della.mplatform.background.swing.SwingBackgroundTask;
import net.della.mplatform.core.datatypes.Item;
import net.della.mplatform.core.datatypes.ItemUtils;
import net.della.mplatform.core.datatypes.ObservableItem;
import net.della.mplatform.gui.AdapterFactory;
import net.della.mplatform.gui.dnd.FileSelection;
import net.della.stuff.generic.util.MathUtil;
import net.mcube.extensions.tags.graph.GraphElement;
import net.mcube.library.LibraryImpl;

import org.apache.commons.logging.LogFactory;



public class TagCosmos {

   private final class DeleteTagAction extends CollectionBackgroundAction {
      private final String text;

      private ObservableItem tagItem;

      private DeleteTagAction(String name, GraphElement element) {
         putValue(Action.NAME, name);
         this.tagItem = element.getAncestorItem();
         this.text = element.getString(element.getMainAttribute());
      }

      protected void execute(ObservableItem item) {
         ItemUtils.removeFromCollection(item, TagsExtension.TAGS, text);
      }

      protected void runTask(SwingBackgroundTask task) {
         Executors.newSingleThreadExecutor().execute(task);
      }

      protected List getCollection() {
         return tagItem.listChilds();
      }

   }

   static final String SELECTED = "selected";

   static final String NORMAL = "normal";

   static final String FRIEND = "friend";

   static final String EXCLUDED = "excluded";

   private JPanel panel;

   private final Map tagLinkMap;

   private final int baseSize;

   private final int maxSize;

   private final LibraryImpl library;

   private int maxShowedTags;

   private final Collection friends;

   private final Map components;

   private final Collection selected;

   private final Collection excluded;

   public TagCosmos() {
      panel = new JPanel();
      tagLinkMap = new HashMap();
      library = (LibraryImpl) BasicLibrary.getDefault();
      baseSize = 18;
      maxSize = 32;
      maxShowedTags = 20;
      friends = new ArrayList();
      selected = new ArrayList();
      excluded = new ArrayList();
      components = new HashMap();
   }

   /**
    * 
    * selez-solo --> rimuovi tutto selez-altri --> rimuovi tutto, aggiungi lui
    * nonselez --> rimuovi tutto, aggiungi lui
    * 
    * @param tagName
    */
   public void setSelection(String tagName) {
      CosmosComponent selectedComponent = (CosmosComponent) components.get(tagName);

      if (selected.size() == 1 && selected.contains(selectedComponent)) {
         clearSelection();
         return;
      }

      clearSelection();

      addToSelection(selectedComponent);

      panel.repaint();

   }

   private void addToSelection(CosmosComponent selectedComponent) {
      String[] relatedIds = selectedComponent.getRelatedIds();
      Collection relatedComponents = getComponentsFromIds(relatedIds);
      switchStatus(relatedComponents, FRIEND);
      friends.addAll(relatedComponents);

      library.addCustomFilter(selectedComponent.getFilter());
      selected.add(selectedComponent);
      switchStatus(selected, SELECTED);
   }

   private Collection getComponentsFromIds(String[] relatedIds) {
      Collection relatedComponents = new ArrayList();
      for (int i = 0; i < relatedIds.length; i++) {
         String id = relatedIds[i];
         Object component = components.get(id);
         if (component == null)
            LogFactory.getLog(this.getClass()).info("no tagCosmosComponent for: " + id);
         else
            relatedComponents.add(component);
      }
      return relatedComponents;
   }

   private void clearSelection() {
      removeSelectedFilters();
      switchStatus(selected, NORMAL);
      selected.clear();
      switchStatus(friends, NORMAL);
      friends.clear();
   }

   private void removeSelectedFilters() {
      for (Object o : selected) {
         CosmosComponent cosmosComponent = (CosmosComponent) o;
         library.removeCustomFilter(cosmosComponent.getFilter());
      }
   }

   public void addToSelection(String tagName) {

      CosmosComponent selectedComponent = (CosmosComponent) components.get(tagName);
      addToSelection(selectedComponent);

      panel.repaint();
   }

   private void switchStatus(Collection col, String newStatus) {
      for (Iterator it = col.iterator(); it.hasNext();) {
         CosmosComponent cosmoComponent = (CosmosComponent) it.next();
         cosmoComponent.switchTo(newStatus);
      }
   }

   JPanel getPanel() {
      return panel;
   }

   protected void removeDeadTags(List newList) {
      // I have to copy elements to ArrayList cause Iter does not seems to
      // support HashMap.KeySet
      Collection oldList = new ArrayList();
      oldList.addAll(tagLinkMap.keySet());
      Collection tagsToRemove = new Iter().filter(oldList, tagsExistsInListCriteria(newList));
      for (Iterator it = tagsToRemove.iterator(); it.hasNext();) {
         removeTag((String) it.next());
      }
      tagLinkMap.keySet().removeAll(tagsToRemove);
   }

   void removeTag(String text) {
      JLabel label = (JLabel) tagLinkMap.get(text);
      panel.remove(label);
   }

   private Criteria tagsExistsInListCriteria(final List queryList) {
      return new Criteria() {

         public boolean match(Object element) {
            for (Iterator iter = queryList.iterator(); iter.hasNext();) {
               ObservableItem item = (ObservableItem) iter.next();
               Object itemValue = item.get(item.getMainAttribute());
               if (element.equals(itemValue))
                  return true;
            }
            return false;
         }
      };
   }

   private void reloadPopulation(List list) {
      if (list.size() == 0)
         return;
      sortList(list);
      int maxFrequency = foundMaxFrequency(list);
      List subListToShow;
      if (list.size() > maxShowedTags)
         subListToShow = list.subList(0, maxShowedTags);
      else
         subListToShow = list;
      for (Iterator it = subListToShow.iterator(); it.hasNext();) {
         Item item = (Item) it.next();
         process((GraphElement) item, maxFrequency);
      }
      panel.revalidate();
      panel.repaint();
   }

   private int foundMaxFrequency(List list) {
      ObservableItem firstItem = (ObservableItem) list.get(0);
      String maxFrequencyString = firstItem.getString(GraphElement.NUMBER_OF_RELATED_ITEMS);
      return Integer.parseInt(maxFrequencyString);
   }

   private void sortList(List list) {
      // occhio che sorto la lista principale, potrebbe essere un problema
      Collections.sort(list, new Comparator() {

         public int compare(Object o1, Object o2) {
            ObservableItem item1 = (ObservableItem) o1;
            ObservableItem item2 = (ObservableItem) o2;
            String relatedNumber1 = item1.getString(GraphElement.NUMBER_OF_RELATED_ITEMS);
            String relatedNumber2 = item2.getString(GraphElement.NUMBER_OF_RELATED_ITEMS);
            return Integer.parseInt(relatedNumber2) - Integer.parseInt(relatedNumber1);
         }

      });
   }

   private void process(final GraphElement graphElement, int maxFrequency) {
      JLabel label;
      final String text = graphElement.getString(graphElement.getMainAttribute());
      String frequency = graphElement.getString(GraphElement.NUMBER_OF_RELATED_ITEMS);
      float rate = MathUtil.normalize(maxSize - baseSize, Integer.parseInt(frequency), maxFrequency);
      if (!tagLinkMap.containsKey(text)) {
         label = new JLabel(text);
         JPopupMenu tagMenu = new JPopupMenu();
         tagMenu.add(new JMenuItem(new DeleteTagAction("Delete Tag", graphElement)));
         label.addMouseListener(AdapterFactory.newShowMenuAdapter(tagMenu, MouseEvent.BUTTON3));
         CosmosComponent newComponent = CosmosComponent.newComponent(graphElement, label);
         String tagName = graphElement.getName();
         components.put(tagName, newComponent);
         new CosmoComponentController(this, tagName, label);
         addTag(text, label);
      } else {
         label = (JLabel) tagLinkMap.get(text);
      }
      label.setFont(label.getFont().deriveFont(baseSize + rate));
   }

   void addTag(String text, JLabel label) {
      tagLinkMap.put(text, label);
      panel.add(label);
   }

   void update(final List list) {
      removeDeadTags(list);
      reloadPopulation(list);
   }

   public void tagSelection(FileSelection selection, String id) {
      for (int i = 0; i < selection.size(); i++) {
         File file = (File) selection.get(i);
         Item item = library.getItemByPath(file.getAbsolutePath());
         CosmosComponent component = (CosmosComponent) components.get(id);
         ObservableItem tagItemSet = component.getItem().getAncestorItem();
         String property = tagItemSet.getMainAttribute();
         String value = tagItemSet.getString(property);
         ItemUtils.addToCollection(item, property, value);
      }
   }

   public void addToExclusion(String tagName) {

      CosmosComponent selectedComponent = (CosmosComponent) components.get(tagName);
      addToExclusion(selectedComponent);

      panel.repaint();
   }

   private void addToExclusion(CosmosComponent selectedComponent) {
      // String[] relatedIds = selectedComponent.getRelatedIds();
      // Collection relatedComponents = getComponentsFromIds(relatedIds);
      // switchStatus(relatedComponents, FRIEND);
      // friends.addAll(relatedComponents);

      library.addCustomFilter(selectedComponent.getExclusionFilter());
      excluded.add(selectedComponent);
      switchStatus(excluded, EXCLUDED);
   }

   public void setExclusion(String tagName) {
      CosmosComponent selectedComponent = (CosmosComponent) components.get(tagName);

      if (excluded.size() == 1 && excluded.contains(selectedComponent)) {
         clearExclusion();
         return;
      }

      clearExclusion();

      addToExclusion(selectedComponent);

      panel.repaint();

   }

   private void clearExclusion() {
      removeExclusionFilters();
      switchStatus(excluded, NORMAL);
      excluded.clear();
   }

   private void removeExclusionFilters() {
      for (Object o : excluded) {
         CosmosComponent cosmosComponent = (CosmosComponent) o;
         library.removeCustomFilter(cosmosComponent.getExclusionFilter());
      }
   }
}
