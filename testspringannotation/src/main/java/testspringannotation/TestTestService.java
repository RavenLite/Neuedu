package testspringannotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neuedu.model.service.EmpService;
import com.neuedu.model.service.ITestService;
import com.neuedu.model.service.TestService;

public class TestTestService {
		
	/**
	 *  How many types of implementation of dynamic proxy
	 *  
	 *  1. JDK support & cglib support, both of them are done at run time.(Spring AOP works on this)
	 * 
	 *  2. AspectJ(this is third part utility), this framework create proxy class during compile phase.
	 *  
	 *  When compile JAVA file => class file, modify the class file
	 *  
	 * 	EmpService service = context.getBean(EmpService.class); // the instance is really EmpService
	 * 
	 *  Aspectj need to take controll of JAVA compilation process.
	 *  
	 *  AspectJ have a better performance.
	 *  
	 *  Spring does not have much to do with AspectJ, it just uses some of the AspectJ anotations.
	 * 
	 *  Spring supports the integration of Aspectj(how to do this, check the document)
	 */

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		//
		ITestService service = (ITestService)context.getBean("testService");
		
		service.test();
		
		/* how spring works? spring create a class that implements this interface
		 * 
		 * in this case, spring implement dynamic proxy based on JDK support
		 * 
		 * Class xx$Proxy implements ITestService
		 *  { 
		 *        TestService service;
		 *  
		 *        public void test()
		 *        {
		 *          //logAspect.before();
		 *          //testAspect.test();
		 *          //business logic
		 *          try
		 *          {
		 *              service.addEmp();            
		 *          }
		 *          catch(Exception)
		 *          {
		 *              //logAspect.afterthrowing();
		 *          }
		 *          finally
		 *          {
		 *             /logAspect.after();
		 *          }
		 *          
		 *          //logAspect.afterreturnning();
		 *          
		 *        }
		 *  
		 *  }
		 *  
		 *  
		 * ITestService service = new xx$Proxy();

		 * service.test();
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * */
		
		
		
		
		
		
		
		
		
		
		
	}

}
