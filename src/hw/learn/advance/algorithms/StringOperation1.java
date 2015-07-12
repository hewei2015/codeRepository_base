package hw.learn.advance.algorithms;

import org.junit.Test;

public class StringOperation1 {
	private static String[] array = null;
	private static String string;
	static {// 也可以用@Before在方法中进行初始化
		array = new String[] { "a", "b", "c", "AB", "CD" };
		string = "abcdef";
	}

	/**
	 * 写一个函数，给你一个字符串 倒序输出来
	 */
	@Test
	public void testReverseSort() {
		if (string != null) {
			String newStr = "";
			for (int i = 0; i < string.length(); i++) {
				char c = string.charAt(string.length() - 1 - i);//★
				newStr = newStr + c;
			}
			System.out.println(newStr);
		}
	}
	/**
	 * 不使用中间变量 把两个变量的值互换
	 */
	@Test
	public void testReplaceByNoOther() {
		int a = 10;
		int b = 100;
		a = a * b;
		b = a / b;
		a = a / b;
		System.out.print("a=" + a + " b=" + b);
	}
	/**
	 * 将this is a test 转化为This Is A Test
	 */
	@Test
	public void testFirth2UpeprCase() {
		String str = "this is a man";
		char c[] = new char[str.length()];
		str.getChars(0, str.length(), c, 0);
		if (c[0] >= 'a' && c[0] <= 'z') {
			c[0] = (char) (c[0] - 32);
		}
		for (int i = 1; i <= c.length - 1; i++) {
			if (c[i] == ' ') {
				c[i + 1] = (char) (c[i + 1] - 32);
			}
		}
		str = new String(c);
		System.out.print(str);
	}
//	@Test
	
}
