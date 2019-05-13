package testspringcore;

public class TestBean2Factory {
	
	public TestBean2Factory()
	{
		System.out.println("TestBean2Factory created");
	}
	
	public TestBean2 getInstance()
	{
		//define the logic
		return new TestBean2();
	}

}
