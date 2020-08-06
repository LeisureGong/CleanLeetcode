package proxy.demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author gonglei
 * @date 2020/8/5
 */
public class JDKProxyDemo {

	public static void main(String[] args) {
		Star star = new Star("ahahhaha");
		// 获取对应的ClassLoader
		ClassLoader classLoader = star.getClass().getClassLoader();
		// 获取被代理对象实现的所有接口
		Class[] interfaces = star.getClass().getInterfaces();
		// 设置请求处理器，处理所有方法调用
		InvocationHandler invocationHandler = new InvocationHandlerImpl(star);

		/**
		 * 根据上面的消息，创建代理消息，在这个过程中：
		 * 1. JDK会通过根据传入的参数信息动态地在内存中创建和.class文件相同的字节码
		 * 2. 然后根据相应的字节码转换成对应的class
		 * 3. 然后根据newInstance()创建实例
		 *
		 */
		Object o = Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
		ShowService showService = (ShowService)o;
		showService.sing("Monkey Dance");
	}
}
