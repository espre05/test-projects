/*
 * Created on 4-set-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package della.swaf.util;

import java.io.*;


/**
 * @author Daniele
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Persistence {

    public static void loadFromFile(String filename, Executor executor) {
        String line = "";
        BufferedReader inputFile = null;
        try {
            inputFile = new BufferedReader(new FileReader(filename));
            while ((line = inputFile.readLine()) != null) {
                executor.execute(line);
            }
            inputFile.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
