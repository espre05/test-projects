package demo.clarity;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;

import demo.clarity.api.impl.ClarityRepoSpringImpl;

@Configuration
public class AppConfigLocal {

	@Bean
	ClarityRepoSpringImpl getClarityService() {
		ClarityRepoSpringImpl handler = new ClarityRepoSpringImpl();
		return handler;
	}

	@Bean RestTemplate restTemplate(){
		RestHostConfig hostcfg = hostConfig();
        final int timeout = 5;

        final RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000).setConnectionRequestTimeout(timeout * 1000).setSocketTimeout(timeout * 1000).build();

        final BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(new AuthScope(hostcfg.host, 8080, AuthScope.ANY_REALM), new UsernamePasswordCredentials(hostcfg.user, hostcfg.password));
        final CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(config).setDefaultCredentialsProvider(credentialsProvider).build();

        final ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(client);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
	}

	@Bean
	Jaxb2Marshaller getCastorMarshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setPackagesToScan("com.genologics.ri");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jaxb.formatted.output", true);
		jaxb2Marshaller.setMarshallerProperties(map);
		return jaxb2Marshaller;
	}

	@Bean
	RestHostConfig hostConfig(){
		return defaultHost();
	}

	public static RestHostConfig defaultHost() {
	String user = "api";
	String password = "admin123";
	String hostName = "clims02";
	return new RestHostConfig(hostName, user, password);
	}

}