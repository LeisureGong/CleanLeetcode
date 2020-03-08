package snippets;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author gonglei
 * @date 2020/3/2 15:19
 */
public class FunString {

	public static int byteSize(String input){
		return input.getBytes().length;
	}

	public static <T> T[] concat(T[] first,T[] second){
		return Stream.concat(
				Stream.of(first),
				Stream.of(second)
		).toArray(i -> (T[]) Arrays.copyOf(new Object[0],i,first.getClass()));
	}




}
