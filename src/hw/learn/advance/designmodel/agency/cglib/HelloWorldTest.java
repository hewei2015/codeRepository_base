package hw.learn.advance.designmodel.agency.cglib;

public class HelloWorldTest {
	public static void main(String[] args) {
		HelloWorld helloWorld = new HelloWorld();
		CglibProxy cglibProxy = new CglibProxy();
		HelloWorld hw = (HelloWorld) cglibProxy.createProxy(helloWorld);
		hw.sayHelloWorld();
	}
}
