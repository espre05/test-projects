package della.jocker.repo;

import java.util.LinkedHashMap;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.observation.Event;

public class JcrToListMapper {

   protected final ApplicationObjectAdapter applicationObjectAdapter;
   private final LinkedHashMap<String, Integer> jcrToListMap;
   protected final List targetList;

   public JcrToListMapper(ApplicationObjectAdapter applicationObjectAdapter, List targetList) {
      this.applicationObjectAdapter = applicationObjectAdapter;
      this.targetList = targetList;
      jcrToListMap = new LinkedHashMap<String, Integer>();
   }

   protected void insertIntoList(final Node node) {
      targetList.add(applicationObjectAdapter.toApplicationObject(node));
   }

   protected void deleteFromList(int index) {
      targetList.remove(index);
   }

   protected void insert(final Node node) throws RuntimeDaoException {
      insertIntoList(node);
      updateMappingForInsert(node);
   }

   private void updateMappingForInsert(final Node node) {
      try {
         jcrToListMap.put(node.getPath(), targetList.size() - 1);
      } catch (RepositoryException e) {
         throw new RuntimeDaoException(e);
      }
   }

   protected void delete(String path) {
      int index = deleteFromMapping(path);
      deleteFromList(index);
   }

   private int deleteFromMapping(String path) {
      int index = jcrToListMap.get(path);
      jcrToListMap.remove(path);
      return index;
   }

   void processInsertEvent(Event event) {
      try {
         Node node = new JcrDao().findByPath(event.getPath().replaceFirst("/", ""));
         insert(node);
      } catch (DaoException e) {
         throw new RuntimeDaoException(e);
      } catch (RepositoryException e) {
         throw new RuntimeDaoException(e);
      }
   }

   void processRemoveEvent(Event event) {
      try {
         delete(event.getPath());
      } catch (RepositoryException e) {
         throw new RuntimeDaoException(e);
      }
   }

}
