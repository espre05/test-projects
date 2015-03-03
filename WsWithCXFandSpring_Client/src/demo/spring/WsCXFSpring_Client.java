package demo.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WsCXFSpring_Client {

	public static void main(String args []){
		ApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml");
		HelloWorld client = (HelloWorld) context.getBean("helloClient");
		System.out.println("Response:" + client.sayHi("Prem Client SentHello"));
		
	}
	
}
