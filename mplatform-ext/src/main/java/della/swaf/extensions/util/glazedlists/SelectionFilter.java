/*
 * Created on 15-gen-2004
 *
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */
package della.swaf.extensions.util.glazedlists;

import java.util.List;


public abstract class SelectionFilter extends CustomFilter {

   protected List selectionList;

   public final void setSelectionList(List list) {
      selectionList = list;
   }

}