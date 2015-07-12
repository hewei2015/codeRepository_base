package hw.learn.datastruct;

public class MyList {

	// 下面我们决定扩大arr
	public void ExpandArr(){
		int[] arr = new int[10];
		int[] newArr = new int[arr.length * 2];
		for (int i = 0; i < arr.length; i++) {
			newArr[i] = arr[i];
			arr = newArr;
		}
	}
}
