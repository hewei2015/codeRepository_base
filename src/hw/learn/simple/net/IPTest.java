package hw.learn.simple.net;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.Test;

public class IPTest {

	@Test
	public void testGetHostNameAndIP() throws UnknownHostException {
		// ▲InetAddress没有构造函数，不需要new
		InetAddress i = InetAddress.getLocalHost();
		// 得到主机名和IP
		System.out.println(i.toString());
		System.out.println("address:" + i.getHostAddress());// 得到地址
		System.out.println("name:" + i.getHostName());// 得到主机名
	}
	@Test
	public void testGetOtherNameAndIP() throws UnknownHostException {
		// ▲找任意一个Ip地址的主机名，如果没有找到该主机返回的名字还是你输入的ip地址
		InetAddress ia = InetAddress.getByName("www.baidu.com");
		// 通常情况下以IP地址为主，因为主机名还需要解析，由于百度的地址不唯一，所以返回的对象也是不唯一
		System.out.println("address:" + ia.getHostAddress());
		System.out.println("name:" + ia.getHostName());
		// 通过GetAllByName方法，可以依据主机名返回一个数组。
		InetAddress [] ias = InetAddress.getAllByName("www.baidu.com");
		for(InetAddress i:ias){
			System.out.println(i);
		}
	}
}
