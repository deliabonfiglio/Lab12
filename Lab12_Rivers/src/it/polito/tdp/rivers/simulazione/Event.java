package it.polito.tdp.rivers.simulazione;

import it.polito.tdp.rivers.model.Flow;

public class Event implements Comparable<Event> {
	
	public enum EventType{FLOW_IN}

	private Flow flow;
	private EventType type;
	
	public Event(Flow flow, EventType type) {
		super();
		this.flow = flow;
		this.type = type;
	}

	/**
	 * @return the flow
	 */
	public Flow getFlow() {
		return flow;
	}




	/**
	 * @param flow the flow to set
	 */
	public void setFlow(Flow flow) {
		this.flow = flow;
	}




	/**
	 * @return the type
	 */
	public EventType getType() {
		return type;
	}




	/**
	 * @param type the type to set
	 */
	public void setType(EventType type) {
		this.type = type;
	}




	@Override
	public int compareTo(Event altro) {
		if (flow.getDay().isAfter(altro.getFlow().getDay()))
			return 1;
		return 0;
	}

}