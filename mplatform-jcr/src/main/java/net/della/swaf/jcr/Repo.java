package net.della.swaf.jcr;

import net.della.stuff.generic.lang.ClassHelper;
import net.della.stuff.generic.lang.ReflectionException;
import della.jocker.repo.DaoException;

public class Repo {

   public static void create(Class<? extends BaseModelDao> klass) throws DaoException {
      ClassHelper helper = new ClassHelper(klass);
      try {
         BaseModelDao dao = (BaseModelDao) helper.createObject();
         dao.delegateCreate();
      } catch (ReflectionException e) {
         throw new DaoException(e);
      }
   }

}
