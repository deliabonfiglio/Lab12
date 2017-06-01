package it.polito.tdp.rivers.simulazione;

public class Statistiche {
	//dati di interesse
	private float Ctot;
	private int gg_not_satisfied;
	private int gg;
	
	public Statistiche() {
		super();
		this.Ctot =0;
		this.gg_not_satisfied=0;
		this.gg=0;
	}

	/**
	 * @return the gg
	 */
	public int getGg() {
		return gg;
	}

	/**
	 * @param gg the gg to set
	 */
	public void setGg(int gg) {
		this.gg = gg;
	}

	/**
	 * @return the cmed
	 */
	public float getCtot() {
		return Ctot;
	}

	/**
	 * @param cmed the cmed to set
	 */
	public void setCtot(float cmed) {
		Ctot = cmed;
	}

	/**
	 * @return the gg_not_satisfied
	 */
	public int getGg_not_satisfied() {
		return gg_not_satisfied;
	}

	/**
	 * @param gg_not_satisfied the gg_not_satisfied to set
	 */
	public void setGg_not_satisfied(int gg_not_satisfied) {
		this.gg_not_satisfied = gg_not_satisfied;
	}
	
	
	
	
}
