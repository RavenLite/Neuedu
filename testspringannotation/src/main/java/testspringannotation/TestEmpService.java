package testspringannotation;

import com.neuedu.model.service.EmpService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestEmpService {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmpService empService = (EmpService) context.getBean("empService");
        empService.addEmp();
    }
}
