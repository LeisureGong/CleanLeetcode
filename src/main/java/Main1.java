import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.BufferedInputStream;
import java.util.*;

public class Main1 {

    public static void main(String... args){
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        int n = sc.nextInt();

        Map<String,Integer> map = new HashMap<>();
        for(int i = 0;i < n;i++){
            String input = sc.next();
            if(map.containsKey(input)){
                map.put(input,map.get(input)+1);
            }else{
                map.put(input,1);
            }
        }
        Set set = map.keySet();
        Object[] arr=set.toArray();
        Arrays.sort(arr);
        for(Object key:arr){
            System.out.println(key + " " + map.get(key));
        }

    }

}
