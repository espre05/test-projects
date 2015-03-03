package net.della.swaf.jcr;

import javax.jcr.Node;

import com.sourcesense.stuff.lang.ClassHelper;
import com.sourcesense.stuff.lang.ReflectionException;

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
