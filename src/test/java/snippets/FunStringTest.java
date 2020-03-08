package snippets; 

import org.junit.Test;

import java.util.Arrays;

public class FunStringTest { 

@Test
public void testByteSize() throws Exception {
//TODO: Test goes here...
	System.out.println(FunString.byteSize("hewhewioaewqouhjiewqfhkkhjlewq"));
}


	@Test
	public void concat() throws Exception{
		String[] first = {"a", "b"};
		String[] second = {"c", "d"};
		System.out.println(Arrays.toString(FunString.concat(first,second)));
	}
}
