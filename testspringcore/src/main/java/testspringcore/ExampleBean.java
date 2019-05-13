package testspringcore;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.io.Resources;

public class ExampleBean {
	
	private int years;
	
	private String ultimateAnswer;
	
	//typed collection
	private List<String> list;
	private Set<String> set;
	private Map<String, String> map;
	
	//properties
	private Properties p;

	/*public ExampleBean(int years, String ultimateAnswer)
	{
		this.years = years;
		this.ultimateAnswer = ultimateAnswer;
	}*/
	
	public void setP(Properties p) {
		this.p = p;
	}


	public void setList(List<String> list) {
		
		this.list = list;
	}


	public void setSet(Set<String> set) {
		this.set = set;
	}


	public void setMap(Map<String, String> map) {
		this.map = map;
	}


	public void setYears(int years) {
		this.years = years;
	}


	public void setUltimateAnswer(String ultimateAnswer) {
		this.ultimateAnswer = ultimateAnswer;
	}

	public void outputinfo()
	{
		System.out.println(this.years+"\t"+this.ultimateAnswer);
		
		//iterate list
		for(String item :list)
		{
			System.out.println(item);
		}
		
		System.out.println("========================");
		
		//iterator set
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
		
		System.out.println("========================");
		
		//iterate map
		Set<Entry<String, String>> entries = map.entrySet();
		Iterator<Entry<String, String>> it2 = entries.iterator();
		while(it2.hasNext())
		{
			Entry<String, String> entry = it2.next();
			System.out.println(entry.getKey()+"\t"+entry.getValue());
		}
		
		//use properties to load file with extension name of properties
		/*p = new Properties();
		try {
			p.load(Resources.getResourceAsStream("db.properties"));
			
			//get key-value from p
			System.out.println(p.get("driver"));
			System.out.println(p.get("url"));
			System.out.println(p.get("username"));
			System.out.println(p.get("password"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//iterate all the properties
		Set<Entry<Object, Object>> entries2 = p.entrySet();
		Iterator<Entry<Object, Object>> it3 = entries2.iterator();
		while(it3.hasNext())
		{
			Entry<Object, Object> entry = it3.next();
			System.out.println(entry.getKey()+"\t"+entry.getValue());
		}
		
	}
	

}
