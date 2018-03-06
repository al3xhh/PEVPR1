package Models;

/**
 * 
 * @author al3x_hh
 *
 */
public abstract class Chromosome {
	protected boolean[] gens;
	protected double aptitude;
	protected double score;
	protected double aggregateSocore;

	public boolean[] getGens() {
		return gens;
	}

	public void setGens(boolean[] gens) {
		this.gens = gens;
	}
	
	public void setGen(boolean gen, int pos) {
		this.gens[pos] = gen;
	}

	public double getAptitude() {
		return aptitude;
	}

	public void setAptitude(double aptitude) {
		this.aptitude = aptitude;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public double getAggregateSocore() {
		return aggregateSocore;
	}

	public void setAggregateSocore(double aggregateSocore) {
		this.aggregateSocore = aggregateSocore;
	}
	
	/**
	 * 
	 * @param minX
	 * @param maxX
	 * @return
	 */
	public int getLength(double minX, double maxX, double precision) {
		double x = 1 + ((maxX - minX) / precision);
		return Utils.log(x, 2);
	}
	
	/**
	 * 
	 * @param mutation
	 */
	public void mutation(double mutation) {
		boolean mutated = false;
		
		for(int i = 0; i < gens.length; i++) {
			if(Math.random() < mutation) {
				mutated = true;
				gens[i] = !gens[i];
			}
		}
		
		if(mutated)
			this.test();
	}
	
	/**
	 * 
	 */
	abstract public void init();
	
	/**
	 * 
	 * @return
	 */
	abstract public double test();
	
	/**
	 * 
	 * @return
	 */
	abstract public double getPhenotype();
	
	/**
	 * 
	 * @return
	 */
	abstract public Chromosome getChild();
}
