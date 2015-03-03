package net.della.swaf.jcr;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import della.jocker.repo.*;

public abstract class BaseModelDao extends NodeItem {

   protected ItemJcrDao itemDao;
   protected Logger logger;

   public BaseModelDao() throws DaoException {
      super();
      logger = LoggerFactory.getLogger(getClass());
      itemDao = new ItemJcrDao(getClass());
   }

   public void delegateRemoveAll() throws DaoException {
      itemDao.removeAll();
   }

   public List delegateSelectAll() throws DaoException {
      return itemDao.selectAll();
   }

   protected NodeItem delegateLookup(String query) throws DaoException {
      return itemDao.lookup(query);
   }

   public NodeItem delegateLookup(long id) throws DaoException {
      return itemDao.lookup(id);
   }

   public NodeItem delegateLookupByProperty(String propTitle, String string) throws DaoException {
      return itemDao.lookupByProperty(propTitle, string);
   }

   public NodeItem delegateLookupByProperties(String... strings) throws DaoException {
      return itemDao.lookupByProperties(strings);
   }

   public Collection<? extends NodeItem> delegateFindByProperty(String property, Object searchValue)
         throws DaoException {
      return itemDao.findByProperty(property, searchValue);
   }

   private NodeItem delegateFindByProperty(String... strings) throws DaoException {
      return itemDao.lookupByProperties(strings);
   }

   protected long delegateCount() throws DaoException {
      return itemDao.count();
   }

   protected EventQueue delegateBind(JcrToListMapper mapper) throws DaoException {
      return itemDao.bind(mapper);
   }

   protected NodeItem getParent() throws DaoException {
      return itemDao.getParent(this);
   }

   public abstract NodeItem delegateCreate() throws DaoException;

   protected XPathPropertiesQueryBuilder getQueryBuilder() {
      return itemDao.getQueryBuilder();
   }

}
