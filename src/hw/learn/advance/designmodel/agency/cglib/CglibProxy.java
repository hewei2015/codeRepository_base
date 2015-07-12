package hw.learn.advance.designmodel.agency.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 通过Cglib实现在方法调用前后向控制台输出两句字符串
 */
//MethodInterceptor为spring内部的类
public class CglibProxy implements MethodInterceptor { 
	// 要代理的原始对象
	private Object obj; 

	public Object createProxy(Object target) {
		this.obj = target;
		Enhancer enhancer = new Enhancer();
		// 设置代理目标
		enhancer.setSuperclass(this.obj.getClass());
		// 设置回调
		enhancer.setCallback(this);
		enhancer.setClassLoader(target.getClass().getClassLoader());
		return enhancer.create();
	}

	/**
	 * 在代理实例上处理方法调用并返回结果
	 * @param proxy     代理类
	 * @param method    被代理的方法
	 * @param params    该方法的参数数组
	 * @param methodProxy
	 */
	public Object intercept(Object proxy, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
		Object result = null;
		// 调用之前
		doBefore();
		// 调用原始对象的方法
		result = methodProxy.invokeSuper(proxy, params);
		// 调用之后
		doAfter();
		return result;
	}

	private void doBefore() {
		System.out.println("before method invoke");
	}

	private void doAfter() {
		System.out.println("after method invoke");
	}
}
