package net.della.swaf.jcr;

import static org.junit.Assert.assertEquals;

import javax.jcr.Node;
import javax.jcr.Property;

import net.della.stuff.generic.lang.ClassHelper;

import org.junit.Test;

import della.jocker.repo.TableJcrDao;

public class NodeItemTest {

   @Test
   public void testSetGenericProperty() throws Exception {
      TableJcrDao jcrDao = new TableJcrDao("table", "type");
      jcrDao.createMainNode();
      Node node = jcrDao.create();
      NodeItem item = new NodeItem(node);
      ClassHelper helper = new ClassHelper(String.class);
      String name = "prop";
      item.setGenericProperty(name, helper.createObject());
      Property value = item.getProperty(name);
      assertEquals("", value.getString());
   }
}
