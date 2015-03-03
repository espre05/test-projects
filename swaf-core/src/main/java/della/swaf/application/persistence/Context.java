package della.swaf.application.persistence;

public interface Context {

	String getPath(String id);

	String getID(String path);

	String getTopFolder(String path);

	String getHome();

}