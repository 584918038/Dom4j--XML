package com.xsh;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * @author 徐思晗
 * @date   2018年9月13日
 * @email  xshlxx@126.com
 * @version 1.0
 */
public class AnalyzeElementType {
	
	/**
	 * 
	 * @param clazz 用来判断类型 
	 * @param obj 传过来的数值
	 * @return
	 */
	public static void getRealType(Class<?> clazz,Object obj,Method methodo,Object p) {
		
		try {
			if(clazz.getName().endsWith(String.class.getName())) { /**证明是字符串 */
				methodo.invoke(p, obj);
				return;
			} else if(clazz.getName().endsWith( int.class.getName()) || clazz.getName().endsWith( Integer.class.getName())) {
				methodo.invoke(p, Integer.valueOf((String)obj));
				return;
			} else if(clazz.getName().endsWith(double.class.getName()) || clazz.getName().endsWith(Double.class.getName())) {
				methodo.invoke(p, Double.valueOf((String)obj));
				return;
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
