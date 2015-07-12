package hw.learn.simple.xmlparse.dom4j;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class MyXMLReader2DOM4J {
	// public static void main(String arge[]) {
	@Test
	public void test_MyXMLReader2DOM() {
		long lasting = System.currentTimeMillis();
		try {
			File f = new File("src/hw/learn/simple/xmlparse/NewFile1.xml");
			SAXReader reader = new SAXReader();
			Document doc = reader.read(f);
			Element root = doc.getRootElement();
			Element foo;
			for (Iterator i = root.elementIterator("VALUE"); i.hasNext();) {
				foo = (Element) i.next();
				System.out.print("车牌号码：" + foo.elementText("NO"));
				System.out.println("车主地址：" + foo.elementText("ADDR"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}