package it.polito.tdp.rivers.model;

import java.util.*;
import it.polito.tdp.rivers.DB.RiversDAO;
import it.polito.tdp.rivers.simulazione.Simulation;

public class Model {
	private Simulation sim;
	private List<River> rivers;
	private RiverIdMap riverIdMap;	
	private FlowIdMap flowIdMap;
	private List<Flow> flows = new ArrayList<Flow>();

	public Model() {
		super();
		this.rivers = new ArrayList<River>();
		this.riverIdMap = new RiverIdMap();
		this.flowIdMap= new FlowIdMap();
	}

	public List<River> getRivers() {
		rivers.clear();		
		if(rivers.isEmpty()){
			RiversDAO dao = new RiversDAO();
			rivers.addAll(dao.listAuthors(riverIdMap));

			}
		return rivers;
	}

	public Flow getStartDate(River river) {
		List<Flow> flows = new ArrayList<Flow>(this.getFlows(river));

		return flows.get(0);
	}

	public Flow getEndDate(River river) {
		List<Flow> flows = new ArrayList<Flow>(this.getFlows(river));

		return flows.get(flows.size()-1);
	}

	public int getNumMeasurements(River river) {
		List<Flow> flows = new ArrayList<Flow>(this.getFlows(river));

		return flows.size();
	}

	public float getFMed(River river) {
		List<Flow> flows = new ArrayList<Flow>(this.getFlows(river));
		
		float sum = 0;
		int count =0;
		
		for(Flow ftemp: flows){
			sum +=ftemp.getFlowValue();
			count++;
		}
		
		return sum/count;
	}

	public List<Flow> getFlows(River r){		
		if(r.getFlows().isEmpty()){
			
			RiversDAO dao = new RiversDAO();
			flows = dao.getFlowsOfRiver(r, riverIdMap, flowIdMap);
		} 
		//System.out.println(flows.size());
		return flows;
	}
	
	public void simula(int kvalue, float f, River r){
		sim = new Simulation(kvalue, f);
		sim.addEvent(this.getFlows(r));
		sim.run();
	}

	public int getStatGGNotSatisfied() {
		return sim.getStat().getGg_not_satisfied();
	}

	public float getStatCmedio() {
		
		return sim.getC()/sim.getStat().getGg();
	}
}
