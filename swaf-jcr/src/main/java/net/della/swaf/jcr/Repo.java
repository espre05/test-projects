package net.della.swaf.jcr;


import com.sourcesense.stuff.lang.ClassHelper;
import com.sourcesense.stuff.lang.ReflectionException;

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
