package della.jocker.repo;

public class RuntimeDaoException extends RuntimeException {

   public RuntimeDaoException(String message, Throwable cause) {
      super(message, cause);
   }

   public RuntimeDaoException(String message) {
      super(message);
   }

   public RuntimeDaoException(Throwable cause) {
      super(cause);
   }

}
