package testspringcore;

//only have ten instance
//This class has the function of producing instance

public class TestBean {

    private static int count = 0;

    public static TestBean getInstance()
    {
        System.out.println("factory method invoked");
        if(count<10)
        {
            count++;
            return new TestBean();
        }
        return null;
    }

}