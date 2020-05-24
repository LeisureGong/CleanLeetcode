package reflect;

import sun.awt.AWTAccessor;

/**
 * @author gonglei
 * @date 2020/5/14
 */
public class SubClass extends SuperClass {

	public static final String sub_default_value = "123456";
	private final String finalString = "final String";
	private final int finalInt = 1;
	private final Integer finalInteger = 11;

	public SubClass() {}

	public SubClass(Integer id, String subName) {
		this.id = id;
		this.subName = subName;
	}

	public SubClass(Integer id, String name, Integer id1, String subName) {
		super(id, name);
		this.id = id1;
		this.subName = subName;
	}

	private Integer id;
	private String subName;

	public void publicMethod(String... args) throws NullPointerException, ClassCastException {
		StringBuffer stringBuffer = new StringBuffer("====");
		for(Object obj : args){
			stringBuffer.append(obj.toString()).append("===");
		}
		privateMethod(stringBuffer.toString());
	}

	private void privateMethod(String str) {
		System.out.println("===name : " + getName() + "  id:" + getId() + "===" + str == null ? "" : str);
	}

	public String getFinalString(){
		return finalString;
	}

	public int getFinalInt(){
		return finalInt;
	}

	public Integer getFinalInteger(){
		return finalInteger;
	}

	@Override
	public Integer getId() {
		return super.getId();
	}

	@Override
	public void setId(Integer id) {
		super.setId(id);
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	// 内部类
	public class InnerSubClass {}
}
