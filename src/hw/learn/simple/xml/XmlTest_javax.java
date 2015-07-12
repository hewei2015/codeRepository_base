package hw.learn.simple.xml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlTest_javax {
	/**
	 * 如同js一样的获取节点内容
	 */
	public static void listNl(NodeList nl) {
		int len = nl.getLength();
		for (int i = 0; i < len; i++) {
			Element eltStudent = (Element) nl.item(i);
			Node eltName = eltStudent.getElementsByTagName("name").item(0);
			Node eltAge = eltStudent.getElementsByTagName("age").item(0);
			String name = eltName.getFirstChild().getNodeValue();
			String age = eltAge.getFirstChild().getNodeValue();
			System.out.print("姓名:");
			System.out.println(name);
			System.out.print("年龄:");
			System.out.println(age);
			System.out.println("------------------------");
		}
	}

	@Test
	/**
	 * 输出所xml所有节点内容
	 */
	// 示例地址：http://it.chinawin.net/softwaredev/article-16023.html
	public void test_ParseXml1Net_01() throws Exception {
		String path = "D:\\workspace\\java_base\\src\\hw\\learn\\simple\\xml\\student.xml";
		FileInputStream fis = new FileInputStream(new File(path));
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = f.newDocumentBuilder();
		Document doc = builder.parse(fis);
		NodeList nl = doc.getElementsByTagName("student");
		listNl(nl);
	}

	@Test
	/**
	 * 将字符串转化为Document文件
	 */
	public void test_String2Xml() throws Exception {
		// String xmlStr = new String("ASDFADADSD");//报错：[Fatal Error] :1:1: 前言中不允许有内容。
		String xmlStr = new String("<?xml version=\"1.0\" encoding=\"GB2312\"?><students><student sn=\"01\"><name>张三</name><age>18</age></student><student sn=\"02\"><name>李四</name><age>20</age></student></students>");
		StringReader sr = new StringReader(xmlStr);
		InputSource is = new InputSource(sr);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(is);
		System.out.println(doc.getClass());//com.sun.org.apache.xerces.internal.dom.DeferredDocumentImpl
	}

	// Begin:使用最原始的javax.xml.parsers，标准的jdk api
	/**
	 * 读取本地文件xml文件为Document对象
	 */
	public static Document test_readXml() throws Exception {
		String path = "D:\\workspace\\java_base\\src\\hw\\learn\\simple\\xml\\student.xml";
		FileInputStream fis = new FileInputStream(new File(path));
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = f.newDocumentBuilder();
		Document doc = builder.parse(fis);
		System.out.println(doc);// ★★[#document: null]注意这里不是打印出来xml文件内容，并不代表没有读到xml文件
		return doc;
	}

	@Test
	/**
	 * 将Document转化为字符串
	 */
	public void test_Xml2String() throws Exception {
		Document doc = this.test_readXml();
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();
		t.setOutputProperty("encoding", "UTF-8");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		t.transform(new DOMSource(doc), new StreamResult(bos));
		String xmlStr = bos.toString();
		System.out.println(xmlStr);
	}
}
