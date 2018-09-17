package dom4j;

import java.io.File;
import java.net.URL;

import org.junit.Test;

public class TestReaderXML {
	
	@Test
	public void test1() {
		
		ClassLoader classLoader = TestReaderXML.class.getClassLoader();
		String resource = classLoader.getResource("resources/parents.xml").getFile();
		File file = new File(resource);
		if (file.exists()) {
			System.out.println("文件读取到了");
			return;
		}
		System.out.println("文件没有读取到");
		return;
	}
	
	/** 读取方法的参数 */
	@Test
	public void test2() {
		
		System.out.println(Integer.class.getName());
		System.out.println(int.class.getName());
	}
}
