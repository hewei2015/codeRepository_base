package hw.learn.simple.net;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

public class SocketSend {
	@Test
	// 模拟服务端
	public void testServer() throws IOException {
		// 19.106.0.141:8101
		int port = 8101;
		// 定义一个ServerSocket监听在端口8101上
		ServerSocket server = new ServerSocket(port);
		// server尝试接收其他Socket的连接请求，server的accept方法是阻塞式的
		Socket socket = server.accept();
		// 跟客户端建立好连接之后，我们就可以获取socket的InputStream，并从中读取客户端发过来的信息了。
		Reader reader = new InputStreamReader(socket.getInputStream());
		char chars[] = new char[64];
		int len;
		StringBuilder sb = new StringBuilder();
		while ((len = reader.read(chars)) != -1) {
			sb.append(new String(chars, 0, len));
		}
		System.out.println("from client: " + sb);
		reader.close();
		socket.close();
		server.close();
	}

	@Test
	public void testSend() throws IOException {
		// 为了简单起见，所有的异常都直接往外抛
		String host = "19.106.0.141"; // 要连接的服务端IP地址
		int port = 8101; // 要连接的服务端对应的监听端口
		// 与服务端建立连接
		Socket client = new Socket(host, port);
		// 建立连接后就可以往服务端写数据了
		Writer writer = new OutputStreamWriter(client.getOutputStream());
		String message = "asfa";
		writer.write("message");
		writer.flush();// 写完后要记得flush
		writer.close();
		client.close();

	}
}
