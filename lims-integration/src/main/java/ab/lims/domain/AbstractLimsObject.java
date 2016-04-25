package ab.lims.domain;


import java.util.Comparator;
import java.util.Date;

/**
 * @author pnatar Common/core fields/functionality to be defined here. e.g all objects in LV Lims
 *         has createdt, moddt,
 * 
 */
public abstract class AbstractLimsObject {
  Date createDate;
  Date modDate;

  public AbstractLimsObject() {}

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getModDate() {
    return modDate;
  }

  public void setModDate(Date modDate) {
    this.modDate = modDate;
  }
  //
  /**
   * Use for getting the latest sample/obj, or oldest sample/obj, 
   * @return
   */
  public Comparator<AbstractLimsObject> getCreateDtComparator() {
    Comparator<AbstractLimsObject> cmp = new Comparator<AbstractLimsObject>() {
      @Override
      public int compare(AbstractLimsObject t1, AbstractLimsObject t2) {
        return t1.getCreateDate().compareTo(t2.getCreateDate());
      }
    };
    return cmp;
  }

  protected Comparator<AbstractLimsObject> getModDtComparator() {
    Comparator<AbstractLimsObject> cmp = new Comparator<AbstractLimsObject>() {
      @Override
      public int compare(AbstractLimsObject t1, AbstractLimsObject t2) {
        return t1.getModDate().compareTo(t2.getModDate());
      }
    };
    return cmp;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("AbstractLimsObject [createDate=").append(createDate).append(", modDate=").append(modDate).append("]");
    return builder.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
    result = prime * result + ((modDate == null) ? 0 : modDate.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AbstractLimsObject other = (AbstractLimsObject) obj;
    if (createDate == null) {
      if (other.createDate != null)
        return false;
    } else if (!createDate.equals(other.createDate))
      return false;
    if (modDate == null) {
      if (other.modDate != null)
        return false;
    } else if (!modDate.equals(other.modDate))
      return false;
    return true;
  }

}
