package hw.learn.simple.grammer;


import org.junit.Test;

public class DuoTai {
	/*
	 * ���ѧ��ѧϰ��˯�� �߼���ѧ��ѧϰ��˯�� ���Խ�������������г�ȡ
	 */

	abstract class Student {
		public abstract void study();

		public void sleep() {
			System.out.println("������˯");
		}
	}

	class BaseStudent extends Student {
		public void study() {
			System.out.println("base study");
		}

		public void sleep() {
			System.out.println("������˯");
		}
	}

	class AdvStudent extends Student {
		public void study() {
			System.out.println("adv study");
		}
	}

	// ����װ-->������
	class DoStudent {
		public void doSome(Student stu) {// ���븸�����
			stu.study();
			stu.sleep();
		}
	}
	@Test
	public void testDuotai() {
		DoStudent ds = new DoStudent();//������д��һ���ͻ��˷ŵ���һ�����У�Ϊʲô���?��DuoTaiDemo.java
		ds.doSome(new BaseStudent());
		ds.doSome(new AdvStudent());
	}
}
