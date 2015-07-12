package hw.learn.simple.grammer;

public  class Finally3 {
	public static void main(String[] args) {
		System.out.println(new Finally3().test());;
	}
	@SuppressWarnings("finally")
	int test()
	{
		try
		{
			return func1();
		}
		finally
		{
			return func2();
		}
	}
	
	int func1()
	{
		System.out.println("func1");
		return 1;
	}
	int func2()
	{
		System.out.println("func2");
		return 2;
	}	
}
