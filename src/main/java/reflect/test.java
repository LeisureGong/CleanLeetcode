package reflect;

import com.sun.javafx.binding.StringFormatter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

/**
 * @author gonglei
 * @date 2020/5/14
 */
public class test {

	public static void parseConstructors(Class cls,boolean declared) {
		Constructor[] constructors;
		if(declared){
			constructors = cls.getDeclaredConstructors();
		} else {
			constructors = cls.getConstructors();
		}

		for(Constructor constructor : constructors) {
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(Modifier.toString(constructor.getModifiers())).append(" ")
					.append(constructor.getName()).append("(");

			//参数
			Parameter[] parameters = constructor.getParameters();
			if (parameters != null && parameters.length > 0) {
				for (Parameter parameter : parameters) {
					stringBuffer.append(parameter.getType().getName()).append(" ")
							.append(parameter.getName()).append(",");
				}
				stringBuffer.delete(stringBuffer.length() - 1,stringBuffer.length());
			}
			stringBuffer.append(")");

			// 异常信息
			Class[] exceptionClass = constructor.getExceptionTypes();
			if(exceptionClass != null && exceptionClass.length > 0){
				stringBuffer.append(" throws ");
				StringBuffer exceptionSb = new StringBuffer();
				for (Class exceptionCls : exceptionClass) {
					exceptionSb.append(exceptionCls.getName()).append(",");
				}
				stringBuffer.append(exceptionSb.substring(0,exceptionSb.length() - 1));
			}
			System.out.println(stringBuffer.toString());
		}

	}

	public static void parseFields(Class cls,boolean declared){
		Field[] fields;
		if(declared) {
			// 获取本类所有变量，所有权限
			fields = cls.getDeclaredFields();
		} else {
			// 获取所有public的成员变量，包含本类和从子类继承来的
			fields = cls.getFields();
		}
		for(Field field : fields) {
			System.out.println(Modifier.toString(field.getModifiers()) + " " + field.getType().getName() + field.getName());

		}
		System.out.println("");
	}

	public static void parseMethods(Class cls, boolean declared) {
		Method[] methods = null;
		if (!declared) {
			//获取自己及父类public的方法
			methods = cls.getMethods();
		} else {
			//获取本类所有权限的方法
			methods = cls.getDeclaredMethods();
		}
		for (Method method : methods) {
			//获取方法的入参信息
			Parameter[] parameters = method.getParameters();
			System.out.print(Modifier.toString(method.getModifiers()) + " " +
					method.getReturnType().getName() + " " + method.getName());
			System.out.print("(");
			//入参
			if (parameters != null && parameters.length > 0) {
				StringBuffer argsStr = new StringBuffer();
				for (Parameter parameter : parameters) {
					argsStr.append(parameter.getType().getName()).append(" ").append(parameter.getName()).append(",");
				}
				System.out.print(argsStr.substring(0, argsStr.length() - 1));
			}
			System.out.print(")");
			//异常信息
			Class[] exceptionClss = method.getExceptionTypes();
			if (exceptionClss != null && exceptionClss.length > 0) {
				System.out.print("throws ");
				StringBuffer exceptionSb = new StringBuffer();
				for (Class exceptionCls : exceptionClss) {
					exceptionSb.append(exceptionCls.getName()).append(",");
				}
				System.out.print(exceptionSb.substring(0, exceptionSb.length() - 1));
			}
			System.out.println();
		}
	}


	public static void main(String... args) {
//		parseConstructors(SubClass.class,true);
//		parseConstructors(SubClass.class,false);
//		parseFields(SubClass.class,true);
//		parseFields(SubClass.class,false);
		parseMethods(SubClass.class,false);
		parseMethods(SubClass.class,true);
	}
}
