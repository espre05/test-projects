package ab.xml;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

import ab.lims.AbException;
import ab.lims.util.LimsUtil;
import ab.xml.gen.v1.jaxb.OrderType;
import ab.xml.gen.v1.jaxb.RequestType;
import ab.xml.gen.v1.jaxb.SequentaOrdersType;

@Component
public class MarshallService {
  private static Logger log = LoggerFactory.getLogger(MarshallService.class);
  
  private Marshaller marshaller;
  private Unmarshaller unmarshaller;

  @Autowired
  public MarshallService(Marshaller marshaller, Unmarshaller unmarshaller){
	  this.marshaller = marshaller;  
	  this.unmarshaller = unmarshaller;
  }
  
  public void marshall(Path xmlFilePath, JAXBElement<RequestType> req) throws IOException {
    FileOutputStream os = null;
    try {
      os = new FileOutputStream(xmlFilePath.toFile());
      write(os, req);
    } finally {
      if (os != null) {
        os.close();
      }
    }
  }

  public void write(OutputStream os, JAXBElement req) {
    try {
      this.marshaller.marshal(req, new StreamResult(os));
    } catch (Exception ex) {
      throw new AbException(ex);
    }
  }

  public <T> JAXBElement<T> unmarshall(Class<T> xmlObjClassType,Path xmlFilePath) {
    try {
      String xmlString = LimsUtil.getFileContent(xmlFilePath);
      JAXBElement<T> je = unmarshall(xmlObjClassType, xmlString);
      T reqType = je.getValue();
      log.debug(reqType.toString());
      return je;
    } catch (Exception e) {
      throw new AbException(e);
    }
  }

  public <T> JAXBElement<T> unmarshall(Class<T> xmlObjClassType, String xmlString) {
    try {
      InputStream stream = LimsUtil.stringToStream(xmlString);
      @SuppressWarnings("unchecked")
      Object obj =this.unmarshaller.unmarshal(new StreamSource(stream));
      //JAXBElement<RequestType> je = (JAXBElement<T>) this.unmarshaller.unmarshal(new StreamSource(stream));
      JAXBElement<T> je = (JAXBElement<T>) obj;
      //RequestType reqType = je.getValue();
      T xmlObj = je.getValue();
      log.debug(xmlObj.toString());
      stream.close();
      return je;
    } catch (Exception e) {
      throw new AbException(e);
    }

  }
  
  public <T> T deepCopyJAXB(T object, Class<T> clazz) {
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
      JAXBElement<T> contentObject = new JAXBElement<T>(new QName(clazz.getSimpleName()), clazz, object);
      JAXBSource source = new JAXBSource(jaxbContext, contentObject);
      return jaxbContext.createUnmarshaller().unmarshal(source, clazz).getValue();
    } catch (JAXBException e) {
        throw new RuntimeException(e);
    }
  }

  public <T> T deepCopyJAXB(T object) {
    if(object==null) throw new RuntimeException("Can't guess at class");
    return deepCopyJAXB(object, (Class<T>) object.getClass());
  }
	public <T> String getAsString(JAXBElement<T> je){
		ByteArrayOutputStream  os = new ByteArrayOutputStream();
		this.write(os, je);
		String xmlSeqOrderString = new String(os.toByteArray(),Charset.defaultCharset());
		return xmlSeqOrderString;
	}

  
  //default helper method
public JAXBElement<SequentaOrdersType> getSeqOrderTemplate(){
	Date nowDate = new Date();
	String nowDateString = LimsUtil.getUSDateAsString(nowDate);
	BigInteger nowTimeStr = new BigInteger(""+LimsUtil.getTimeStampAsLong(nowDate));
	String fileInClassPath = "/ab/lims/test/msg/lpl/_template_SequentaOrders_new.xml";
	Path xmlFilePath = LimsUtil.getResourceAsPath(fileInClassPath);
	JAXBElement<SequentaOrdersType> je = this.unmarshall(SequentaOrdersType.class, xmlFilePath);
	SequentaOrdersType seqOrder = je.getValue();
	
	OrderType order = seqOrder.getOrder().get(0);
	order.setReceivedDate(nowDateString);
	order.setCollectionDate(nowDateString);
	order.setOrderDate(nowDateString);
	order.setOrderID(nowTimeStr);
	order.setStoredSpecimenRetreivalDate(nowDateString);
	
	order.getClinicalTest().setTestCollectionDate(nowDateString);
	order.getClinicalTest().setStoredSpecimenRetreivalDate(nowDateString);
	
//	OrderType order2 = this.deepCopyJAXB(order);
//	seqOrder.getOrder().add(order2);
	return je; 
}


}
