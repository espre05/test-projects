package ab.lims.spring.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
//@BootstrapWith(CamelTestContextBootstrapper.class)
@ContextConfiguration("classpath:ab/lims/spring/cfg/app.xml")
public class SpringPropUtilTest {
  @Before
  public void startUp() throws Exception{
    FileUtils.deleteQuietly(Paths.get("C:/lims_dir/JUNIT/").toFile());
  }

  @Test
  public void substitutedPropertyFetch() throws Exception {
    String expectedBaseDirPath = "C:/lims_dir/JUNIT/LV701";
    String baseDirPath = SpringPropUtil.getProperty("lims.app.baseDir.path");
    Assert.assertEquals("Test substituted property retrival",expectedBaseDirPath, baseDirPath);
    
  }
  
  @Test
  public void checkFolderAutoCreation() throws Exception {
    Path poolFileShouldNotBeCreated = Paths.get(SpringPropUtil.getProperty("pooling.finalPoolFile.path"));
    Assert.assertTrue("A folder should not be created for file names", Files.notExists(poolFileShouldNotBeCreated));
    Assert.assertTrue("A PARENT folder should BE-created for file names", Files.exists(poolFileShouldNotBeCreated.getParent()));

    Path cellCounterPartialFileFolderShouldNotBeCreated = Paths.get(SpringPropUtil.getProperty("cellCount.cellCounterSimulatorFile.partial"));
    Assert.assertTrue("A folder should not be created for partial file", Files.notExists(cellCounterPartialFileFolderShouldNotBeCreated));
    Assert.assertTrue("A PARENT folder should BE-created for file names", Files.exists(cellCounterPartialFileFolderShouldNotBeCreated.getParent()));
    
    Path nxpGDBaseDirShouldBeCreated = Paths.get(SpringPropUtil.getProperty("cellIso.granularCellDep.nxp.baseDir.path"));
    Assert.assertTrue("A folder should be created for baseDir", Files.exists(nxpGDBaseDirShouldBeCreated));
  }
  

}
