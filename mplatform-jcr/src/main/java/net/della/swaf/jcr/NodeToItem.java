package net.della.swaf.jcr;

import javax.jcr.Node;

import net.della.stuff.generic.lang.ClassHelper;
import net.della.stuff.generic.lang.ReflectionException;

public class NodeToItem {

   public static NodeItem createNodeItem(Node node, Class<? extends NodeItem> klass) {

      ClassHelper classHelper = new ClassHelper(klass);
      try {
         NodeItem item = (NodeItem) classHelper.createObject();
         item.setNode(node);
         return item;
      } catch (ReflectionException e) {
         throw new RuntimeException("Unable to create instance for " + klass.getName(), e);
      }
   }

}
