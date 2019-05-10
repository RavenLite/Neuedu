package testspringannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TestService {

	@Autowired
	private TestDAO testDAO;
	
	public void outputinfo()
	{
		System.out.println(testDAO);
	}

}
