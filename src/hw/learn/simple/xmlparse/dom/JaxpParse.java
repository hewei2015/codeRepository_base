package hw.learn.simple.xmlparse.dom;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//参考文件：http://blog.csdn.net/redarmy_chen/article/details/12912065
//1.javax.xml.parsers 包中的DocumentBuilderFactory用于创建DOM模式的解析器对象 ， DocumentBuilderFactory是一个抽象工厂类，它不能直接实例化，但该类提供了一个newInstance方法 ，这个方法会根据本地平台默认安装的解析器，自动创建一个工厂的对象并返回
//2.调用 DocumentBuilderFactory.newInstance() 方法得到创建 DOM 解析器的工厂。
//3.调用工厂对象的 newDocumentBuilder方法得到 DOM 解析器对象。
//4.调用 DOM 解析器对象的 parse() 方法解析 XML 文档，得到代表整个文档的 Document 对象，进行可以利用DOM特性对整个XML文档进行操作了。
//总结：DocumentBuilderFactory[newInstance()]->newDocumentBuilder() ->parse

public class JaxpParse {
	/**
	 * 获取XML的根节点对象
	 */
	@Test
	public void test_listAllNode() throws Exception {
		// 调用 DocumentBuilderFactory.newInstance() 方法得到创建 DOM 解析器的工厂
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		// 调用工厂对象的 newDocumentBuilder方法得到 DOM 解析器对象
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		String path = "D:\\workspace\\java_base\\src\\hw\\learn\\simple\\xmlparse\\jaxp\\JaxpTest.xml";
		FileInputStream is = new FileInputStream(new File(path));
		// 得到Document
		Document document = builder.parse(is);
		// document.getDocumentElement()获取根节点的元素对象
		Element root = document.getDocumentElement();
		// 遍历根节点下面的所有子节点
		listNodes(root);
	}

	/**
	 * 遍历根据节点对象下面的所有的节点对象
	 */
	// /总结：
	// Node.getNodeType() // 判断是否是元素节点
	// Element.hasAttributes() // 判断此元素节点是否有属性
	// Element.getAttributes() // 获取属性节点的集合，返回NamedNodeMap类型
	// NamedNodeMap.getLength()//得到NamedNodeMap集合的元素个数
	// NamedNodeMap.item(k) // 获取具体的k个属性节点
	// Attr.getNodeName() //获得某个属性结点的结点名字
	// Attr.getNodeValue() //获得某个属性结点的结点值
	// Element.getChildNodes();// 获取元素节点的所有孩子节点
	public void listNodes(Node node) {
		// 节点是什么类型的节点
		if (node.getNodeType() == Node.ELEMENT_NODE) {// 判断是否是元素节点
			Element element = (Element) node;
			// 判断此元素节点是否有属性
			if (element.hasAttributes()) {
				// 获取属性节点的集合
				NamedNodeMap namenm = element.getAttributes();// Node
				// 遍历属性节点的集合
				for (int k = 0; k < namenm.getLength(); k++) {
					// 获取具体的某个属性节点
					Attr attr = (Attr) namenm.item(k);
					System.out.println("name:::" + attr.getNodeName() + " value::" + attr.getNodeValue() + "  type::" + attr.getNodeType());
				}
			}
			// 获取元素节点的所有孩子节点
			NodeList listnode = element.getChildNodes();
			// 遍历
			for (int j = 0; j < listnode.getLength(); j++) {
				// 得到某个具体的节点对象
				Node nd = listnode.item(j);
				System.out.println("name::" + nd.getNodeName() + "  value:::" + nd.getNodeValue() + "  type:::" + nd.getNodeType());
				// 重新调用遍历节点的操作的方法
				listNodes(nd);
			}
		}
	}
}
