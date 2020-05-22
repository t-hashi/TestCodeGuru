package test;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class NotThreadSafeBean {

	public String doBusiness() {
		// not thread safe.
		final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
		//HashMap<String, String> map = new HashMap<String, String>();
		map.put("message", getTimeStamp());
		
		return map.get("message");
	}
	
	private String getTimeStamp() {
		long millis = System.currentTimeMillis();
	    Timestamp timestamp = new Timestamp(millis);
		return timestamp.toString();
	}
}
