/**
 * 
 */
package net.della.swaf.jcr;

import net.della.stuff.generic.util.Command;
import della.jocker.repo.DaoException;
import della.jocker.repo.RuntimeDaoException;

public final class CreateItemCommand implements Command {

   private Class<? extends BaseModelDao> klass;

   public CreateItemCommand(Class<? extends BaseModelDao> klass) {
      this.klass = klass;
   }

   public Object run() {
      try {
         Repo.create(klass);
      } catch (DaoException e) {
         new RuntimeDaoException("Unable to create a New Author in repository: ", e);
      }
      return null;
   }
}