package hw.learn.simple.grammer;


public class StaticTest {
	public static int staticVar = 0; 
	public int instanceVar = 0; 
	public StaticTest(){
		staticVar++;
		instanceVar++;
		System.out.println("staticVar=" + staticVar + ",instanceVar="+ instanceVar);
	}
}
