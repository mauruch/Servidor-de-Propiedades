package com.mauruch.propertyserver;

/**
 * 
 * @author mauro
 *
 * @param <K>
 * @param <V>
 */
public interface PropertyServer<K, V> {
	
	public V get(K key);
	
	public K set(String key);
	
	public K list();
	
	public String inc(K key);
	
	public K clear(K key);
	
	public K exit();
	
	public K terminate();
	

}
