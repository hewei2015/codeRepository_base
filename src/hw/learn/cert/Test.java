package hw.learn.cert;


import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 测试类
 */
public class Test {
	public static void main(String[] args) {
		try {
			// 工具类
			RSAUtil rsa = new RSAUtil();
			// 从jks私钥中获取私钥加密串
			PrivateKey priKeyFromKs = rsa.getPriKeyFromKS("d:\\myalias.keystore", "123456", "myalias", "123456");
			// 从jks私钥中获取公钥解密串
			PublicKey pubKeyFromKS = rsa.getPubKeyFromKS("d:\\myalias.keystore", "123456", "myalias");
			// 从crt公钥中获取公钥解密串
			PublicKey pubKeyFromCrt = rsa.getPubKeyFromCRT("d:\\myalias.crt");

			rsa.encryptWithPrv("d:\\file.xls", priKeyFromKs, "d:\\file_encWithKSPri.xls", true);
			// 用jks公钥串解密
			rsa.decryptWithPub("d:\\file_encWithKSPri.xls", pubKeyFromKS, true, "d:\\file_encWithKSPri_decKs.xls");
			// 用crt公钥串解密
			rsa.decryptWithPub("d:\\file_encWithKSPri.xls", pubKeyFromCrt, true, "d:\\file_encWithKSPri_decCrt.xls");

			/*
			 * //不使用base64编码 则只能加密文本格式文件，否则解密的时候会出错
			 * rsa.encryptWithPrv("d:\\file.xls",priKeyFromKs,"d:\\file_encWithKSPri_nobase64.xls",false);
			 * rsa.decryptWithPub("d:\\file_encWithKSPri_nobase64.xls", pubKeyFromKS, false, "d:\\file_encWithKSPri_nobase64_decKs.xls");
			 * rsa.decryptWithPub("d:\\file_encWithKSPri_nobase64.xls", pubKeyFromCrt, false, "d:\\file_encWithKSPri_nobase64_decCrt.xls");
			 */

			// 从PFX私钥中获取私钥加密串
			PrivateKey priKeyFromPfx = rsa.getPvkformPfx("d:\\myalia2.pfx", "123456");

			// 从Pfx私钥中获取公钥解密串
			PublicKey pubKeyFromPfx = rsa.getPukformPfx("d:\\myalia2.pfx", "123456");
			// 从Key公钥钥中获取公钥解密串
			// PublicKey pubKeyFromKey = rsa.getPubKeyFromCRT("d:\\myalia2.key");

			// 用私钥串加密
			rsa.encryptWithPrv("d:\\file.xls", priKeyFromPfx, "d:\\file_encWithPfxPri.xls", true);
			// 用pfx公钥串解密
			rsa.decryptWithPub("d:\\file_encWithPfxPri.xls", pubKeyFromPfx, true, "d:\\file_encWithPfxPri_decPfx.xls");
			// 用crt公钥串解密
			rsa.decryptWithPub("d:\\file_encWithPfxPri.xls", pubKeyFromCrt, true, "d:\\file_encWithPfxPri_decCrt.xls");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
