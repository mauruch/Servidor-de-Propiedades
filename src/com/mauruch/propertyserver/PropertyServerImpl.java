package com.mauruch.propertyserver;

import java.util.Iterator;
import static com.mauruch.propertyserver.utils.Constants.*;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 
 * @author mauro
 *
 */
public class PropertyServerImpl implements PropertyServer<String, String> {

	private static Map<String, String> map;

	public PropertyServerImpl() {
		map = new ConcurrentHashMap<String, String>();
	}

	@Override
	public String get(String key) {
		String val = map.get(key);
		return val != null ? val : NO_AVAILABLE;
	}

	@Override
	public String set(String key) {
		String[] keyValue = key.split(SEPARATOR_EQUALS);
		String resultMsg = get(keyValue[0]);
		map.put(keyValue[0].toString(), keyValue[1]);
		return resultMsg;

	}

	@Override
	public String list() {
		return map.isEmpty() ? NO_AVAILABLE : printMap();
	}

	@Override
	public String inc(String key) {
		Integer val;
		try {
			val = Integer.parseInt(map.get(key));
		} catch (NumberFormatException e) {
			return OPERATION_ERROR;
		}
		val += 1;
		map.put(key, val.toString());
		return val.toString();

	}

	@Override
	public String clear(String key) {
		return map.remove(key) != null ? OPERATION_OK : OPERATION_ERROR;

	}

	@Override
	public String exit() {
		return OPERATION_EXIT;

	}

	@Override
	public String terminate() {
		return OPERATION_TERMINATED;

	}
	
	private String printMap(){
		StringBuilder sb = new StringBuilder();
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> pair = (Map.Entry<String, String>) it.next();
			sb.append(pair.getKey());
			sb.append(SEPARATOR_EQUALS);
			sb.append(pair.getValue());
			sb.append("\n");
		}
		return sb.toString();
	}

}
