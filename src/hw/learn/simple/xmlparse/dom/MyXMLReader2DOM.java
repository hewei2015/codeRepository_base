package hw.learn.simple.xmlparse.dom;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class MyXMLReader2DOM {
	//public static void main(String arge[]) {
	@Test
	public void test_MyXMLReader2DOM(){
		long lasting = System.currentTimeMillis();
		try {
			File f = new File("src/hw/learn/simple/xmlparse/NewFile1.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(f);
			//System.out.println(doc);
			NodeList nl = doc.getElementsByTagName("VALUE");
			for (int i = 0; i < nl.getLength(); i++) {
				System.out.print("车牌号码：" + doc.getElementsByTagName("NO").item(i).getFirstChild().getNodeValue());
				System.out.println("车主地址：" + doc.getElementsByTagName("ADDR").item(i).getFirstChild().getNodeValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}