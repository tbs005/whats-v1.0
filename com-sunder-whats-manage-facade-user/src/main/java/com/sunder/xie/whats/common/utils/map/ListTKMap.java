package com.sunder.xie.whats.common.utils.map;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * list two key map
 * @author c_xiaoshiwei-001
 *
 * @param <K1>
 * @param <K2>
 * @param <T>
 */
public class ListTKMap<K1, K2,T > extends TKMap<K1,K2,List<T>> {

	private static final long serialVersionUID = -4966726723150508225L;
	
	/**
	 * 
	 * @param k1
	 * @return
	 */
	public List<T> getK1All(K1 k1){
		Collection<List<T>> coll = this.getK1Vas(k1);
		if(CollectionUtils.isEmpty(coll)){
			return null;
		}
		List<T> allList = new ArrayList<T>();
		for(List<T> var:coll){
			allList.addAll(var);
		}
		return allList;
	}
	
	
}
