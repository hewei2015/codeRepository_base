package hw.learn.simple.net;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

public class ClientSendSocket {
	public static void main(String[] args) {
		try {
			/*String message = "<COSPN><HEAD><BUSITYPE>00001</BUSITYPE></HEAD><QUEST><TIMESTAMP>20150423124723</TIMESTAMP>"
					+ "<IDNUMBER>440306201306289999</IDNUMBER><IDTYPE>A</IDTYPE><NAME>张三</NAME>"
					+ "<STRCILENTSIGNEDDATA>nT/auXCu5zGgMmFf/x3Nl6f2XQgQLzHIJUy0fm2Ar+aeJjTt5jr+EbFUi+9s/"
					+ "TK96LQhwk1yVIthpdL40vPqzFLAJoPP81/wESKGHScJ3CSlMBMzcaB0mOOLzEys7h0Evpc2xL03+QZVJtkICb3c1dEfoCVfh0ORMuw909RnWSI="
					+ "</STRCILENTSIGNEDDATA><STRSERVERRAN>SH='1';SN='您正在进行';IN='0';SI='经办人注册';|SH='0';SN='RandomNum';IN='0';SI='q6Y7h7ABghQktoVkQ21t+UEIrDqGB4yG';|</STRSERVERRAN><CERTENTITY>MIICjzCCAfigAwIBAgIEBMpaDDANBgkqhkiG9w0BAQQFADBHMRYwFAYDVQQDEw16aGFuZ3lhbiB0ZXN0MQ8wDQYDVQQKEwZ0ZXN0IG8xDzANBgNVBAsTBlRlc3RPdTELMAkGA1UEBhMCQ04wHhcNMTQwMTA4MjM0NTEzWhcNMTkwMTA5MDM0NTEzWjBBMRIwEAYDVQQDEwlKMDAxMjcwMTMxEDAOBgNVBAsTB05FVEJBTksxDDAKBgNVBAoTA0NDQjELMAkGA1UEBhMCQ04wgZ8wDQYJKoZIhvcNAQEBBQADgY0AMIGJAoGBAN2gleMgyDKHqqUP8kQI91uq3kGEc40S3gyVAVTOMU8ADdi30MimyWtmxK/+UDX5U4917iekdQ1LUlbgTEzn91gLdsgBeNkVzp/TSShl8mXDK/i4BLj6E2xq1ZsXuedqPcRsrzHzfUVk9yNjI4quZf/ZBI/1AipOpFt819rVVzqPAgMBAAGjgY0wgYowEQYJYIZIAYb4QgEBBAQDAgWgMB8GA1UdIwQYMBaAFFacTm0pi3I1hcbo0WAs0nrVczBzMAkGA1UdEwQCMAAwCwYDVR0PBAQDAgbAMB0GA1UdDgQWBBQcqAtkl94SiL+jRRs+tXH6YLY2ajAdBgNVHSUEFjAUBggrBgEFBQcDAgYIKwYBBQUHAwQwDQYJKoZIhvcNAQEEBQADgYEAJimsHPStZBT9wPWSGxoRQiEYXNLd4OF/yFUFaLAL16SyTE9MSS+ZAp3Mx88gfQqWRmvRU+N0guUYrL9olq+z0Xv8X4tYq8V5xTSUj36+xOXofklPbglECg7ZdLbANa4/2KOBoEYMcGfW4Xtbl2TO6G/D4av33b+0AAUia8tx15w=</CERTENTITY></QUEST></COSPN>";
			*/
			String message = "";
			// String host = "19.106.0.141"; 
			String host = "127.0.0.1";	// 要连接的服务端IP地址
			int port = 8101; // 要连接的服务端对应的监听端口
			// 与服务端建立连接
			Socket client = new Socket(host, port);
			System.out.println("已经建立socket连接");
			// 建立连接后就可以往服务端写数据了
			Writer writer = new OutputStreamWriter(client.getOutputStream());
			writer.write(message);

			// ServerSocket.sendData(ClientSocket,html_text);//http://blog.163.com/snowd_rop/blog/static/115784782201001723619190/

			writer.flush();// 写完后要记得flush
			writer.close();
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("报文发送不成功");
		}
	}

}
