package hw.learn.simple.grammer;


import org.junit.Test;

public class Other {
	@Test
	public void testType() {
		short s1 = 1;// �Զ�����Ϊ���ʽ����
		System.out.println(s1);// 1
		int s2 = (int) 1.2;// ����ǿת�����򱨴?int���Ͳ����Զ�����Ϊshort����
		System.out.println(s2);// 1
		s1 = (short) (s1 + 1);// ����ǿת�����򱨴�
		// System.out.println(s1);//2
		// += ��java���Թ涨�������java�����������������⴦��
		s1 += 1; // ����s1 = s1+1;-->������-->s1=(short)(s1+1);
		System.out.println(s1);// 3
	}

	/**
	 * final�ؼ�������һ������ʱ����ָ���ñ������ܱ䣬���ñ�����ָ��Ķ����е����ݻ��ǿ��Ըı��
	 */
	@Test
	public void testFinal() {
		final StringBuffer a = new StringBuffer("immutable");
		// a = new StringBuffer("");// ���?��������new һ������
		a.append(" broken!"); // ��ȷ�������޸����ж���
		System.out.println(a);

	}

	public static class VariantTest{///ע����������classΪʲôҪ�����η�static
		public static int staticVar = 0; 
		public int instanceVar = 0; 
		public VariantTest(){
			staticVar++;
			instanceVar++;
			System.out.println("staticVar=" + staticVar + ",instanceVar="+ instanceVar);
		}
	}
	@Test
	public void testVariantTest(){
		new VariantTest();
		new VariantTest();
	}
	@Test
	public void testStaticTest(){
		new VariantTest();
		new VariantTest();
	}
	@Test
	public void testString(){
		String s1 = "a";
		String s2 = s1 + "b";
		String s3 = "a" + "b";
		System.out.println(s2 == "ab");
		System.out.println(s3 == "ab");
		String s = "a" + "b" + "c" + "d";//�������Զ��Ż�
		System.out.println(s == "abcd");
	}
}

