package com.atguigu.scw.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统中需要共享的数据可以放在这个缓存中
 * @author lfy
 *
 */
public class AppCacheUtils {
	
	//缓存的数据都放在这个map中
	private static Map<String, Object> cache = new HashMap<>();
	
	//缓存
	public static void put(String key,Object value){
		cache.put(key, value);
	}
	
	//查询
	public static Object get(String key){
		return cache.get(key);
	}
	
	//删除指定key
	public static Object remove(String key){
		return cache.remove(key);
	}
	
	//清空缓存
	public static void clear(){
		cache.clear();
	}
}
