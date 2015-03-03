package net.della.swaf.jcr;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.jcr.*;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NoSuchNodeTypeException;
import javax.jcr.version.VersionException;

import jin.collection.core.CollectionFactory;
import net.della.mplatform.application.datatypes.Item;
import net.della.mplatform.application.datatypes.ItemAccessException;

import org.apache.jackrabbit.util.ISO9075;

import della.jocker.repo.TableJcrDao;

public class NodeItem implements Item {

   private Node node;

   public NodeItem() {
   }

   public NodeItem(Node node) {
      this.node = node;
   }

   public Iterator childIterator() {
      try {
         return node.getNodes();
      } catch (RepositoryException e) {
         throw new ItemAccessException("Error accessing childs of item: ", e);
      }
   }

   public int countChilds() {
      try {
         return (int) node.getNodes().getSize();
      } catch (RepositoryException e) {
         throw new ItemAccessException("Error accessing childs of item: ", e);
      }
   }

   public Object get(String key) {
      String name = "";
      try {
         name = node.getName();
         return node.getProperty(key);
      } catch (PathNotFoundException e) {
         throw new ItemAccessException("Unable to access property '" + key + "' in node '" + name + "'. "
               + "Maybe the key does not match with a real property inside this Item" + "id: '" + getId()
               + "'. Type: " + getType() + ".", e);
      } catch (RepositoryException e) {
         throw new ItemAccessException("Repository error reading a property from Item: ", e);
      }
   }

   public Item getChild(int index) {
      Node nodeAtIndex = (Node) CollectionFactory.asArrayList(childIterator()).get(index);
      return new NodeItem(nodeAtIndex);
   }

   public List listChilds() {
      return CollectionFactory.asArrayList(childIterator());
   }

   public boolean hasChild(Item child) {
      throw new UnsupportedOperationException("Operation not yet implemented for this implementation");
   }

   public void put(String key, Object value) {
      throw new UnsupportedOperationException(
            "This Item is backed by a JCR Node. Is best to use this as a NodeItem and call one of the specific setProperty");
   }

   public void remove(String key) {
      throw new UnsupportedOperationException("JCR Node backed Item does not support property remove");
   }

   public Property setProperty(String name, long value) {
      try {
         return node.setProperty(name, value);
      } catch (ValueFormatException e) {
         throw new ItemAccessException(e);
      } catch (VersionException e) {
         throw new ItemAccessException(e);
      } catch (LockException e) {
         throw new ItemAccessException(e);
      } catch (ConstraintViolationException e) {
         throw new ItemAccessException(e);
      } catch (RepositoryException e) {
         throw new ItemAccessException(e);
      }
   }

   public void putStrings(String name, String[] values) {
      for (int i = 0; i < values.length; i++) {
         putString(name, values[i]);
      }
   }

   public String getMainAttribute() {
      return TableJcrDao.PERSISTENT_ID;
   }

   public String getString(String key) {
      try {
         String encoded = getProperty(key).getString();
         return ISO9075.decode(encoded);
      } catch (ValueFormatException e) {
         throw new ItemAccessException(e);
      } catch (RepositoryException e) {
         throw new ItemAccessException(e);
      }
   }

   protected Property getProperty(String key) {
      return (Property) this.get(key);
   }

   public Boolean getBoolean(String key) {
      try {
         return getProperty(key).getBoolean();
      } catch (ValueFormatException e) {
         throw new ItemAccessException(e);
      } catch (RepositoryException e) {
         throw new ItemAccessException(e);
      }
   }

   public void save() {
      try {
         node.save();
      } catch (AccessDeniedException e) {
         throw new ItemAccessException(e);
      } catch (ItemExistsException e) {
         throw new ItemAccessException(e);
      } catch (ConstraintViolationException e) {
         throw new ItemAccessException(e);
      } catch (InvalidItemStateException e) {
         throw new ItemAccessException(e);
      } catch (ReferentialIntegrityException e) {
         throw new ItemAccessException(e);
      } catch (VersionException e) {
         throw new ItemAccessException(e);
      } catch (LockException e) {
         throw new ItemAccessException(e);
      } catch (NoSuchNodeTypeException e) {
         throw new ItemAccessException(e);
      } catch (RepositoryException e) {
         throw new ItemAccessException(e);
      }
   }

   public void putString(String propertyName, String value) {
      try {
         node.setProperty(propertyName, ISO9075.encode(value));
      } catch (ValueFormatException e) {
         throw new ItemAccessException(e);
      } catch (VersionException e) {
         throw new ItemAccessException(e);
      } catch (LockException e) {
         throw new ItemAccessException(e);
      } catch (ConstraintViolationException e) {
         throw new ItemAccessException(e);
      } catch (RepositoryException e) {
         throw new ItemAccessException(e);
      }
   }

