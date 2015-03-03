/**
 * 
 */
package net.della.swaf.jcr;

import javax.jcr.Node;

import della.jocker.repo.ApplicationObjectAdapter;

public class NodeToItemAdapter implements ApplicationObjectAdapter {
   private Class klass;

   public NodeToItemAdapter(Class<? extends NodeItem> klass) {
      this.klass = klass;
   }

   public NodeItem toApplicationObject(Node node) {
      return NodeToItem.createNodeItem(node, klass);
   }
}