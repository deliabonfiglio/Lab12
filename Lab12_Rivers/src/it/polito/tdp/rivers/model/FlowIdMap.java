package it.polito.tdp.rivers.model;

import java.util.HashMap;
import java.util.Map;

public class FlowIdMap {
	Map<Integer, Flow> map;

	public FlowIdMap() {
		super();
		this.map = new HashMap<Integer, Flow>();
	}
	
	public Flow get(Integer id){
		return map.get(id);
	}
	
	public Flow put(Flow value){
		Flow old = map.get(value);
		if(old == null){
			map.put(value.getId(), value);
			return value;
		} else 
			return old;
	}
	
	
}
