package testspringcore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDI {
    public static void main(String[] args) {
        testExampleBean();
    }

    /* demo for 依赖注入 */
    public static void testDependency() {
        //1.get Spring IOC container
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //2.get TestService instance from container
        TestService instance = context.getBean(TestService.class);
        //if we can get a xx@xx, we can make sure dependencies is injected by Spring
        instance.outputDependency();
    }

    /* demo for 带有简单数据类型的依赖注入 */
    public static void testExampleBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ExampleBean instance = context.getBean(ExampleBean.class);
        instance.outputInfo();
    }
}