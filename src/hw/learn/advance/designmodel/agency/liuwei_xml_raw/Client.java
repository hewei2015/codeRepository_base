package hw.learn.advance.designmodel.agency.liuwei_xml_raw;

/**
 * 这里是完全自己实现动态代理，通过该例能比较好的理解动态代理、面向接口编程
 * 
 * 这里通过使用代理达到对原有资源的简单权限限制，可以用被代理对象和代理对象测试看效果
 */
public class Client {
	public static void main(String args[]) {
		//第一种方式：xml文件解析后得到代理类，父类接口接收
		//AbstractPermission permission = (AbstractPermission) XMLUtil.getBean();
		//第二种方式：java代码new代理类对象，父类接口接收
		//AbstractPermission permission = new PermissionProxy();//内部自动想父类(接口)转发
		//第三种方式：如果使用被代理类对象，则没有代理效果
		AbstractPermission permission = new RealPermission();
		
		permission.modifyUserInfo();
		permission.viewNote();
		permission.publishNote();
		permission.modifyNote();
		System.out.println("----------------------------");
		permission.setLevel(1);
		permission.modifyUserInfo();
		permission.viewNote();
		permission.publishNote();
		permission.modifyNote();
	}
}