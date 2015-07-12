package hw.learn.simple.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

/**
 * ★注意：javax.xml.parsers中的和dom4j中的两种形式的Document不能转换
 * 所以在应用时只能用一套xml解析方案，不能交替使用，否则报：ClassCastException异常
 * dom4j具体细节操作：参见http://www.cnblogs.com/liuling/archive/2013/02/05/dom4jxml.html
 */
public class XmlTest_dom4j {
	private static String path = "D:\\workspace\\java_base\\src\\hw\\learn\\simple\\xml\\student.xml";
	// Begin:使用dom4j后程序变得更简单
	@Test
	public void test_String2Xml_dom4j() throws DocumentException {
		String xmlStr = new String("<?xml version=\"1.0\" encoding=\"GB2312\"?><students><student sn=\"01\"><name>张三</name><age>18</age></student><student sn=\"02\"><name>李四</name><age>20</age></student></students>");
		Document document = DocumentHelper.parseText(xmlStr);// 必须引入dom4j的Document
	}

	@Test
	/**
	 * 最普通的方法：将xml文件看成普通文本文件输出字符信息
	 */
	public void test_OrdTextReadXml() throws Exception {
		File f = new File(path);
		InputStream in = new FileInputStream(f);
		byte[] b = new byte[1024];
		in.read(b);
		in.close();
		System.out.println(new String(b));
	}

	@Test
	/**
	 * ★★方法2：解析后读取xml文件中所有节点信息，
	 */
	public void test_Xml2String_dom4j() throws Exception {
		SAXReader sr = new SAXReader();// 获取读取xml的对象。
		Document doc = sr.read(path);// 得到xml所在位置。然后开始读取。并将数据放入doc中
		Element el_root = doc.getRootElement();// 向外取数据，获取xml的根节点。
		Iterator it = el_root.elementIterator();// 从根节点下依次遍历，获取根节点下所有子节点
		while (it.hasNext()) {// 遍历该子节点
			Object o = it.next();// 再获取该子节点下的子节点
			Element el_row = (Element) o;
			String s = el_row.getText();
			Iterator it_row = el_row.elementIterator();
			while (it_row.hasNext()) {// 遍历节点
				Element el_ename = (Element) it_row.next();// 获取该节点下的所有数据。
				System.out.println(el_ename.getText());
			}
		}
	}

	@Test
	/**
	 * 方法三：使用elements方法进行xml的读取，相当于条件查询，可以根据不同的节点，利用for循环查询该节点下所有的数据。
	 * 依据不同的xml文件而定
	 */
	public void test_ByElement() throws Exception {
		SAXReader sr = new SAXReader();// 获取读取方式
		Document doc = sr.read(path);// 读取xml文件，并且将数据全部存放到Document中
		Element root = doc.getRootElement();// 获取根节点
		List list = root.elements("student");// 根据根节点，将根节点下 student中的所有数据放到list容器中。
		for (Object obj : list) {// 这种遍历方式，是jdk1.5以上的版本支持的遍历方式
			Element row = (Element) obj;
			List list_name = row.elements("name");// 获取name节点下所有的内容，存入list_row容器中
			for (Object objempno : list_name) {
				Element el_empno = (Element) objempno;
				System.out.println(el_empno.getName() + ": " + el_empno.getText());// 获取节点下的数据。
			}
		}
	}
	
	@Test
	public void test_bySelectNode() throws Exception {
		String elementpath = new String("name");// 使用selectNodes获取所要查询xml的节点。
		SAXReader sr = new SAXReader();
		Document doc = sr.read(path);
		List list = doc.selectNodes(elementpath);
		for (Object obj : list) {// 遍历节点，获取节点内数据。
			Element el = (Element) obj;
			System.out.println(el.getText());
		}
	}

}
