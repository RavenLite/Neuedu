package testspringcore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestService {

    private TestService()
    {
        System.out.println("spring create a bean");
    }

    //IOC inversion of control, also called DI(dependency injection)
    private TestDAO testDAO;

    public void setTestDAO(TestDAO testDAO) {
        this.testDAO = testDAO;
    }

    public static void main(String[] args) {

        //get a instance of TestService from Spring
        //ask spring for instance
        //create a Spring container,
        //if spring initialized container, spring will initialize all singleton instance.
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //get instance from Spring container

        //by default, spring uses singleton mode

        //by default, Spring initiate beans by invoking construction methods
		/*TestService service = (TestService)context.getBean("testService");
		TestService service2 = (TestService)context.getBean("testService");
		TestService service3 = (TestService)context.getBean("testService");

		System.out.println(service);
		System.out.println(service2);
		System.out.println(service3);*/

        TestBean bean = (TestBean)context.getBean("testBean");
        TestBean bean2 = (TestBean)context.getBean("testBean");
        TestBean bean3 = (TestBean)context.getBean("testBean");
        TestBean bean4 = (TestBean)context.getBean("testBean");
        TestBean bean5 = (TestBean)context.getBean("testBean");
        TestBean bean6 = (TestBean)context.getBean("testBean");
        TestBean bean7 = (TestBean)context.getBean("testBean");
        TestBean bean8 = (TestBean)context.getBean("testBean");
        TestBean bean9 = (TestBean)context.getBean("testBean");
        TestBean bean10 = (TestBean)context.getBean("testBean");
        //TestBean bean11 = (TestBean)context.getBean("testBean");

        System.out.println(bean);
        System.out.println(bean2);
        System.out.println(bean3);
        System.out.println(bean4);
        System.out.println(bean5);
        System.out.println(bean6);
        System.out.println(bean7);
        System.out.println(bean8);
        System.out.println(bean9);
        System.out.println(bean10);
        //System.out.println(bean11);

        System.out.println("===================================");

        TestBean2 t = context.getBean(TestBean2.class);
        TestBean2 t2 = context.getBean(TestBean2.class);
        TestBean2 t3 = context.getBean(TestBean2.class);
        System.out.println(t);
        System.out.println(t2);
        System.out.println(t3);


    }

}
