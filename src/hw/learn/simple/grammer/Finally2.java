package hw.learn.simple.grammer;


public class Finally2 {
	public static void main(String args[]) {
		Finally2 t = new Finally2();
		int b = t.get();
		System.out.println(b);//2
	}
	@SuppressWarnings("finally")
	public int get() {
		try {
			return 1;
		} finally {
			return 2;
		}
	}
}
