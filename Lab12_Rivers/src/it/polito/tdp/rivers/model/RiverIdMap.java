package it.polito.tdp.rivers.model;

import java.util.*;

public class RiverIdMap {
	Map<Integer, River> map;

	public RiverIdMap() {
		super();
		this.map = new HashMap<Integer, River>();
	}
	
	public River get(Integer id){
		return map.get(id);
	}
	
	public River put(River value){
		River old = map.get(value);
		if(old == null){
			map.put(value.getId(), value);
			return value;
		} else 
			return old;
	}
	
	
}
