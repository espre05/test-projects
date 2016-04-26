package demo.clarity.api.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.genologics.ri.sample.Sample;
import com.genologics.ri.sample.SampleLink;
import com.genologics.ri.sample.Samples;

import demo.clarity.RestHostConfig;
import demo.lims.AppException;

@Component
public class ClarityRepoSpringImpl {

	@Autowired RestTemplate restTemplate;
	@Autowired RestHostConfig config;
	

	public List<Sample> getSamples() {
		Samples samples = restTemplate.getForObject(config.basePath.toString()+ "samples", Samples.class);
		List<SampleLink> links = samples.getSample();
		List<Sample> sampleList = new ArrayList<>(links.size());
		links.stream().forEach(s ->{
			Sample sample = restTemplate.getForObject(s.getUri(), Sample.class);
			sampleList.add(sample);
		});
		
		return sampleList;
	}


	@Autowired
	private Marshaller marshaller;
	@Autowired
	private Unmarshaller unmarshaller;

	// Converts Object to XML file
	public void marshallToXML(String fileName, Object jaxbElement) throws IOException {
		FileOutputStream fos = null;
		try {
			// JAXBElement<RequestType> je =
			// marshallService.unmarshall(RequestType.class, xmlFilePath);
			fos = new FileOutputStream(fileName);
			marshaller.marshal(jaxbElement, new StreamResult(fos));
		} finally {
			fos.close();
		}
	}

	public void marshall(Object jaxbElement, OutputStream os) {
		try {
			marshaller.marshal(jaxbElement, new StreamResult(os));
		} catch (Exception e) {
			// Don't swallow.
		}
	}

	// Converts XML to Java Object
	public Object xmlToObject(String fileName) throws IOException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
			return unmarshaller.unmarshal(new StreamSource(fis));
		} finally {
			fis.close();
		}
	}
	public Object toObject(InputStream is){
		try {
			return unmarshaller.unmarshal(new StreamSource(is));
		}catch(Exception e){
			throw new AppException(e);
		}
	}

}