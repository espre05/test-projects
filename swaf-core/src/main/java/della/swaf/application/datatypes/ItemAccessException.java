package della.swaf.application.datatypes;

public class ItemAccessException extends RuntimeException {

   public ItemAccessException(String message, Throwable cause) {
      super(message, cause);
   }

   public ItemAccessException(String message) {
      super(message);
   }

   public ItemAccessException(Throwable cause) {
      super(cause);
   }

}
