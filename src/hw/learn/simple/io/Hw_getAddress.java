package hw.learn.simple.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class Hw_getAddress {

	@Test
	public void test_readLoalFileAtThisProject() throws Exception {
		// 依据文件得到的目标路径字符串
		// String path="D:\\HwTemp\\dongguan_test\\reqExample1.xml";
		// String path = this.getClass().getClassLoader().getResource("").getPath(); // /D:/workspace/java_base/bin/

		//★★注意：字符串里 \\表示一个"\" 但relaceAll里的 第一个参数是一个正则表达式，在正则里 \\\\表示一个"\"
		//工程路径+文件所在工程的相对路径（用）
		String path = System.getProperty("user.dir")+"\\src\\file.txt";
		System.out.println();
		//★将所有的上面得到的路径中"\"转换成"\\"
		path = path.replaceAll("\\\\","\\\\\\\\");
		System.out.println(path);
		FileInputStream fis = new FileInputStream(new File(path));//D:\\workspace\\java_base\\src\\file.txt
	}
	
	@Test
	public void test_readAddress() throws FileNotFoundException {
		String path = System.getProperty("user.dir") + "\\WebContent\\resources\\reqExample1.xml";
		System.out.println(path);
		path = path.replaceAll("\\\\", "\\\\\\\\");
		System.out.println(path);

		System.out.println(this.getClass().getClassLoader().getResource(".").getPath());// 得到的是项目的class文件路径
		System.out.println(this.getClass().getClassLoader().getResource("").getPath());// 得到的是项目的class文件路径
		System.out.println(this.getClass().getResource("/").getPath());// 得到的是项目的class文件路径
		System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
		System.out.println(Thread.currentThread().getContextClassLoader().getResource(".").getPath());
		System.out.println(this.getClass().getResource("").getPath());// ★得到当前文件所在的包路径
		System.out.println(this.getClass().getResource(".").getPath());// ★得到当前文件所在的包路径
	}
	/**
	 * 获取WEB-INF目录下面web.xml文件的路径，如果发布到tomcat中，得到的结果又不一样（与tomcat的路径有关）
	 */
	@Test
	public void test_getXmlPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		path = path.replace('/', '\\'); // 将/换成\
		path = path.replace("file:", ""); // 去掉file:
		path = path.replace("classes\\", ""); // 去掉class\
		path = path.substring(1); // 去掉第一个\,如 \D:\JavaWeb...
		path += "web.xml";
		System.out.println(path);
	}
	
	@Test
	public void test_getPath() throws IOException{
		File directory = new File("");// 设定为当前文件夹 
		System.out.println(directory.getCanonicalPath());// 获取标准的路径 
		System.out.println(directory.getAbsolutePath());// 获取绝对路径 
		File panFu = new File("/");// 设定为当前文件夹 
		System.out.println(panFu.getCanonicalPath());//得到所在磁盘
	}
}
