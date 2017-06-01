package it.polito.tdp.rivers.model;

import java.time.*;

public class Flow {

	private int id;
	private LocalDate day;
	private float flowValue;
	private int river;
	public Flow(int id, LocalDate day, float flow, int river) {
		super();
		this.id = id;
		this.day = day;
		this.flowValue = flow;
		this.river = river;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the day
	 */
	public LocalDate getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(LocalDate day) {
		this.day = day;
	}
	/**
	 * @return the flow
	 */
	public float getFlowValue() {
		return flowValue;
	}
	/**
	 * @param flow the flow to set
	 */
	public void setFlowValue(float flow) {
		this.flowValue = flow;
	}
	/**
	 * @return the river
	 */
	public int getRiver() {
		return river;
	}
	/**
	 * @param river the river to set
	 */
	public void setRiver(int river) {
		this.river = river;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flow other = (Flow) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}