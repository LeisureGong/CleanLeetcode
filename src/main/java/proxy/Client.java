package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author gonglei
 * @date 2020/5/14
 */
public class Client {

	public static void main(String[] args) throws IllegalArgumentException,InstantiationException {

		UserServiceImpl userServiceImpl = new UserServiceImpl();

		ClassLoader classLoader = userServiceImpl.getClass().getClassLoader();

		Class[] interfaces = userServiceImpl.getClass().getInterfaces();

		InvocationHandler logHandler = new LogHandler(userServiceImpl);

		UserService proxy = (UserService) Proxy.newProxyInstance(classLoader,interfaces, logHandler);

		proxy.select();
		proxy.update();
	}
}