   public void putDate(String propertyName, Calendar value) {
      try {
         node.setProperty(propertyName, value);
      } catch (ValueFormatException e) {
         throw new ItemAccessException(e);
      } catch (VersionException e) {
         throw new ItemAccessException(e);
      } catch (LockException e) {
         throw new ItemAccessException(e);
      } catch (ConstraintViolationException e) {
         throw new ItemAccessException(e);
      } catch (RepositoryException e) {
         throw new ItemAccessException(e);
      }
   }

   public String getId() {
      try {
         return node.getUUID();
      } catch (ValueFormatException e) {
         throw new ItemAccessException(e);
      } catch (PathNotFoundException e) {
         throw new ItemAccessException(e);
      } catch (RepositoryException e) {
         throw new ItemAccessException(e);
      }
   }

   public PropertyIterator getProperties() {
      try {
         return node.getProperties();
      } catch (RepositoryException e) {
         throw new ItemAccessException(e);
      }
   }

   public boolean isEmpty() {
      return node == null;
   }

   public Integer getInteger(String propertyName) {
      try {
         return (int) node.getProperty(propertyName).getLong();
      } catch (ValueFormatException e) {
         throw new ItemAccessException(e);
      } catch (PathNotFoundException e) {
         throw new ItemAccessException(e);
      } catch (RepositoryException e) {
         throw new ItemAccessException(e);
      }
   }

   public void setGenericProperty(String name, Object createObject) {
      if (createObject instanceof Integer) {
         setProperty(name, (Integer) createObject);
      } else if (createObject instanceof String) {
         putString(name, (String) createObject);
      } else {
         throw new UnsupportedOperationException(
               "Actually there is no implementation to use generic setProperty(String, Object) for instance of "
                     + createObject.getClass() + " type");
      }
   }

   public Calendar getDate(String propertyName) {
      try {
         return node.getProperty(propertyName).getDate();
      } catch (ValueFormatException e) {
         throw new ItemAccessException(e);
      } catch (PathNotFoundException e) {
         throw new ItemAccessException(e);
      } catch (RepositoryException e) {
         throw new ItemAccessException(e);
      }
   }

   public Long getLong(String propertyName) {
      try {
         return node.getProperty(propertyName).getLong();
      } catch (ValueFormatException e) {
         throw new ItemAccessException(e);
      } catch (PathNotFoundException e) {
         throw new ItemAccessException(e);
      } catch (RepositoryException e) {
         throw new ItemAccessException(e);
      }
   }

   public String getType() {
      try {
         return node.getName();
      } catch (RepositoryException e) {
         throw new ItemAccessException(e);
      }
   }

   public void setType(String type) {
      throw new UnsupportedOperationException("In a " + getClass().getName()
            + " the TYPE of the Item is the NAME of the Node as defined in a standard JCR");
   }

   public void delete() {
      try {
         Node parent = node.getParent();
         node.remove();
         parent.save();
      } catch (ValueFormatException e) {
         throw new ItemAccessException(e);
      } catch (PathNotFoundException e) {
         throw new ItemAccessException(e);
      } catch (RepositoryException e) {
         throw new ItemAccessException(e);
      }
   }

   public void putLong(String propertyName, Long value) {
      try {
         node.setProperty(propertyName, value);
      } catch (ValueFormatException e) {
         throw new ItemAccessException(e);
      } catch (PathNotFoundException e) {
         throw new ItemAccessException(e);
      } catch (RepositoryException e) {
         throw new ItemAccessException(e);
      }
   }

   public InputStream getStream(String propertyName) {
      try {
         return node.getProperty(propertyName).getStream();
      } catch (ValueFormatException e) {
         throw new ItemAccessException(e);
      } catch (PathNotFoundException e) {
         throw new ItemAccessException(e);
      } catch (RepositoryException e) {
         throw new ItemAccessException(e);
      }
   }

   public NodeItem addChild(String relPath) {
      try {
         return new NodeItem(node.addNode(relPath));
      } catch (ItemExistsException e) {
         throw new ItemAccessException(e);
      } catch (PathNotFoundException e) {
         throw new ItemAccessException(e);
      } catch (VersionException e) {
         throw new ItemAccessException(e);
      } catch (ConstraintViolationException e) {
         throw new ItemAccessException(e);
      } catch (LockException e) {
         throw new ItemAccessException(e);
      } catch (RepositoryException e) {
         throw new ItemAccessException(e);
      }
   }

