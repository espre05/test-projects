/**
 * 
 */
package net.della.swaf.jcr;

import javax.jcr.Node;

import jin.collection.core.ReadAccessor;

final class NodeToItemAccessor implements ReadAccessor {
   private Class klass;

   NodeToItemAccessor(Class<? extends NodeItem> klass) {
      this.klass = klass;
   }

   public Object getValue(Object element) {
      return NodeToItem.createNodeItem((Node) element, klass);
   }
}