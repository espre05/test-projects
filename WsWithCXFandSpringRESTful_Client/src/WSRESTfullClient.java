import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public final class WSRESTfullClient {

 public static void main(String[] args) throws Exception {
//  String xml = null;
  try {
   // Make an HTTP connection to the server
   URL u = new URL("http://localhost:26446/orders");
   HttpURLConnection httpCon = (HttpURLConnection) u.openConnection();
	httpCon.setRequestProperty("Content-Type", "text/xml; charset=\"utf-8\"");

   // Set the request method as POST
   httpCon.setRequestMethod("POST"); 
   httpCon.setDoOutput(true);

   OutputStream os = httpCon.getOutputStream();
   // XML encoded in UTF-8 format
   OutputStreamWriter wout = new OutputStreamWriter(os, "UTF-8"); 
   wout.write("<?xml version=\"1.0\"?>\r\n");  
   // Add customer name as XML fragment
   wout.write("<order>  <name>Prem1</name></order>\r\n"); 
   wout.flush();

   // Make implicit connection to the server
   httpCon.getContentLength(); 
   httpCon.disconnect();
   
   os.close();
  } catch (IOException e) { 
   e.printStackTrace();
  }
 }
}
