package hw.learn.simple.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * 获取指定IP的主机名
 */
public class IPScanner {
	// 传入IP地址，返回主机名称，若主机不可达则返回ip地址的字符串形式
	public static String scanner(byte[] ip) {
		InetAddress addr = null;
		try {
			addr = InetAddress.getByAddress(ip);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return addr.getHostName();
	}

	public static void main(String[] args) {
		String hostName;
		String ip;
		for (int i = 0; i <= 127; i++) {
			ip = "192.168.1." + i;
			hostName = scanner(new byte[] { 59, 68, (byte) 255, (byte)i });
			if (!ip.equals(hostName))
				System.out.println(ip +" : "+hostName);
		}
	}
}
