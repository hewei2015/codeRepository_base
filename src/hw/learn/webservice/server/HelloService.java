package hw.learn.webservice.server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService //将java类标记为实现web service，或者将java接口标记为定义web Service接口
public class HelloService {
	public static void main(String[] args){
		//Endpoint：web service端点类
		//publish()：参数一为服务的发布地址；参数二为服务的实现者（供外部调用的方法），该方法会启动一个新的进程
		Endpoint.publish("http://127.0.0.1/hello",new HelloService()); 
		System.out.println("Service ready ... ");//会输出，属于主线程[多线程]
	}
	//★★静态方法、用final修饰的方法是不能被发布为web服务的
	public String sayHello(String name){
		return "hello "+name;
	}
    @WebMethod(exclude=true) //★★表示：将一个方法隐藏起来不发布
    public String sayHello2(String name){
        return "hello"+name;
    }
}
