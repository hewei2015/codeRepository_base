package hw.learn.advance.designmodel.adapter.liuwei_sample1;

public class Client {
	public static void main(String args[]) {
		Robot robot = (Robot) XMLUtil.getBean();
		robot.cry();
		robot.move();
	}
}