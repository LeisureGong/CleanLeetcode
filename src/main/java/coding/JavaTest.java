package coding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author gonglei
 * @date 2020/4/1 9:22
 */
public class JavaTest {
	public static void main(String... args){
//		List<Data2> listOfData2 = new ArrayList<Data2>();
//
//		listOfData2.add(new Data2(10501, "JOE"  , "Type1"));
//		listOfData2.add(new Data2(10603, "SAL"  , "Type5"));
//		listOfData2.add(new Data2(40514, "PETER", "Type4"));
//		listOfData2.add(new Data2(59562, "JIM"  , "Type2"));
//		listOfData2.add(new Data2(29415, "BOB"  , "Type1"));
//		listOfData2.add(new Data2(61812, "JOE"  , "Type9"));
//		listOfData2.add(new Data2(98432, "JOE"  , "Type7"));
//		listOfData2.add(new Data2(62556, "JEFF" , "Type1"));
//		listOfData2.add(new Data2(10599, "TOM"  , "Type4"));


		List<Data1> listOfData1 = new ArrayList<Data1>();

		listOfData1.add(new Data1(10501, "JOE"    ,0));
		listOfData1.add(new Data1(10603, "SAL"    ,0));
		listOfData1.add(new Data1(40514, "PETER"  ,0));
		listOfData1.add(new Data1(59562, "JIM"    ,0));
		listOfData1.add(new Data1(29415, "BOB"    ,0));

		List<Data1> tempData1 = new ArrayList<>();
		tempData1.add(new Data1(40514, "PETER"  ,2005000));
		tempData1.add(new Data1(59562, "JIM"    ,3000000));
		tempData1.add(new Data1(29415, "BOB"    ,3000000));

//		List<Data1> result = listOfData1.stream()
//				.flatMap(x -> tempData1.stream()
//				.filter(y -> x.getId() == y.getId())
//				.map(y -> new Data1(x.getId(),x.getName(),y.getAmount())))
//				.collect(Collectors.toList());
//
//		List<Data1> result1 = listOfData1.stream().filter(
//				i -> tempData1.stream().anyMatch(j -> i.getId() == j.getId())
//		).collect(Collectors.toList());
		Map<String,Data1> id = tempData1.stream().collect(Collectors.toMap(Data1::getName,a -> a,(k1,k2)->k1));
		for(Data1 t : listOfData1){
			if(id.containsKey(t.getName())){
				t.setAmount(id.get(t.getName()).getAmount());
			}
		}

		System.out.println(listOfData1);
//
//		List<OutputData> result = listOfData1.stream()
//				.flatMap(x -> listOfData2.stream()
//						.filter(y -> x.getId() == y.getId())
//						.map(y -> new OutputData(y.getId(), x.getName(), y.getType(), x.getAmount())))
//				.collect(Collectors.toList());
//		System.out.println(result);

		/*difference by key*/
//		List<Data1> data1IntersectResult = listOfData1.stream().filter(data1 -> listOfData2.stream().map(Data2::getId).collect(Collectors.toList()).contains(data1.getId())).collect(Collectors.toList());
//		System.out.println(data1IntersectResult);
	}
}


class Data1 {
	private int id;
	private String name;
	private int amount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Data1(int id, String name, int amount) {
		this.id = id;
		this.name = name;
		this.amount = amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Data1 data1 = (Data1) o;
		return id == data1.id &&
				name.equals(data1.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return "Data1{" +
				"id=" + id +
				", name='" + name + '\'' +
				", amount=" + amount +
				'}';
	}
}

class Data2 {
	private int id;
	private String name;
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Data2(int id, String name, String type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}
}

class OutputData {
	private int id;
	private String name;
	private String type;
	private int amount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public OutputData(int id, String name, String type, int amount) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "OutputData{" +
				"id=" + id +
				", name='" + name + '\'' +
				", type='" + type + '\'' +
				", amount=" + amount +
				'}';
	}
}



