package test;

import java.util.HashMap;

public class NotThreadSafeBean {

	public HashMap doBusiness() {
		// HashMap is not thread safe.
		//ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("message", "Hello World.");
		return map;
	}
}
