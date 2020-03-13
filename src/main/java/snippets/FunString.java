package snippets;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;

/**
 * @author gonglei
 * @date 2020/3/2 15:19
 */
public class FunString {

	public static void main(String[] args){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -365);
		date = calendar.getTime();
		System.out.println(sdf.format(date));
	}




}
