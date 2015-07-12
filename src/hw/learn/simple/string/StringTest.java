package hw.learn.simple.string;

import org.junit.Test;

public class StringTest {
	@Test
	//contains():当且仅当此字符串包含指定的 char 值序列时，返回 true，所以可以将待比较字符串全部变为小写，然后再看是否包含
	
	public void testStringContain(){
		String s = "BBC asdafdfa adsfa";
		String s1 = "asdafds BBC adsadfa";
		if(s.toLowerCase().contains("bbc"))
			System.out.println("包含bbc");
		else 
			System.out.println("不包含bbc");
		if(s1.contains("BBC"))
			System.out.println("包含BBC");
		else 
			System.out.println("不包含BBC");
	}
}
