package testspringannotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAnnotation {

	public static void main(String[] args) {
		
		//1. get Spring IOC container
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//2. get Bean
		TestController bean = (TestController)context.getBean("testController");
		
		bean.outputcollaborators();

	}

}
