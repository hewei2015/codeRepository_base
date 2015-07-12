package hw.learn.advance.designmodel.agency.distinction;


/**
 * 参考文档：http://blog.csdn.net/hitprince/article/details/6794748
 * 本例主要说明代理设计模式和装饰器设计模式的区别：
 *		代理模式中，代理类对被代理的对象有控制权，决定其执行或者不执行。
 *		而装饰模式中，装饰类对代理对象没有控制权，只能为其增加一层装饰，以加强被装饰对象的功能，仅此而已。
 */
public class Client {
	public static void main(String[] args) {
	//@Test
	//public void client_test(){
		Girl niceGirl = new NiceGirl("小美");
		Girl friend = new GirlAgent(niceGirl);
		friend.behavior();
	}
}
