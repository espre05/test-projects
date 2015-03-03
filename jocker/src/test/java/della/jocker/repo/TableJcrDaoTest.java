package della.jocker.repo;

import static org.junit.Assert.*;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.version.*;

import org.junit.*;

import della.jocker.util.BaseRepositoryTest;
import della.jocker.util.JcrUtil;

public class TableJcrDaoTest extends BaseRepositoryTest {

   private TableJcrDao dao;

   public TableJcrDaoTest() throws DaoException {
      dao = new TableJcrDao("aTable", "aType");
   }

   @Before
   public void cleanupAndInit() throws Exception {
      cleanUpAllRepo();
      dao.createMainNode();
   }

   @Test
   public void testConstructor() throws Exception {
      NodeIterator nodes = dao.rootNode().getNodes();
      System.out.println(nodes.nextNode().getName());
      Node node2 = nodes.nextNode();
      assertEquals("aTable", node2.getName());
      // Node node3 = nodes.nextNode();
      // assertEquals("aTable", node3.getName());
      assertEquals(2l, nodes.getSize());
      NodeIterator nodeIterator = dao.find("//aTable");
      assertEquals(1l, nodeIterator.getSize());
      dao.remove("//aTable");
      nodeIterator = dao.find("//aTable");
      assertEquals(0l, nodeIterator.getSize());

      assertNotNull(dao.createMainNode());
      assertNull(dao.createMainNode());

      assertEquals(dao.getMainNode(), dao.getMainNode("aTable"));

      assertEquals(0, getSession().getRootNode().getDepth());
      assertEquals(1, dao.getMainNode("aTable").getDepth());

   }

   @Test
   public void shouldCreateContentAtSpecificDepth() throws Exception {
      Node node = dao.create();
      assertEquals(2, node.getDepth());
   }

   @Test
   public void testSelectAllFindsOnlyContents() throws Exception {
      dao.create();
      dao.create();

      assertEquals(2l, dao.selectAll().getSize());
   }

   @Test
   public void shouldRemoveContentsButNotTable() throws Exception {
      dao.create();
      dao.create();

      dao.removeAll();

      assertEquals(0l, dao.selectAll().getSize());
      assertEquals(1l, dao.find("aTable").getSize());
      assertEquals(1l, dao.find("//aTable").getSize());
      assertNotNull(dao.getMainNode());
   }

   @Test
   public void testCount() throws Exception {
      assertEquals(0, dao.count());

      dao.create();
      dao.create();
      assertEquals(2, dao.count());

      dao.removeAll();
      assertEquals(0, dao.count());
   }

   @Test
   @Ignore
   public void shouldCreateVersionableNode() throws Exception {
      dao.setVersionable(true);
      Node node = dao.create();
      node.addMixin("mix:versionable");
      node.setProperty("director", "pippo");
      node.setProperty("title", "matrix");
      node.save();

      node.checkout();
      node.setProperty("director", "topolino");
      node.save();
      node.checkin();

      assertEquals(1, dao.selectAll().getSize());
      VersionHistory versionHistory = node.getVersionHistory();
      assertEquals(6, versionHistory.getDepth());
      VersionIterator versions = versionHistory.getAllVersions();
      assertEquals(2, versions.getSize());
      Version firstVersion = versions.nextVersion();
      JcrUtil.printProperties(firstVersion.getProperties());
      assertEquals("pippo", firstVersion.getProperty("director"));
      Version secondVersion = versions.nextVersion();
      assertEquals("topolino", secondVersion.getProperty("director"));
   }

   @Test
   public void showXPathOptions() throws Exception {
      assertEquals(dao.find("aTable").nextNode(), dao.find("//aTable").nextNode());
      assertEquals(1l, dao.find("aTable").getSize());
      dao.remove("aTable");
      assertEquals(0l, dao.find("aTable").getSize());
      assertEquals(0l, dao.find("//aTable").getSize());
   }

   @Test
   public void lookupShouldReturnNullIfNothinIsFound() throws Exception {
      assertNull("", dao.lookupByProperty("foo", "aaa"));
   }

   @Test
   public void testFindByProperty() throws Exception {
      Node node1 = dao.create();
      node1.setProperty("foo", "my bar");
      node1.save();
      Node node2 = dao.create();
      node2.setProperty("foo", "my bar");
      node2.save();
      NodeIterator result = dao.findByProperty("foo", "my bar");
      assertEquals(2, result.getSize());
   }

   @Test
   public void testLookupByMultipleProperties() throws Exception {
      Node node = dao.create();
      node.setProperty("foo", "my bar");
      node.setProperty("foo2", "my bars");
      node.setProperty("foo3", "my bars 3");
      node.save();

      Node result = dao.lookupByProperties("foo", "my bar");
      assertEquals(node, result);
      result = dao.lookupByProperties("foo", "my bar", "foo2", "my bars");
      assertTrue("", node.equals(result));
      result = dao.lookupByProperties("foo", "my bar", "foo2", "my bars", "foo3", "my bars 3");
      assertTrue("", node.equals(result));

      result = dao.lookupByProperties("foo", "my bar", "foo2", "bars");
      assertFalse("", node.equals(result));
   }

   @Test
   public void testLookupByProperty() throws Exception {
      Node node = dao.create();
      node.setProperty("foo", "my bar");
      node.save();
      Node result = dao.lookupByProperty("foo", "my bar");
      assertEquals(node, result);
   }

   @Test
   public void testTreePath() throws Exception {
      Node node1 = dao.create();
      assertEquals("aType", node1.getName());
      Node node2 = dao.create();
      assertEquals("aType", node2.getName());
      Node table = node2.getParent();
      assertEquals("aTable", table.getName());
      assertEquals(2, table.getNodes("aType").getSize());
   }

}
