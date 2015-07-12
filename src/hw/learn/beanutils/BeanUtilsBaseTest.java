package hw.learn.beanutils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * BeanUtils基本用法
 */
public class BeanUtilsBaseTest {

	private User user = new User();
	private Order order1 = new Order();
	private Order order2 = new Order();
	private Order order3 = new Order();
	private Map map = new HashMap();
	private User user1 = new User();

	public BeanUtilsBaseTest() {
		init();
	}

	public static void main(String[] args) throws Exception {
		BeanUtilsBaseTest test = new BeanUtilsBaseTest();
		// 输出某个对象的某个属性
		System.out.println(BeanUtils.getProperty(test.user, "username"));
		// 输出某个对象的内嵌属性,只要使用点号分隔
		System.out.println(BeanUtils.getProperty(test.order1, "user.username"));
		// BeanUtils还支持List和Map类型的属性,对于Map类型，则需要以"属性名（key值）"的
		// 对于Indexed，则为"属性名[索引值]"，注意对于ArrayList和数组都可以用一样的方式进行操作
		System.out.println(BeanUtils.getProperty(test.user1, "map(order2).desc"));
		// 拷贝对象的属性值
		User tempUser = new User();
		BeanUtils.copyProperties(tempUser, test.user1);

		System.out.println(tempUser.getUsername());
		System.out.println(tempUser.getId());
	}

	// 初始化
	public void init() {
		user.setId(0);
		user.setUsername("zhangshan");

		order1.setId(1);
		order1.setDesc("order1");
		order1.setUser(user);

		order2.setId(2);
		order2.setDesc("order2");
		order2.setUser(user);

		order3.setId(3);
		order3.setDesc("order3");
		order3.setUser(user);

		map.put("order1", order1);
		map.put("order2", order2);
		map.put("order3", order3);

		user1.setId(1);
		user1.setUsername("lisi");
		user1.setMap(map);
	}
}
