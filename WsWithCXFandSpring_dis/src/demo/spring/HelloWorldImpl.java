package demo.spring;

import javax.jws.WebService;

@WebService(endpointInterface="demo.spring.HelloWorld")
public class HelloWorldImpl {
	
	public String sayHi(String text){
		System.out.println("SayHi called");
		return "helloFromPrem" + text;
	}

}
