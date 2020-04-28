package coding;

import javafx.util.Pair;
import jdk.internal.cmm.SystemResourcePressureImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author gonglei
 * @date 2020/4/28 10:02
 */
public class Pairs {
	public static void main(String... args) {
		testForEach();
	}

	public static void testForEach(){
		List<String> list = new ArrayList<>();
		list.add("1");
//		list.add("2");
//		list.add("3");
//		list.add("4");
		Iterator<String> iterator = list.iterator();
		while(iterator.hasNext()){
			String item = iterator.next();
			if("1".equals(item)){
				iterator.remove();
			}
			if("4".equals(item)){
				iterator.remove();
			}
		}
		System.out.println(list);

//		for(String a : list){
//			if("4".equals(a)){
//				list.remove(a);
//			}
//		}
//		System.out.println(list);
	}

	public static void testPair(){
		List<Pair<String,Double>> pairArrayList = new ArrayList<>(3);
		pairArrayList.add(new Pair<>("version",6.19));
		pairArrayList.add(new Pair<>("version",8.0));
		pairArrayList.add(new Pair<>("version",13.14));
		Map<String,Double> map = pairArrayList.stream().collect(
				Collectors.toMap(Pair::getKey,Pair::getValue,(v1,v2) -> v2));
		for(Map.Entry m : map.entrySet()){
			System.out.println(m.getKey() + "  :  " + m.getValue());
		}

		String[] departments = new String[]{"iERP","EIBU"};
		Map<Integer,String> strMap = Arrays.stream(departments)
				.collect(Collectors.toMap(String::hashCode,str -> str));
		for(Map.Entry s : strMap.entrySet()){
			System.out.println(s.getKey() + "  :  " + s.getValue());
		}
	}
}
