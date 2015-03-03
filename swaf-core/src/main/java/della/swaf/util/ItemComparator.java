package della.swaf.util;

import java.util.Comparator;

import della.swaf.application.datatypes.Item;

public abstract class ItemComparator implements Comparator {

  private Comparator comparator;
  protected String propName;

  public ItemComparator(String propName) {
    this.propName = propName;
  }

  public int compare(Object o1, Object o2) {
    return compare((Item) o1, (Item) o2);
  }

  public int compare(Item item1, Item item2) {
    String name1 = item1.getString(propName);
    String name2 = item2.getString(propName);
    return getComparator().compare(name1, name2);
  }

  protected Comparator getComparator() {
    return comparator;
  }

  protected void setComparator(Comparator stringComparator) {
    this.comparator = stringComparator;
  }

}
