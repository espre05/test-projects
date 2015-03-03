package net.della.swaf.jcr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Collection;

import org.apache.jackrabbit.util.ISO9075;
import org.junit.Before;
import org.junit.Test;

import della.jocker.repo.XPathPropertiesQueryBuilder;

public class ItemJcrDaoTest {

   private ItemJcrDao dao;

   @Before
   public void setUp() throws Exception {
      dao = new ItemJcrDao(DomainObjects.class);
      dao.removeAll();
   }

   @Test
   public void verifyNoObjectsInRepository() throws Exception {
      assertEquals(0l, dao.selectAll().size());
   }

   @Test
   public void shouldCreateInstanceOfDomainObject() throws Exception {
      NodeItem object = dao.create();
      assertEquals(DomainObjects.class, object.getClass());
   }

   @Test
   public void shouldAlwaysReturnInstancesOfDomainObject() throws Exception {
      NodeItem object = dao.create();
      NodeItem result = dao.selectAll().get(0);
      assertEquals(object.getId(), result.getId());
      assertEquals(DomainObjects.class, result.getClass());
   }

   @Test
   public void shouldRemoveAllAuthorsButNotMainNode() throws Exception {
      dao.create();
      dao.removeAll();
      assertEquals("domain_objects", dao.getName());
   }

   @Test
   public void testSelectAll() throws Exception {
      dao.create();
      Collection all = dao.selectAll();
      assertEquals(1, all.size());
   }

   @Test
   public void testEsapedCharacters() throws Exception {
      NodeItem node = dao.create();
      node.putString("foo", "my 'bar");
      node.save();
      NodeItem result = dao.lookupByProperty("foo", "my 'bar");
      assertEquals(node.getId(), result.getId());
   }

   @Test
   public void testLookupWithDirectQuery() throws Exception {
      NodeItem node = dao.create();
      node.putLong("p1", 2l);
      node.putString("p2", "sss");
      node.putString("p3", "zzz");
      node.save();

      NodeItem result = dao.lookupByProperties("p2", "sss", "p3", "zzz");
      assertFalse("", result.isEmpty());

      XPathPropertiesQueryBuilder queryBuilder = dao.getQueryBuilder();
      // queryBuilder.appendCondition("p1", 2l);
      queryBuilder.appendCondition("p2", ISO9075.encode("sss"));
      queryBuilder.appendCondition("p3", ISO9075.encode("zzz"));
      result = dao.lookup(queryBuilder.getQuery());
      System.out.println("selfmade query: " + queryBuilder.getQuery());
      assertFalse("", result.isEmpty());

      assertEquals(node.getId(), result.getId());
   }

}
