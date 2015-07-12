package hw.learn.advance.designmodel.agency.jdk_1;

import org.junit.BeforeClass;
import org.junit.Test;

public class PersonTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void Test() {
		JDKProxyFactory factory = new JDKProxyFactory();
		PersonService bean = (PersonService) factory.createProxyInstance(new PersonServiceImpl("lucy"));
		// 用户为lucy，有权限
		bean.save("abc");

		PersonService bean2 = (PersonService) factory.createProxyInstance(new PersonServiceImpl());
		// 用户为null，没有权限，不输出
		bean2.save("abc");
	}
}
