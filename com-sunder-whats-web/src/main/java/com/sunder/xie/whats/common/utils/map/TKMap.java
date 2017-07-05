package com.sunder.xie.whats.common.utils.map;

import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * two key Map
 * @author c_xiaoshiwei-001
 *
 * @param <K1>
 * @param <K2>
 * @param <VA>
 */
public class TKMap <K1, K2, VA> implements Serializable{
	private static final long serialVersionUID = -8047949160973187215L;
	private ConcurrentMap<K1, ConcurrentMap<K2, VA>> twoKeyMap = new ConcurrentHashMap<K1,  ConcurrentMap<K2, VA>>();
	
	/**
	 * put value
	 * @param k1
	 * @param k2
	 * @param value
	 */
	public void put(K1 k1, K2 k2, VA value){
		if(k1 == null){
			return;
		}
		ConcurrentMap<K2, VA> keyMap = twoKeyMap.get(k1);
	    if (keyMap != null){
	    	keyMap.put(k2, value);
	    } else {
	    	keyMap = new ConcurrentHashMap<K2, VA>();
	    	keyMap.put(k2, value);
	    	twoKeyMap.put(k1, keyMap);
	    }
	 }
	
	/**
	 * put value
	 * @param k1
	 * @param k2
	 * @param value
	 */
	public void put(K1 k1,Map<K2, VA> k2Map){
		if(k1 == null || k2Map == null || k2Map.isEmpty()){
			return;
		}
		Set<Entry<K2, VA>> k2Set = k2Map.entrySet();
        for(Entry<K2, VA> var : k2Set){
        	this.put(k1, var.getKey(), var.getValue());
        }
	 }

	/**
	 * get value
	 * @param k1
	 * @param k2
	 * @return
	 */
	 public VA get(K1 k1, K2 k2){
		if(k1 == null){
			return null;
		}
		ConcurrentMap<K2, VA> keyMap = twoKeyMap.get(k1);
	    if (keyMap == null){
	      return null;
	    }
	    if (k2 == null){
	      Collection<VA> vs = keyMap.values();
	      if (CollectionUtils.isNotEmpty(vs)){
	        return vs.iterator().next();
	      }
	      return null;
	    }
	    return keyMap.get(k2);
	}
	
	 /**
	  * get key is k1 value
	  * @param k1
	  * @return
	  */
	public Collection<VA> getK1Vas(K1 k1){
		if(k1 == null){
			return null;
		}
		ConcurrentMap<K2, VA> keyMap = twoKeyMap.get(k1);
	    if (keyMap == null){
	      return null;
	    }
	    return keyMap.values();
	}
	
	 /**
	  * get key is k2 value
	  * @param k1
	  * @return
	  */
	public ConcurrentMap<K2, VA> getK2Map(K1 k1){
		if(k1 == null){
			return null;
		}
		return twoKeyMap.get(k1);
	}
	
	/**
	 * clear value
	 */
	public void clear(){
		twoKeyMap.clear();
	}
}
