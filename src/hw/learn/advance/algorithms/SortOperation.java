package hw.learn.advance.algorithms;

import org.junit.Test;

public class SortOperation {
	public static int[] data = { 12, 15, 20, 10, 19, 3, 89, 32, 39, 47, 55 }; // 原始数据
	public static int counter = 1; // 计数器
	public static int len = data.length;
	/**
	 * 折半查找
	 */
	public static boolean BinarySearch(int keyValue) {
		int left; // 左边界变量
		int right; // 右边界变量
		int middle; // 中位数变量
		System.out.println("数据长度：" + len);
		left = 0;
		right = len - 1;
		while (left <= right) {
			// 由于源数据不是顺序的，需先进行排序
			int temp;
			for (int i = 0; i < data.length; ++i) {
				for (int j = 0; j < data.length - i - 1; ++j) {
					if (data[j] > data[j + 1]) {
						temp = data[j];
						data[j] = data[j + 1];
						data[j + 1] = temp;
					}
				}
			}
			middle = (left + right) / 2;// 欲查找值较小
			if (keyValue < data[middle]) {
				right = middle - 1; // 查找前半段
			}
			else if (keyValue > data[middle]) {// 欲查找值较大
				left = middle + 1; // 查找后半段
			}
			// 查找到数据
			else if (keyValue == data[middle]) {
				System.out.println("data[" + middle + "] = " + data[middle]);
				return true;
			}
			counter++;
		}
		return false;
	}
	@Test
	public void testBinarySearch() {
		// 要查找的数
		int keyValue = 89;
		SortOperation t = new SortOperation();
		boolean b = t.BinarySearch(keyValue);

		if (b) {
			// 输出查找次数
			System.out.println("Search Time = " + counter);
		} else {
			// 输出没有找到数据
			System.out.println("No Found!!");
		}
	}

}
