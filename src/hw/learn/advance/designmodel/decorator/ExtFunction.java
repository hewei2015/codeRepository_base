package hw.learn.advance.designmodel.decorator;

public abstract class ExtFunction extends Component {
	private Component extFunc;
	
	public ExtFunction(Component component) {
		this.extFunc = component;
	}
	
	public void callAcceptAndSaveMsg(){
		if(extFunc!=null){
			extFunc.acceptAndSaveMsg();
		}
	}
}
