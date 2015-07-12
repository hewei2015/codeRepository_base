package hw.learn.advance.designmodel.agency.jdk_1;

public class PersonServiceImpl implements PersonService {
	
	public String user = null;

	public PersonServiceImpl() {
	};

	public PersonServiceImpl(String user) {
		this.user = user;
	}

	@Override
	public String getPersonName(Integer personId) {
		System.out.println("这是find方法");
		return this.user;
	}

	@Override
	public void save(String name) {
		System.out.println("这是save方法");
	}

	@Override
	public void update(Integer personId, String name) {
		System.out.println("这是update方法");
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
