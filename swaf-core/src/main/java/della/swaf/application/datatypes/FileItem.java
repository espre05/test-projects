package della.swaf.application.datatypes;

import java.io.File;

import com.sourcesense.stuff.file.FileUtils;

public class FileItem extends ObservableItem {

   private String type;

   private String relativePath;

   public void setType(String type) {
      this.type = type;
      put(FileItemAttributes.TYPE, type);

   }

   public String getType() {
      if (type != null)
         return type;
      return getString(FileItemAttributes.TYPE);
   }

   public void renameFileFromData() {

   }

   public void formatData() {

   }

   public String getPath() {
      // String vPath = getString(FileItemAttributes.VIRTUAL_PATH);
      // String rPath = getString(FileItemAttributes.RELATIVE_PATH);
      // Context context = LibraryImpl.getDefault().getContext();
      // String realHome = context.getPath(vPath);
      // return realHome + rPath;
      return "";
   }

   public String getExtension() {
      return FileUtils.getExtension(new File(getPath()));
   }

   public void setExtension(String ext) {
      File file = new File(getPath());
      // FileUtils.setExtension(file, ext);
   }

   public void deleteFromDisk() {
      try {
         File f = new File(getPath());
         f.delete();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   // public Profile getProfile(Class key) {
   // Class profileClassName = (Class) get(key.getName());
   // try {
   // Profile newInstance = (Profile) profileClassName.newInstance();
   // newInstance.setItem(this);
   // return newInstance;
   // } catch (InstantiationException e) {
   // e.printStackTrace();
   // } catch (IllegalAccessException e) {
   // e.printStackTrace();
   // }
   // return null;
   // }

   public void setProfile(Class name) {
      put(name.getName(), name);
   }

   public final String getRelativePath() {
      if (relativePath != null)
         return relativePath;
      return getString(FileItemAttributes.RELATIVE_PATH);
   }

   public final void setRelativePath(String relativePath) {
      this.relativePath = relativePath;
      put(FileItemAttributes.RELATIVE_PATH, relativePath);
   }

}
