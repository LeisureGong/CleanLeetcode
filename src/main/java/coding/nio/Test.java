package coding.nio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author gonglei
 * @date 2020/7/20
 */
public class Test {

	public void outputTest() throws IOException {
		OutputStream output = new FileOutputStream("d:\\Github\\test.txt");
		output.write("Hello World".getBytes());
		output.close();
	}

	public void inputTest() throws IOException {
		InputStream input = new BufferedInputStream(
				new FileInputStream("d:\\Github\\test.txt")
		);
		int data = input.read();
		System.out.println(data);
	}

	public static void main(String[] args) throws IOException {
		Test solution = new Test();
		solution.inputTest();
	}

}
