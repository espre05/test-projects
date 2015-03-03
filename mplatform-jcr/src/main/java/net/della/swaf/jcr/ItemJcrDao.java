package net.della.swaf.jcr;

import java.util.*;

import javax.jcr.*;
import javax.jcr.query.Query;

import jin.collection.core.CollectionFactory;
import jin.collection.core.Iter;

import org.apache.jackrabbit.util.ISO9075;

import della.jocker.repo.*;

public class ItemJcrDao {

   protected TableJcrDao dao;
   final Class<? extends NodeItem> klass;

   public ItemJcrDao(Class<? extends NodeItem> klass) throws DaoException {
      this.klass = klass;
      dao = createDao();
   }

   public List<? extends NodeItem> selectAll() throws DaoException {
      Iterator nodeIterator = dao.selectAll();
      return (List<? extends NodeItem>) Iter.collect(CollectionFactory.asArrayList(nodeIterator),
            new NodeToItemAccessor(klass));
   }

   public NodeItem lookup(Long authorId) throws DaoException {
      Node node = dao.find(authorId);
      return NodeToItem.createNodeItem(node, klass);
   }

   public NodeItem create() throws DaoException {
      Node node = dao.create();
      return NodeToItem.createNodeItem(node, klass);
   }

   // public Iterator find(String queryString) throws DaoException {
   // return dao.find(queryString);
   // }

   public NodeItem lookup(String query) throws DaoException {
      return NodeToItem.createNodeItem(dao.lookup(query), klass);
   }

   public NodeItem lookupByProperty(String property, String searchTerm) throws DaoException {
      return lookupByProperties(property, searchTerm);
   }

   public NodeItem lookupByProperties(String... strings) throws DaoException {
      String[] result = strings.clone();
      for (int i = 1; i < strings.length; i = i + 2) {
         String string = strings[i];
         String encoded = ISO9075.encode(string);
         result[i] = encoded;
      }
      Node node = dao.lookupByProperties(result);
      return NodeToItem.createNodeItem(node, klass);
   }

   public Collection<? extends NodeItem> findByProperty(String property, Object searchTerm)
         throws DaoException {
      NodeIterator nodeIterator = dao.findByProperty(property, searchTerm);
      return Iter.collect(nodeIterator, new NodeToItemAccessor(klass));
   }

   public String getDefaultQueryLanguage() {
      return dao.getDefaultQueryLanguage();
   }

   public Query query(String queryString) throws DaoException {
      return dao.query(queryString);
   }

   public void removeAll() throws DaoException {
      dao.removeAll();
   }

   public void setDefaultQueryLanguage(String defaultQueryLanguage) {
      dao.setDefaultQueryLanguage(defaultQueryLanguage);
   }

   protected TableJcrDao createDao() throws DaoException {
      String className = klass.getSimpleName();
      TableJcrDao jcrDao = new TableJcrDao(toSqlName(className), toSingular(className));
      jcrDao.createMainNode();
      return jcrDao;
   }

   public String getName() throws DaoException {
      try {
         return dao.getMainNode().getName();
      } catch (RepositoryException e) {
         throw new DaoException(e);
      }
   }

   public int getDepth() throws DaoException {
      try {
         return dao.getMainNode().getDepth();
      } catch (RepositoryException e) {
         throw new DaoException(e);
      }
   }

   public long count() throws DaoException {
      return dao.count();
   }

   public NodeItem getParent(NodeItem nodeItem) throws DaoException {
      try {
         return NodeToItem.createNodeItem(nodeItem.getNode().getParent(), klass);
      } catch (ItemNotFoundException e) {
         throw new DaoException(e);
      } catch (AccessDeniedException e) {
         throw new DaoException(e);
      } catch (RepositoryException e) {
         throw new DaoException(e);
      }
   }

   public OperationsQueue bind(JcrToListMapper mapper) throws DaoException {
      return dao.bind(mapper);
   }

   public static String toSqlName(String name) {
      String result = "";
      for (int i = 0; i < name.length(); i++) {
         char ch = name.charAt(i);
         if (ch >= 'A' && ch <= 'Z')
            result = result + (i > 0 ? "_" : "") + String.valueOf(ch).toLowerCase();
         else
            result += ch;
      }
      return result;
   }

   public static String toSingular(String tableName) {
      return tableName.substring(0, tableName.length() - 1);
   }

   public XPathPropertiesQueryBuilder getQueryBuilder() {
      return dao.getQueryBuilder();
   }

   public NodeItem lookupById(String id) throws DaoException {
      Node node = dao.lookupByProperty("jcr:uuid", id);
      return NodeToItem.createNodeItem(node, klass);
   }

}
