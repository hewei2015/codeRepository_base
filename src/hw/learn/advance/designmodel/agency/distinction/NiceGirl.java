package hw.learn.advance.designmodel.agency.distinction;

public class NiceGirl implements Girl {

	private String name;

	public NiceGirl(String name) {
		this.name = name;
	}

	@Override
	public void behavior() {
		System.out.println(this.name + "长的非常nice");
		System.out.println(this.name + "说话也非常nice");

	}

}