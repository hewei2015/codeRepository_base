package hw.learn.simple.grammer;


public class Finally {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		System.out.println(new Finally().test());
	}
	static int test() {
		int x = 1;
		try {
			return x;
		} finally {
			++x;
		}
	}

}
