package hw.learn.cert;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Enumeration;

import javax.crypto.Cipher;

/**
 * 读取数字证书RSA密钥对工具类
 */
public class RSAUtil {

	/**
	 * 从KEYSTORE文件中取得公钥
	 */
	public PublicKey getPubKeyFromKS(String keyStoreFile, String storeFilePass, String keyAlias) throws Exception {

		// 读取密钥是所要用到的工具类
		KeyStore ks;
		// 公钥类所对应的类
		PublicKey pubkey = null;
		// 得到实例对象
		ks = KeyStore.getInstance("JKS");
		FileInputStream fin;
		// 读取JKS文件
		fin = new FileInputStream(keyStoreFile);
		// 读取公钥
		ks.load(fin, storeFilePass.toCharArray());
		java.security.cert.Certificate cert = ks.getCertificate(keyAlias);
		pubkey = cert.getPublicKey();

		return pubkey;
	}

	/**
	 * 从公钥证书中读取公钥
	 */
	public PublicKey getPubKeyFromCRT(String crtFileName) throws Exception {
		InputStream is = new FileInputStream(crtFileName);
		CertificateFactory cf = CertificateFactory.getInstance("x509");
		Certificate cerCert = cf.generateCertificate(is);
		return cerCert.getPublicKey();
	}

	/**
	 * 通过PFX文件获得公钥
	 * 
	 * @param PFX文件路径
	 * @param PublicKey
	 * @return
	 */
	public PublicKey getPukformPfx(String strPfx, String strPassword) throws Exception {
		PublicKey pubkey = null;
		KeyStore ks = getKsformPfx(strPfx, strPassword);
		String keyAlias = getAlsformPfx(strPfx, strPassword);
		Certificate cert = ks.getCertificate(keyAlias);
		pubkey = cert.getPublicKey();
		return pubkey;
	}

	/**
	 * 从KEYSTORE文件中取得私钥
	 * 
	 * @param keyStoreFile
	 * @param storeFilePass
	 * @param keyAlias
	 * @param keyAliasPass
	 * @return
	 * @throws Exception
	 */
	public PrivateKey getPriKeyFromKS(String keyStoreFile, String storeFilePass, String keyAlias, String keyAliasPass) throws Exception {
		KeyStore ks;
		PrivateKey prikey = null;
		ks = KeyStore.getInstance("JKS");
		FileInputStream fin;
		fin = new FileInputStream(keyStoreFile);
		// 先打开文件
		ks.load(fin, storeFilePass.toCharArray());
		// 通过别名和密码得到私钥
		prikey = (PrivateKey) ks.getKey(keyAlias, keyAliasPass.toCharArray());
		return prikey;
	}

	/**
	 * 通过PFX文件获得私钥
	 * 
	 * @param 文件路径
	 * @param PFX密码
	 * @return PrivateKey
	 */
	public PrivateKey getPvkformPfx(String strPfx, String strPassword) throws Exception {
		PrivateKey prikey = null;
		char[] nPassword = null;
		if ((strPassword == null) || strPassword.trim().equals("")) {
			nPassword = null;
		} else {
			nPassword = strPassword.toCharArray();
		}
		KeyStore ks = getKsformPfx(strPfx, strPassword);
		String keyAlias = getAlsformPfx(strPfx, strPassword);
		prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
		return prikey;
	}

	/**
	 * 通过PFX文件获得KEYSTORE
	 * 
	 * @param 文件路径
	 * @param PFX密码
	 * @return KeyStore
	 */
	public static KeyStore getKsformPfx(String strPfx, String strPassword) throws Exception {
		FileInputStream fis = null;
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

		KeyStore ks = KeyStore.getInstance("PKCS12", "BC");
		fis = new FileInputStream(strPfx);
		// If the keystore password is empty(""), then we have to set
		// to null, otherwise it won't work!!!
		char[] nPassword = null;
		if ((strPassword == null) || strPassword.trim().equals("")) {
			nPassword = null;
		} else {
			nPassword = strPassword.toCharArray();
		}
		ks.load(fis, nPassword);
		if (null != fis) {

			fis.close();

		}
		return ks;

	}

	/**
	 * 通过PFX文件获得别名
	 * 
	 * @param 文件路径
	 * @param PFX密码
	 * @return 别名
	 */
	public static String getAlsformPfx(String strPfx, String strPassword) throws Exception {
		String keyAlias = null;
		KeyStore ks = getKsformPfx(strPfx, strPassword);
		Enumeration<String> enumas = ks.aliases();
		keyAlias = null;
		// we are readin just one certificate.
		if (enumas.hasMoreElements()) {
			keyAlias = (String) enumas.nextElement();
		}
		return keyAlias;
	}

