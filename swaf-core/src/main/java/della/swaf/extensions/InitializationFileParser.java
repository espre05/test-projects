package della.swaf.extensions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class InitializationFileParser {

   public List load(String filename) {

      List lines = new ArrayList();
      File file = new File(filename);
      if (file.exists())
         try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
               if (line.indexOf(':') == -1) {
                  line = line.replaceAll("-", " ");
                  line = line.trim();
                  lines.add(parseLine(line));
               }
               line = reader.readLine();
            }
            reader.close();
         } catch (FileNotFoundException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         }
      return lines;
   }

   public abstract Object parseLine(String line);
}
