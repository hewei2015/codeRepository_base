package hw.learn.cryptoAdecrypt;

import static org.junit.Assert.*;

import org.junit.Test;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Coder {

	public final static String ENCODING = "UTF-8";

	/**
	 * 使用sun-misc中的方法
	 * 问题：[sun.misc.BASE64Encoder找不到jar包的解决方法](http://blog.csdn.net/jbxiaozi/article/details/7351768)
	 * 右键项目->属性->java bulid path->jre System Library->access rules->resolution选择accessible，下面填上** 点击确定
	 */
	public static String encode(String data) throws Exception {
		BASE64Encoder encoder = new BASE64Encoder();
		byte[] b = data.getBytes(ENCODING);
		return encoder.encodeBuffer(b);
	}

	public static String decode(String data) throws Exception {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b = decoder.decodeBuffer(data);
		return new String(b, ENCODING);
	}
	
	/**
	 * 测试sun-misc中Base64编码与解码
	 */
	@Test
	public void test_sun_misc() throws Exception {
		String inputStr = "Java加密与解密的艺术";
		System.err.println("原文:\n\t" + inputStr);
		// 进行Base64编码
		String code = Base64Coder.encode(inputStr);
		System.err.println("Base64编码后:\n\t" + code);
		// 进行Base64解码
		String outputStr = Base64Coder.decode(code);
		System.err.println("Base64解码后:\n\t" + outputStr);
		// 验证Base64编码解码一致性
		assertEquals(inputStr, outputStr);
	}
	
}