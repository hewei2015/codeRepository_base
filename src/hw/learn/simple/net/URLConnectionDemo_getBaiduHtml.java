package hw.learn.simple.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 介绍URL连接的详细信息：报文头部和请求属性设置、连接过程
 * 编码流程：URL->openConnection()->设置参数、connect()、getXxx()->OutputStream ->InputStream -> Scanner()
 */
public class URLConnectionDemo_getBaiduHtml {
	public static void main(String[] args) {
		try {
			// 1、通过在 URL上调用 openConnection 方法创建连接对象。
			URL url = new URL("http://www.baidu.com/s?wd=Hewei");
			URLConnection connection = url.openConnection();
			// 最好将URLConnection转化为HttpURLConnection，这里不转化也是可以的
			HttpURLConnection httpConnection = (HttpURLConnection) connection;

			// 2、处理设置参数和一般请求属性，必须在connect()之前完成
			// 设置是否从httpUrlConnection读入，默认情况下是true;
			httpConnection.setDoInput(true);
			// 设置是否向httpUrlConnection输出，如果是post请求，参数要放在 http正文内，因此需要设为true,
			// 默认情况下是false
			httpConnection.setDoOutput(true);
			// 使用缓存，如果是Post请求，应该设置为false
			httpConnection.setUseCaches(false);
			// 设定传送的内容类型是可序列化的java对象
			httpConnection.setRequestProperty("Content-type","application/x-java-serialized-object");
			// ★设定请求的方法为"POST"，默认是GET，比如百度必须用get的方式
			httpConnection.setRequestMethod("GET");

			// 3、使用 connect 方法建立到远程对象的实际连接。
			// httpConnection.connect();//第一种方式
			// 此处getOutputStream会隐含的进行connect(即：如同调用上面的connect()方法，所以在开发中不调用上述的connect()也可以)。
//			OutputStream outStrm = httpConnection.getOutputStream();//一旦打开就不能百度到东西

			// 3.1、远程对象变为可用。远程对象的头字段和内容变为可访问。
			System.out.println(httpConnection.getContentType());
			System.out.println(httpConnection.getContentEncoding());
			System.out.println(httpConnection.getContentLength());
			System.out.println(httpConnection.getDate());
						
			// 4、HttpURLConnection写数据与发送数据问题
			// 现在通过输出流对象构建对象输出流对象，以实现输出可序列化的对象。 
//			ObjectOutputStream objOutputStrm = new ObjectOutputStream(outStrm); 
//			// 向对象输出流写出数据，这些数据将存到内存缓冲区中 
//			objOutputStrm.writeObject(new String("我是测试数据！")); 
//			// 刷新对象输出流，将任何字节都写入潜在的流中（些处为ObjectOutputStream） 
//			objOutputStrm.flush();
//			// 关闭流对象。此时，不能再向对象输出流写入任何数据，先前写入的数据存在于内存缓冲区中, 
//			// 再调用下边的getInputStream()函数时才把准备好的http请求正式发送到服务器 
//			objOutputStrm.close(); 
			// 调用HttpURLConnection连接对象的getInputStream()函数, 将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。
			InputStream inStrm = httpConnection.getInputStream(); //？？<===注意，实际发送请求的代码段就在这里 
			
			System.out.println("================");
			//最原始的输出方式StringBuffer类读取内容
			StringBuffer out = new StringBuffer();   
			byte[] b = new byte[4096];   
			for (int n; (n = inStrm.read(b)) != -1;) {   
				out.append(new String(b, 0, n));   
			}   
			System.out.println(out.toString());
			// 用Scanner类进行扫描的方式读取内容
//			Scanner in = new Scanner(httpConnection.getInputStream());
//			while (in.hasNextLine())
//				System.out.println(in.nextLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}