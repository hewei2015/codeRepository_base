package hw.learn.simple.xml;

import java.io.ByteArrayOutputStream;
import java.security.PrivateKey;
import java.util.Collections;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class SignXml {
	// 参考文档：http://www.javacodegeeks.com/2013/10/xml-security-with-digital-signature-in-java.html
	/**
	 * @param doc 待签名的xml文档
	 * @param privateKey  用于对xml签名的私钥
	 * @return 已签名的xml文档
	 */
	public static Document signXmlDoc(Document doc, PrivateKey privateKey) throws Exception {
		// 创建XML签名工厂
		XMLSignatureFactory xmlSigFactory = XMLSignatureFactory.getInstance("DOM");
		DOMSignContext domSignCtx = new DOMSignContext(privateKey, doc.getDocumentElement());
		System.out.println(domSignCtx);
		javax.xml.crypto.dsig.Reference ref = null;
		SignedInfo signedInfo = null;
		try {
			ref = xmlSigFactory.newReference("#CUSTINFOREQ", xmlSigFactory.newDigestMethod(DigestMethod.SHA1, null), Collections.singletonList(xmlSigFactory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)), null, null);
			signedInfo = xmlSigFactory.newSignedInfo(xmlSigFactory.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null), xmlSigFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null), Collections.singletonList(ref));
			System.out.println(signedInfo);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		KeyInfo ki = null;
		// 创建新的XML签名
		XMLSignature xmlSignature = xmlSigFactory.newXMLSignature(signedInfo, ki);
		try {
			// 对文档签名
			xmlSignature.sign(domSignCtx);
			// ★打印出来xml文档：查看签名信息！【org.dom.Document->String】
			System.out.println("#Begin:get signedXmlDoc********************************");
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			t.setOutputProperty("encoding", "GB23121");
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			t.transform(new DOMSource(doc), new StreamResult(bos));
			String xmlStr = bos.toString();
			System.out.println(xmlStr);
			System.out.println("#End:get signedXmlDoc********************************");
			return doc;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
}
