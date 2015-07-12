package hw.learn.simple.net;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

public class UDPTest {
	/** 需求：通过udp传输方式，将一段文字数据发送出去。首先定义一个udp发送端 */
	/** 注意测试之前应该开启接收端的测试，并且不能运行完就关闭连接 */
	@Test
	public void testUDPSend() throws Exception {
		// 1，创建udp服务。通过DatagramSocket对象。
		DatagramSocket ds = new DatagramSocket(8888);
		// 2，确定数据，并封装成数据包。
		// DatagramPacket(byte[] buf, int length, InetAddress address, int port)
		byte[] buf = "I love you ".getBytes();// 字符串数据变成byte数组
		// 使用发送数据包的DatagramPacket构造函数
		DatagramPacket dp = new DatagramPacket(buf, buf.length,
				InetAddress.getByName("192.168.1.109"), 10000);
		// 3，通过socket服务，将已有的数据包发送出去。通过send方法。
		ds.send(dp);
		// 4，关闭资源
		ds.close();
	}

	/** 需求：定义一个应用程序，用于接收udp协议传输的数据并处理的 */
	@Test
	public void testUDPRece() throws Exception {
		// 1,创建udpsocket服务，通常会监听一个端口。其实就是给这个接收网络应用程序定义数字标识，方便于明确哪些数据过来该应用程序可以处理。
		// ▲应该数据标识，所以10000必须写
		DatagramSocket ds = new DatagramSocket(10000);
		/* 这一句放在循环里面会报BindException，因为new了新的DatagramSocket ，但是端口号没改 */
		while (true) {// ▲因为里面有个阻塞方法，所以这里不会死循环
			// 2,定义数据包。用于存储接收到的字节数据。
			byte[] buf = new byte[1024];
			// 使用接受数据包的DatagramPacket构造函数
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			// 3，通过服务的receive方法将收到数据存入数据包中。
			ds.receive(dp);// 阻塞式方法，没有数据就一直等，有数据就notify
			// 4，▲通过数据包的方法获取其中的数据。
			String ip = dp.getAddress().getHostAddress();
			// 只取有数据的那一段，而不是1024byte
			String data = new String(dp.getData(), 0, dp.getLength());
			int port = dp.getPort();
			System.out.println(ip + "::" + data + "::" + port);
		}
		// ds.close();//5，关闭资源
	}

	/** 实现控制台输入，然后再发送 */
	@Test
	public void testUDPSend2() throws Exception {
		DatagramSocket ds = new DatagramSocket();
		BufferedReader bufr = new BufferedReader(new InputStreamReader(
				System.in));
		String line = null;
		while ((line = bufr.readLine()) != null) {
			if ("886".equals(line))
				break;
			byte[] buf = line.getBytes();
			DatagramPacket dp = new DatagramPacket(buf, buf.length,
					InetAddress.getByName("192.168.1.109"), 10001);
			ds.send(dp);
		}
		ds.close();
	}

	@Test
	public void testUDPRece2() throws Exception {
		DatagramSocket ds = new DatagramSocket(10001);
		while (true) {
			byte[] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			ds.receive(dp);
			String ip = dp.getAddress().getHostAddress();
			String data = new String(dp.getData(), 0, dp.getLength());
			System.out.println(ip + "::" + data);
		}
	}

	/** 测试时应该先开服务端 */
	@Test
	public void testTcpClient() throws Exception {
		// 创建客户端的socket服务。指定目的主机和端口
		Socket s = new Socket("192.168.1.109", 10003);
		// ▲如果这步成功，则通路建立了，同时产生socket流，socket里面有输入流和输出流
		// ▲为了发送数据，应该【获取socket流中的输出流】。
		OutputStream out = s.getOutputStream();
		out.write("tcp ge men lai le ".getBytes());
		s.close();
	}