   public void putBoolean(String propAuthentic, boolean b) {
      try {
         node.setProperty(propAuthentic, b);
      } catch (ValueFormatException e) {
         throw new ItemAccessException(e);
      } catch (VersionException e) {
         throw new ItemAccessException(e);
      } catch (LockException e) {
         throw new ItemAccessException(e);
      } catch (ConstraintViolationException e) {
         throw new ItemAccessException(e);
      } catch (RepositoryException e) {
         throw new ItemAccessException(e);
      }
   }

   public void putStream(String key, InputStream stream) {
      try {
         node.setProperty(key, stream);
      } catch (ItemExistsException e) {
         throw new ItemAccessException(e);
      } catch (PathNotFoundException e) {
         throw new ItemAccessException(e);
      } catch (VersionException e) {
         throw new ItemAccessException(e);
      } catch (ConstraintViolationException e) {
         throw new ItemAccessException(e);
      } catch (LockException e) {
         throw new ItemAccessException(e);
      } catch (RepositoryException e) {
         throw new ItemAccessException(e);
      }
   }

   public void setNode(Node node) {
      this.node = node;
   }

   Node getNode() {
      return node;
   }

   public Timestamp getTimestamp(String propertyName) {
      throw new RuntimeException("method not implemented");
   }

   @Override
   public String toString() {
      return getType();
   }

   public void addChild(Item item) {
      try {
         node.addNode(item.getId().toString());
      } catch (ItemExistsException e) {
         throw new ItemAccessException(e);
      } catch (PathNotFoundException e) {
         throw new ItemAccessException(e);
      } catch (VersionException e) {
         throw new ItemAccessException(e);
      } catch (ConstraintViolationException e) {
         throw new ItemAccessException(e);
      } catch (LockException e) {
         throw new ItemAccessException(e);
      } catch (RepositoryException e) {
         throw new ItemAccessException(e);
      }
   }

   @Override
   public Object getMainAttributeValue() {
      return getString(getMainAttribute());
   }

   public void addMixin(String mixinName) {
      try {
         node.addMixin(mixinName);
      } catch (NoSuchNodeTypeException e) {
         throw new ItemAccessException(e);
      } catch (VersionException e) {
         throw new ItemAccessException(e);
      } catch (ConstraintViolationException e) {
         throw new ItemAccessException(e);
      } catch (LockException e) {
         throw new ItemAccessException(e);
      } catch (RepositoryException e) {
         throw new ItemAccessException(e);
      }
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((node == null) ? 0 : node.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      final NodeItem other = (NodeItem) obj;
      if (node == null) {
         if (other.node != null)
            return false;
      } else if (!node.equals(other.node))
         return false;
      return true;
   }

   public void reference(NodeItem item) {
      Node targetNode = item.getNode();
      try {
         node.setProperty(referencePropertyName(item.getClass()), targetNode.getUUID(),
               PropertyType.REFERENCE);
      } catch (ValueFormatException e) {
         throw new ItemAccessException(e);
      } catch (VersionException e) {
         throw new ItemAccessException(e);
      } catch (LockException e) {
         throw new ItemAccessException(e);
      } catch (ConstraintViolationException e) {
         throw new ItemAccessException(e);
      } catch (UnsupportedRepositoryOperationException e) {
         throw new ItemAccessException(e);
      } catch (RepositoryException e) {
      }
   }

   protected NodeItem getReference(Class<? extends NodeItem> klass) {
      Property property = getProperty(referencePropertyName(klass));
      try {
         return NodeToItem.createNodeItem(property.getNode(), klass);
      } catch (ValueFormatException e) {
         throw new ItemAccessException(e);
      } catch (RepositoryException e) {
         throw new ItemAccessException(e);
      }
   }

   protected static String referencePropertyName(Class<? extends NodeItem> klass) {
      return klass.getSimpleName().toLowerCase() + "_reference";
   }

   public void setReferenceable(boolean b) {
      if (b) {
         addMixin("mix:referenceable");
      } else {
         removeMixin("mix:referenceable");
      }
   }

   private void removeMixin(String mixinName) {
      try {
         node.removeMixin(mixinName);
      } catch (NoSuchNodeTypeException e) {
         throw new ItemAccessException(e);
      } catch (VersionException e) {
         throw new ItemAccessException(e);
      } catch (ConstraintViolationException e) {
         throw new ItemAccessException(e);
      } catch (LockException e) {
         throw new ItemAccessException(e);
      } catch (RepositoryException e) {
         throw new ItemAccessException(e);
      }
   }

}
