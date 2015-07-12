package hw.learn.advance.designmodel.agency.distinction;

import java.util.Random;

public class GirlAgent implements Girl {

	private Girl girl;

	public GirlAgent(Girl girl) {
		super();
		this.girl = girl;
	}

	/**
	 * 代理模式：使用behavior
	@Override
	public void behavior() {
		Random rand = new Random();
		if (rand.nextBoolean()) {
			System.out.println("我安排你们上自习");
			girl.behavior();
		} else {
			System.out.println("先看你的表现，上自习以后再说");
		}
	}
	 */
	
	/**
	 * 装饰器模式：使用behavior
	 */
	@Override
	public void behavior() {
		System.out.println("我家MM不但知书达礼，而且还会做饭");
		girl.behavior();
	}
}
