package demo.clarity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigDEV extends AppConfigLocal{
	@Override
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