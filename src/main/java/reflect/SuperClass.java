package reflect;

/**
 * @author gonglei
 * @date 2020/5/14
 */
public class SuperClass extends Object {

	private Integer id;
	private String name;
	public static final String super_default_value = "123456";

	public SuperClass(){}

	public SuperClass(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// public 内部类
	public class InnerSuperClass{}

	// protected 内部类
	protected class InnerSuperClass2{}

	// private内部类
	private class InnerSuperClass3{}


}