	@Test
	public void testTcpServer() throws Exception {
		// 建立服务端socket服务。并监听一个端口。
		ServerSocket ss = new ServerSocket(10003);
		// 通过accept方法获取连接过来的客户端对象。
		while (true) {
			Socket s = ss.accept();// 服务端首先要接受客服端请求
			String ip = s.getInetAddress().getHostAddress();// 得到客户端ip地址
			System.out.println(ip + ".....connected");
			// 获取客户端发送过来的数据，那么要使用客户端对象的读取流来读取数据。
			InputStream in = s.getInputStream();// 这个源是网络流！
			byte[] buf = new byte[1024];
			int len = in.read(buf);
			System.out.println(new String(buf, 0, len));
			s.close();// ★关闭客户端.
		}
		// ss.close();
	}

	/** 客户端和服务端互访：必须先开启服务端 */
	@Test
	public void testTcpClient2() throws Exception {
		Socket s = new Socket("192.168.1.109", 10004);
		OutputStream out = s.getOutputStream();
		out.write("服务端，你好".getBytes());
		InputStream in = s.getInputStream();
		byte[] buf = new byte[1024];
		int len = in.read(buf);// 读取网络源中的信息
		System.out.println(new String(buf, 0, len));
		s.close();
	}

	@Test
	public void testTcpServer2() throws Exception {
		ServerSocket ss = new ServerSocket(10004);
		Socket s = ss.accept();
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip + "....connected");
		InputStream in = s.getInputStream();
		byte[] buf = new byte[1024];
		int len = in.read(buf);
		System.out.println(new String(buf, 0, len));
		OutputStream out = s.getOutputStream();
		Thread.sleep(10000);
		out.write("哥们收到,你也好".getBytes());
		s.close();
		ss.close();
	}
	
	/**需求：客户端给服务端发送文本，服务端会将文本转成大写在返回给客户端，当客户端输入over时，转换结束*/
	@Test
	public void testTcpTransClient() throws Exception {
		Socket s = new Socket("192.168.1.109", 10005);
		// 定义源：读取【键盘数据】的流对象。
		BufferedReader bufr = new BufferedReader(new InputStreamReader(
				System.in));
		// 定义目的，将数据写入到socket输出流。发给服务端。
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);// 打印流
		// 定义一个【socket读取流】，读取服务端返回的大写信息。
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
		String line = null;
		while ((line = bufr.readLine()) != null) {
			if ("over".equals(line))
				break;
			out.println(line);
			String str = bufIn.readLine();
			System.out.println("server:" + str);
		}
		bufr.close();
		s.close();
	}
	@Test
	public void testTcpTransServer() throws Exception {
		ServerSocket ss = new ServerSocket(10005);
		Socket s = ss.accept();
		String ip = s.getInetAddress().getHostAddress();
		System.out.println(ip + "....connected");
		// 读取socket读取流中的数据。
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
		// 目的。socket输出流。将大写数据写入到socket输出流，并发送给客户端。
		PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		String line = null;
		while ((line = bufIn.readLine()) != null) {
			System.out.println(line);
			out.println(line.toUpperCase());
		}
		s.close();
		ss.close();
	}
	
	/**测试：网络字符文件拷贝下载、上传*/
	@Test
	public void testTcpTransTextServer() throws Exception{
	    ServerSocket ss = new ServerSocket(10006);
	    Socket s = ss.accept();
	    String ip = s.getInetAddress().getHostAddress();
	    System.out.println(ip+"....connected");
	    BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
	    PrintWriter out  = new PrintWriter(new FileWriter("server.txt"),true);
	    String line = null;
	    while((line=bufIn.readLine())!=null){
	        //if("over".equals(line))
	            //break;
	        out.println(line);
	    }
	    PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
	    pw.println("上传成功");
	    out.close();
	    s.close();
	    ss.close();
	}
	
	@Test
	public void testTcpTransTextClient() throws Exception {
        Socket s = new Socket("192.168.1.109",10006);
        BufferedReader bufr = new BufferedReader(new FileReader("IPTest.java"));//封装成文件对象
        PrintWriter out = new PrintWriter(s.getOutputStream(),true);//打印流
        String line = null;
        while((line=bufr.readLine())!=null){
            out.println(line);
        }
        s.shutdownOutput();//▲关闭客户端的输出流。相当于给流中加入一个结束标记-1.
        //代替了服务器注释的两行代码
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String str = bufIn.readLine();
        System.out.println(str);
        bufr.close();
        s.close();
	}
	
	/***/
	
}
