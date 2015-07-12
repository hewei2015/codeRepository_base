package hw.learn.simple.grammer;



public class SuperTest {
	public static void main(String[] args) {
		new SuperTest().test();
	}
	public void test(){
		System.out.println(super.getClass().getName());
	}
}
