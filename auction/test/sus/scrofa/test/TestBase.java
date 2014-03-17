package sus.scrofa.test;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBase {
	@Before
	public void init() {
		cxt = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		System.out.println("spring initialized.");
	}

	protected ApplicationContext cxt;
}
