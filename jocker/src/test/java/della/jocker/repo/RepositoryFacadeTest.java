package della.jocker.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.jcr.Repository;

import net.della.stuff.generic.file.FileUtils;

import org.apache.jackrabbit.core.TransientRepository;
import org.junit.Test;

public class RepositoryFacadeTest {

	@Test
	public void shouldStoreRepositoryInCustomFolder() throws Exception {
		RepositoryFacade.closeSession();
		RepositoryFacade.REPO_HOME_DIR = "repo_real";
		RepositoryFacade.currentSession();
		File homefolder = new File("repo_real");
		assertTrue("", homefolder.exists());
		assertTrue("", homefolder.isDirectory());

		FileUtils.deleteFoldersRecursively(RepositoryFacade.REPO_HOME_DIR);
	}

	@Test
	public void iniateTransactionrepository() throws Exception {
		Repository repository = RepositoryFacade.getRepository();
		assertEquals(TransientRepository.class, repository.getClass());
	}
}
