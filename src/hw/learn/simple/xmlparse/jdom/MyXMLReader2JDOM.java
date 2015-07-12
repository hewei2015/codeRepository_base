package hw.learn.simple.xmlparse.jdom;

import java.io.File;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.junit.Test;

public class MyXMLReader2JDOM {
	//public static void main(String arge[]) {
	@Test
	public void test_MyXMLReader2JDOM(){
		long lasting = System.currentTimeMillis();
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new File("src/hw/learn/simple/xmlparse/NewFile1.xml"));
			Element foo = doc.getRootElement();
			List allChildren = foo.getChildren();
			for (int i = 0; i < allChildren.size(); i++) {
				System.out.print("车牌号码：" + ((Element) allChildren.get(i)).getChild("NO").getText());
				System.out.println("车主地址：" + ((Element) allChildren.get(i)).getChild("ADDR").getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}