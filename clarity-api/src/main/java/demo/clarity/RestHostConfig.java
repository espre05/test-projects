package demo.clarity;

import java.net.URI;

public class RestHostConfig{
	public final String host;
	public final String user;
	public final String password;
	public final URI basePath; 
	public RestHostConfig(String host, String user, String password){
		this.host = host;
		this.user = user;
		this.password = password;
		this.basePath = getBasePath();
	}
	private URI getBasePath(){
		return URI.create("http://"+host+":8080/api/v2/");
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RestHostConfig [basePath=").append(basePath).append("]");
		return builder.toString();
	}
	
}