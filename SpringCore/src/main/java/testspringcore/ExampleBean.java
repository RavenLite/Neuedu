package testspringcore;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExampleBean {
    private int years;
    private String ultimateAnswer;

    private List<String> list;
    private Set<String> set;
    private Map<String, String> map;

    public ExampleBean() {

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

    public ExampleBean(int years, String ultimateAnswer) {
        this.years = years;
        this.ultimateAnswer = ultimateAnswer;
    }

    public void outputInfo() {
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
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Iterator<Map.Entry<String, String>> it2 = entries.iterator();
        while(it2.hasNext())
        {
            Map.Entry<String, String> entry = it2.next();
            System.out.println(entry.getKey()+"\t"+entry.getValue());
        }
    }
}
