package demo.clarity.api.impl;

import java.io.InputStream;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.genologics.ri.sample.Sample;
import com.genologics.ri.project.Project;
import com.genologics.ri.project.ProjectLink;
import com.genologics.ri.project.Projects;
import com.genologics.ri.sample.SampleLink;
import com.genologics.ri.sample.Samples;

import demo.clarity.RestHostConfig;
import demo.lims.AppException;

public class ClarityRepoImpl {
	private RestHostConfig config;
	RestTemplate restTemplate;
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;

	//api - get
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

	public List<Project> getProjects() {
		Projects projects = restTemplate.getForObject(config.basePath.toString()+ "projects", Projects.class);
		List<ProjectLink> links = projects.getProject();
		List<Project> projectList = new ArrayList<>(links.size());
		links.stream().forEach(s ->{
			Project project = restTemplate.getForObject(s.getUri(), Project.class);
			projectList.add(project);
		});
		return projectList;
	}

//	public <T> List<T> get(Class<T> classType){
//		//Sample.class
//		ClarityMeta meta = Util.getMeta(classType);
//		String uri = config.basePath.toString() + "projects";
//		ResponseEntity<meta.collectionClass> response = rest.
//		return response.getBody();
//	}



	//infra
	public ClarityRepoImpl(RestHostConfig cfg) {
		this.config = cfg;
		init();
	}

	private void init() {
		initTemplate();
		initJaxb();
	}

	private void initJaxb() {
		try {
			JAXBContext jc = JAXBContext.newInstance("com.genologics.ri");
			marshaller = jc.createMarshaller();
			unmarshaller = jc.createUnmarshaller();
		} catch (Exception e) {
			throw new AppException(e);
		}
	}

	private void initTemplate() {
		// set basic auth
		Authenticator.setDefault(new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(config.user, config.password.toCharArray());
			}
		});

		HttpClient httpClient = HttpClients.createDefault();
		HttpHost host = URIUtils.extractHost(config.basePath);
		ClientHttpRequestFactory requestFactory = new AuthHttpComponentsClientHttpRequestFactory(httpClient, host,
				config.user, config.password);
		restTemplate = new RestTemplate(requestFactory);

	}

	public Object toObject(InputStream is) {
		try {
			return unmarshaller.unmarshal(new StreamSource(is));
		} catch (Exception e) {
			throw new AppException(e);
		}
	}
	


}
