package hw.learn.simple.net;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BaiduSearch_02_GetHtml {
	public static void main(String[] args) throws Exception {
		//URL url = new URL("http://www.baidu.com/s?ie=utf-8&bs=23&f=8&rsv_bp=1&wd=贺威&rsv_sug3=1&rsv_sug4=89&rsv_sug1=1&inputT=0");
		URL url = new URL("http://www.baidu.com/s?wd=hewei");//不用设置那么长也是可以的
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();//打开连接-->设置属性
		//设置属性
		conn.setRequestMethod("GET");//必须为GET，用POST将不能访问
		conn.setDoOutput(true);
		InputStream inStream = conn.getInputStream();//去连接，实际发送请求的代码段就在这里 
		
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		outStream.close();
		inStream.close();
		System.out.println(new String(data, "utf-8"));
	}
}
