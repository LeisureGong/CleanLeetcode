package proxy.demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author gonglei
 * @date 2020/8/5
 */
public class InvocationHandlerImpl implements InvocationHandler {

	ShowService target;

	public InvocationHandlerImpl(ShowService target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object invoke = method.invoke(target,args);
		return invoke;
	}
}
