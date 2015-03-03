/**
 * 
 */
package net.della.swaf.jcr;

import net.della.swaf.jcr.BaseModelDao;
import net.della.swaf.jcr.Repo;
import della.jocker.repo.DaoException;
import della.jocker.repo.RuntimeDaoException;
import della.swaf.util.Command;

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