package it.polito.tdp.rivers.simulazione;

import java.util.List;
import java.util.PriorityQueue;
import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.simulazione.Event.EventType;


public class Simulation {
	private PriorityQueue<Event> queue;
	
	private float C;
	private float Q;
	private int k;
	private float fmedia;
	
	private Statistiche stat;
	
	
	public Simulation(int k, float f) {
		super();
		this.k = k;
		this.Q = k*f*30*24*3600;
		this.C = Q/2;
		this.queue= new PriorityQueue<>();
		this.fmedia=f*24*3600;
		this.stat= new Statistiche();
		
	}

	public void addEvent(List<Flow> flows) {
		for(Flow ftemp: flows)
			queue.add(new Event(ftemp, EventType.FLOW_IN));		
		stat.setGg(flows.size()+1);
		//System.out.println("n gg:"+flows.size()+1);
	}

	public void run() {
		while(!queue.isEmpty()){
			Event e = queue.poll();	//finchè non si svuota
			//devo elaborare l'evento ogni volta che lo estraggo = processEvent()
			
			switch(e.getType()){
			
			case FLOW_IN:
				processInEvent(e);
				break;
			default:
				break;
			}
		}
	
	}

	private void processInEvent(Event e) {
		float fin = e.getFlow().getFlowValue()*3600*24; //prendo il valore del flusso registrato dal Flusso
		float fout=0;
		
		double prob = Math.random();
		//System.out.println(prob);
		if(prob<0.95){
			//flusso in uscita normale
			fout= (float) (0.8*fmedia);		
		} else {
			// richiesta aggiuntiva di acqua per irrigare i campi
			fout= (float) (10*0.8*fmedia);	
		}
		
		C += fin-fout;
		
		if(C<0){	
			//non ho soddisfatto la richiesta di acqua
			C=0; 													//cosi la capacità media non risulta negativa
			stat.setGg_not_satisfied(stat.getGg_not_satisfied()+1);	//ma devo settare che è un giorno dove non soddisfo la richiesta
	
		} else {
			if(C>Q){											//poichè fout=fin allora C=Q
				C=Q;
			} 
			stat.setCtot(stat.getCtot()+C);
			
		}
		
	}

	/**
	 * @return the stat
	 */
	public Statistiche getStat() {
		return stat;
	}

	/**
	 * @param stat the stat to set
	 */
	public void setStat(Statistiche stat) {
		this.stat = stat;
	}

	/**
	 * @return the c
	 */
	public float getC() {
		return C;
	}

	/**
	 * @param c the c to set
	 */
	public void setC(float c) {
		C = c;
	}
}
