package della.jocker.repo;


public class XPathPropertiesQueryBuilder {

   private StringBuffer sb;

   public XPathPropertiesQueryBuilder(String typeScopeQuery) {
      sb = new StringBuffer(typeScopeQuery);
   }

   public void appendCondition(String property, Object authorId) {
      sb.append("[");
      sb.append(property);
      sb.append("='");
      sb.append(authorId);
      sb.append("'");
      sb.append("]");
   }

   public void appendCondition(String property, String searchTerm) {
      sb.append("[");
      sb.append(property);
      sb.append("='");
      sb.append(searchTerm);
      sb.append("'");
      sb.append("]");
   }

   public String getQuery() {
      return sb.toString();
   }

}
