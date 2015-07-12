package hw.learn.advance.designmodel.decorator;

public class BaseFunction extends Component {
	@Override
	public void acceptAndSaveMsg() {
		System.out.println("接收前台页面传递过来的信息");
		
		System.out.println("将这些信息存于数据库");
	}
}
