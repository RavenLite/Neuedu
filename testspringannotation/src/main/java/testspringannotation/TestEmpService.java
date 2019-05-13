package testspringannotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neuedu.model.service.DeptService;
import com.neuedu.model.service.EmpService;

public class TestEmpService {
	
	public static void main(String[] args) {
		//1. initialize the spring container
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2. get a instance of the service
		EmpService service = context.getBean(EmpService.class);
		
		/**
		 *  How spring works? Spring will create a child class
		 *  
		 *  Spring implements dynamic proxy based on cglib(supported by third party component) features
		 *   
		 *  Class xx$Proxy extends EmpService
		 *  {
		 *  
		 *        public void addEmp()
		 *        {
		 *          //logAspect.before();
		 *          //testAspect.test();
		 *          //business logic
		 *          try
		 *          {
		 *              super.addEmp();            
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
		 * EmpService service = new xx$Proxy();
		 * 
		 * service.addEmp();
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
		 * 
		 * 
		 * 
		 * 
		 */
		
		
		
		
		
		
		
		//3. invoke its method to see if aspect is weaved or not
		service.addEmp();
		/*service.deleteEmp();
		
		DeptService service2 = context.getBean(DeptService.class);
		service2.test1();
		service2.test2();*/

	}

}
