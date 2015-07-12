package hw.learn.advance.designmodel.adapter.liuwei_sample1;

public class BirdAdapter extends Bird implements Robot {
	public void cry() {
		System.out.print("机器人模仿：");
		super.tweedle();
	}

	public void move() {
		System.out.print("机器人模仿：");
		super.fly();
	}
}