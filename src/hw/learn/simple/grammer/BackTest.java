package hw.learn.simple.grammer;


import org.junit.Test;

public class BackTest {
	@Test
	public void testBack1() {
		int arr[][] = { { 1, 2, 3 }, { 4, 5, 6, 7 }, { 9 } };
		boolean found = false;
		for (int i = 0; i < arr.length && !found; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.println("i=" + i + ",j=" + j);
				if (arr[i][j] == 5) {
					found = true;
					break;
				}
			}
		}
	}

	@Test
	public void testBack2() {
		ok: for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.println("i=" + i + ",j=" + j);
				if (j == 5)
					break ok;
			}
		}
	}

}
