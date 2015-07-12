package hw.learn.advance.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * 写一个函数，例如：给你的 a b c 则输出 abc acb bac bca cab cba
 */
public class ArrayOperation {
	private static String[] array = null;
	static {// 也可以用@Before在方法中进行初始化
		array = new String[] { "a", "b", "c","AB","CD"};
	}

	/**
	 * 输出数组中所有的元素
	 */
	public void outArray(String[] a) {
		for (int i = 0; i <a.length; i++) {//注意这里不是2，而是3，也可用<=2
			System.out.print(a[i]);
		}
	}
	/**
	 * 交换数组中的两个元素的位置，★★要想交换数组中元素的位置，必须将原数组传进来
	 */
	public void exchange(String a[],int i, int j) {
		String temp;
		temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	/**
	 * 该函数能把数组a的前m 个元素与后n 个元素交换位置
	 */
	public void swap(String a[], int m, int n) {
		String temp;
		for (int i = 0; i < m; i++) {
			temp = a[i];
			a[i] = a[i + n];
			a[i + n] = temp;
		}
	}
	@Test
	public void testOutStringAcb() {
		exchange(array,1, 2);
		outArray(array);
	}
	@Test
	public void testOutStringMN() {
		swap(array,1,2);
		outArray(array);
	}
	/**
	 * 将数组变成list:使用AddAll()方法
	 */
	@Test
	public void testArray2List(){
		//第一种方式
//		String[] words = { ... };
//		List<String> list = new ArrayList<String>(words.length);
//		for (String s : words) {
//		    list.add(s);
//		}
		//第二种方式
		List myList = new ArrayList();
		String[] myStringArray = new String[] {"Java", "is", "Cool"};
		Collections.addAll(myList, myStringArray);
		outArray(myStringArray);
		System.out.println(myList.size());
	}
}
