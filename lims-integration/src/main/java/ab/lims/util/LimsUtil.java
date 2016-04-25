package ab.lims.util;


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sapphire.xml.PropertyList;
import ab.lims.AbException;

/**
 * @author pnatar
 * 
 *         Collect utilities here. If you are using 3rd party utilities (apache, google etc), those
 *         should idealy be masked from rest of the system through a call here. This would be a
 *         minor code addition, but enable flexibility if one api goes out and other comes in.
 */
public class LimsUtil {
  private static Logger log = LoggerFactory.getLogger(LimsUtil.class);

  public static Throwable getCause(Throwable t) {
    Throwable causingEx = ExceptionUtils.getRootCause(t);
    if (null == causingEx) {
      causingEx = t;
    }
    return causingEx;
  }

  /**
   * Use when you
   * 
   * @param filePath
   * @return
   */
  public static String getFileContent(Path filePath) {
    String fileContent = null;
    try {
      fileContent = FileUtils.readFileToString(filePath.toFile(), Charset.forName("UTF-8"));
    } catch (IOException e) {
      throw new AbException("Error reading file" + filePath, e);
    }
    return fileContent;
  }

  public static String getFileContent(String fileInClassPath) {
    String fileContent = null;
    try {
      Path path = LimsUtil.getResourceAsPath(fileInClassPath);
      fileContent = LimsUtil.getFileContent(path);

    } catch (Exception e) {
      throw new AbException("Error reading file in classpath" + fileInClassPath, e);
    }
    return fileContent;
  }
  
  public static InputStream stringToStream(String source){
    try{
    InputStream in = IOUtils.toInputStream(source, "UTF-8");
    return in;
    }catch(Exception e){
      throw new AbException("Error converting string to stream " + source, e);
    }
  }

  public static Path getResourceAsPath(String fileInClassPath) {
    Path path = null;
    try {
      path = Paths.get(new Object().getClass().getResource(fileInClassPath).toURI());

    } catch (Exception e) {
      log.debug("Error reading file in classpath" + fileInClassPath);
      throw new AbException("Error reading file in classpath" + fileInClassPath, e);
    }
    return path;
  }

  public static InputStream getResourceAsStream(String fileInClassPath) {
    InputStream path = null;
    try {
      path = new Object().getClass().getResourceAsStream(fileInClassPath);

    } catch (Exception e) {
      log.debug("Error reading file in classpath" + fileInClassPath);
      throw new AbException("Error reading file in classpath" + fileInClassPath, e);
    }
    return path;
  }
  
  @SuppressWarnings("unchecked")
  public static PropertyList toPropList(Map<String, String> map) {
    PropertyList sapphirePropList = new PropertyList();
    sapphirePropList.putAll(map);
    return sapphirePropList;
  }

  public static Date dbToJavaDate(String dbDateString) {

    Date javaDate = null;
    if (dbDateString != null) {
      SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm ");
      try {
        javaDate = dateFormat.parse(dbDateString);
        //DateFormat.getInstance().format(dbDateString);

      } catch (Exception e) {
        throw new AbException("Error conveting dateSting from db dbDateString=" + dbDateString, e);
      }
    }
    return javaDate;
  }

  public static java.sql.Date javaToDbDate(Date javaDate) {
    return new java.sql.Date(javaDate.getTime());
  }
  //for use in file naming
  public static String getTimestampString(Date dt)
  {
      DateFormat dateFormat = new SimpleDateFormat("yyMMdd_hhmmss.SSS"); 
      return dateFormat.format(dt);
  }
  static final DateFormat US_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
  static final DateFormat ISO_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
  static final DateFormat TIMESTAMP_ASLONG_FORMAT = new SimpleDateFormat("yyyyMMddhhmmSS");

  public static String getUSDateAsString(Date dt){
	  return US_DATE_FORMAT.format(dt);
  }
  public static String getIsoDateAsString(Date dt){
	  return ISO_DATE_FORMAT.format(dt);
  }
  public static long getTimeStampAsLong(Date dt){
	  return Long.parseLong(TIMESTAMP_ASLONG_FORMAT.format(dt));
  }
}