	/**
	 * 用私钥对文件进行加密
	 * 
	 * @param filePath 需要加密的文件路径
	 * @param PriKey 私钥
	 * @param outFilePath  加密后的文件路径
	 * @param needEncode 是否需要base64编码(解决传输过程中乱码问题,否则只有文本格式文件解密成功)
	 * @throws Exception
	 */
	public void encryptWithPrv(String filePath, PrivateKey PriKey, String outFilePath, boolean needEncode) throws Exception {

		// A.加密原始文件
		File file = new File(filePath);
		FileInputStream in = new FileInputStream(file);
		ByteArrayOutputStream bout = new ByteArrayOutputStream(); // 创建一个字节输出流

		byte[] tmpbuf = new byte[1024];
		int count = 0, count1 = 0;
		while ((count = in.read(tmpbuf)) != -1) {
			bout.write(tmpbuf, 0, count);
			tmpbuf = new byte[1024];
			count1++;
		}
		in.close();
		RSAPrivateKey priKey = (RSAPrivateKey) PriKey;
		byte[] orgData = bout.toByteArray();
		byte[] orgDataBase64;
		if (needEncode) {
			// 1.对文件数据进行Base64转码,解决TXT外的其他格式加密解密后出现乱码的错误的
			orgDataBase64 = Base64.encode(orgData);
		} else {
			orgDataBase64 = orgData;
		}
		// 2. 用私钥对数据流进行加密
		byte[] raw = encrypt(priKey, orgDataBase64);
		// 3.输出加密后的数据流到文件
		if (null != outFilePath && !"".equals(outFilePath)) {
			file = new File(outFilePath);
			OutputStream out = new FileOutputStream(file);
			out.write(raw);
			out.close();
		}
	}

	/**
	 * 用公钥进行解密
	 * 
	 * @param filePath
	 * @param pubKey
	 * @param needDecode
	 * @param outFilePath
	 * @return
	 * @throws Exception
	 */
	public byte[] decryptWithPub(String filePath, PublicKey pubKey, boolean needDecode, String outFilePath) throws Exception {
		byte[] dataBase64 = null;
		// 从私钥pfx中取得公钥
		RSAPublicKey rsaPubKey = (RSAPublicKey) pubKey;
		byte[] pubModBytes = rsaPubKey.getModulus().toByteArray();
		byte[] pubPubExpBytes = rsaPubKey.getPublicExponent().toByteArray();
		RSAPublicKey recoveryPubKey = generateRSAPublicKey(pubModBytes, pubPubExpBytes);
		// 解密文件
		File encFile = new File(filePath);
		// 1. 读取文件到byte流
		byte[] encBytes = Base64.readBytes(encFile);
		// 2.用公钥对byte流进行解密
		byte[] data = decrypt(recoveryPubKey, encBytes);
		// 3.对解密后的数据进行Base64解码
		if (needDecode) {
			dataBase64 = Base64.decode(data);
		} else {
			dataBase64 = data;
		}
		// 4.输出解密后的文件
		if (null != outFilePath && !"".equals(outFilePath)) {
			File file = new File(outFilePath);
			OutputStream out = new FileOutputStream(file);
			out = new FileOutputStream(file);
			out.write(dataBase64);
			out.flush();
			out.close();
		}
		return dataBase64;
	}

	/**
	 * 
	 */
	public static RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) throws Exception {
		KeyFactory keyFac = null;
		keyFac = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
		// getInstance(String algorithm, Provider provider)
		// 为指定提供程序中的指定算法生成KeyFactory 对象
		RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
		return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
		// generatePublic(KeySpec keySpec) 根据所提供的密钥规范（密钥材料）生成公钥对象。
	}

	public static byte[] encrypt(Key key, byte[] data) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
		// getInstance(String transformation, String provider)
		// 创建一个实现指定转换的 Cipher 对象，该转换由指定的提供程序提供。
		cipher.init(Cipher.ENCRYPT_MODE, key);
		// ENCRYPT_MODE 用于将 cipher 初始化为加密模式的常量。
		// init(int opmode, Key key) 用密钥初始化此 cipher。
		int blockSize = cipher.getBlockSize();
		// getBlockSize
		// public final int getBlockSize()返回块的大小（以字节为单位）
		// 返回：
		// 块的大小（以字节为单位），如果基础算法不是块 cipher，则返回 0
		// 获得加密块大小，如:加密前数据为128个byte，
		// 而key_size=1024 加密块大小为127 byte,加密后为128个byte;因此共有2个加密块，第一个127
		// byte第二個为1個byte
		int outputSize = cipher.getOutputSize(data.length);// 获得加密块加密后块大小
		int leavedSize = data.length % blockSize;
		int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
		byte[] raw = new byte[outputSize * blocksSize];
		int i = 0;
		while (data.length - i * blockSize > 0) {
			if (data.length - i * blockSize > blockSize)
				cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
			else
				cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
			// 这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把
			// byte[]放到ByteArrayOutputStream中，而最后doFinal的时候才将所有的byte[]进行加密，可是到
			// 了此时加密块大小很可能已经超出了OutputSize所以只好用dofinal方法。
			i++;
		}
		return raw;
	}

	/**
	 * 解密数据
	 * 
	 * @param Key
	 * @param byte[] raw
	 */
	public static byte[] decrypt(Key key, byte[] raw) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
		cipher.init(cipher.DECRYPT_MODE, key);
		int blockSize = cipher.getBlockSize();
		ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
		int j = 0;
		while (raw.length - j * blockSize > 0) {
			bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
			j++;
		}
		return bout.toByteArray();
	}
}
