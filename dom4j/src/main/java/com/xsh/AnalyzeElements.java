package com.xsh;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
public class AnalyzeElements {
	
	public static void main(String[] args) throws Exception{
		
		List<Object> lists = new ArrayList<Object>();
		Class<?> clapp = Class.forName("com.xsh.Header");
		String pathValue = AnalyzeElements.class.getResource("/resources/header.xml").getFile();
		File file = new File(pathValue);
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(file);
			
			Element root = doc.getRootElement(); 
			
			@SuppressWarnings("unchecked")
			List<Element> childList = root.elements();
			
			if (null != childList && childList.size() > 0) {
				
				for(int i = 0; i < childList.size(); i++) {
					
					if (isExistChildElement(childList.get(i))) { 
						
						List<?> childElement = isExistElement(childList.get(i));
						
						Object p = clapp.newInstance();
						
						for(int j = 0; j < childElement.size(); j++) {
							
							Element EE = (Element)childElement.get(j); 
							
							String childValue = EE.getText(); 
							
							Method[] methods = clapp.getMethods();
							
							String regex = "^[s]{1}\\w+$"; /** 遍历出Set方法 */
							
							execMethod(methods,regex,clapp,p,childValue,EE);
						}
						lists.add(p);
					}
				}
			}
			TraversalElement(lists);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param methods class这个类中所有的方法
	 * @param regex 正则表达式
	 * @param clapp 反射出来的类
	 * @param p 实体类
	 * @param childValue 节点元素里面的Text内容
	 * @param EE 元素Element
	 */
	public static void execMethod(Method[] methods,String regex
			,Class<?> clapp,Object p,String childValue,Element EE) {
		
		try { /** 下面,我们来找到出现这个问题的原因 */
			for (Method method : methods) {
				if(method.getName().matches(regex)) {
					if((method.getName().endsWith(EE.getName()))) {
						Class<?>[] parameterTypes = method.getParameterTypes();
						/** 我的思路是自己去拼接一下,就最后的参数而言,我的是Int 为什么匹配出来的是String*/
						Method methodo  = clapp.getMethod(method.getName(), parameterTypes);
						/** 应该在这里面进行处理,解决的方案是: 判断类型 */
						for (Class<?> cla : parameterTypes) {
							AnalyzeElementType.getRealType(cla,childValue,methodo, p);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	/**
	 * 
	 * @param element 判断该元素是否存在子元素 
	 * @return 存在的话,将元素存放进去 不存在的话,返回null 
	 */
	public static List<?> isExistElement(Element element) {
		/** 1.先判断是否可以继续遍历下去  */
		if (null != element) {
			return element.elements();
		}
		return null;
	}
	/**
	 * 
	 * @param element 传入的Element元素
	 * @return true 存在子元素 false 不存在子元素
	 */
	public static boolean isExistChildElement(Element element) {
			
		if(element.elements().size() > 0) return true;
		
		return false;
	}
	/**
	 * 
	 * @param lists 需要打印出的元素的集合 
	 */
	public static void TraversalElement(List<?> lists) {
		for (Object object : lists) {
			System.out.println(object);
		}
	}
}
