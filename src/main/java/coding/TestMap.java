package coding;

/**
 * @author gonglei
 * @date 2020/3/5 10:05
 */
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TestMap{
	public static void main(String[] args){
		Map<String,String> map = new HashMap<String,String>();
		map.put("1","value1");
		map.put("2","value2");
		map.put("3","value3");

//		for(String key : map.keySet()){
//			System.out.println(key + "   " + map.get(key));
//		}
//
//		System.out.println("-------------[round 2]----------------");
//		Iterator<Map.Entry<String,String>> it = map.entrySet().iterator();
//		while(it.hasNext()){
//			Map.Entry<String,String> entry = it.next();
//			System.out.println(entry.getKey() + "    " + entry.getValue());
//		}
//
//		System.out.println("-------------[round 3]----------------");
//		for(Map.Entry<String,String> entry:map.entrySet()){
//			System.out.println(entry.getKey() + "ahah" + entry.getValue());
//		}
//
//		System.out.println("-------------[round 4]----------------");
//		for(String v : map.values()){
//			System.out.println(v);
//		}

		Map<Long,String> newMap = map.entrySet().stream()
				.collect(Collectors.toMap(e -> Long.parseLong(e.getKey()),Map.Entry::getValue));
		return;
	}


}