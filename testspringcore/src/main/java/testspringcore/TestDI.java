package testspringcore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDI {

	public static void main(String[] args) {
		//1. get Spring IOC container
		//ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2. get TestService instance from container
		//TestService instance = context.getBean(TestService.class);
		//if we can get a xx@xx, we can make sure dependencies is injected by Spring
		//instance.outputDependency();
		
		testExampleBean();
	}
	
	public static void testExampleBean()
	{
		//1. get Spring IOC container
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2. get bean of ExampleBean
		ExampleBean bean = context.getBean(ExampleBean.class);
		
		bean.outputinfo();
	
	
	}

}
