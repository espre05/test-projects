package della.jocker.spike;

import static org.junit.Assert.assertEquals;

import javax.jcr.Node;
import javax.jcr.PropertyType;

import org.junit.Test;

import della.jocker.repo.JcrDao;

public class ReferenceableTest {

   @Test
   public void testReferenceableNodes() throws Exception {
      JcrDao dao = new JcrDao();
      Node pictures = dao.create("pictures");
      Node leonardo = pictures.addNode("leonardo");
      leonardo.addMixin("mix:versionable");
      pictures.save();

      Node classicTag = dao.create("tags").addNode("classic");
      Node leonardoTagged = classicTag.addNode("leonardo");
      leonardoTagged.setProperty("author_reference", leonardo.getUUID(), PropertyType.REFERENCE);

      assertEquals(leonardo, leonardoTagged.getProperty("author_reference").getNode());

      // NodeIterator findByProperty = dao.findByProperty("//tags//classic",
      // "author_reference", leonardo
      // .getUUID());
      // assertEquals(1, findByProperty.getSize());
   }
}
