package Models;

/**
 * 
 * @author al3x_hh
 *
 */
public abstract class Chromosome {
	protected boolean[][] gens;
	protected double aptitude;
	protected double score;
	protected double aggregateSocore;
	protected int[] length;

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
	
	public int getLength(int i) {
		return length[i];
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public boolean[] getAlleles(int index) {
		
		return gens[index];
	}
	
	/**
	 * 
	 * @param minX
	 * @param maxX
	 * @return
	 */
	public int calculateLength(double minX, double maxX, double precision) {
		return Utils.log(1 + ((maxX - minX) / precision), 2);
	}
	
	public boolean[][] getGens() {
		return gens;
	}

	public void setGens(boolean[][] gens) {
		this.gens = gens;
	}

	public int[] getLength() {
		return length;
	}

	public void setLength(int[] length) {
		this.length = length;
	}

	/**
	 * 
	 * @param mutation
	 */
	public void mutation(double mutation) {
		boolean mutated = false;
		
		for(int j = 0; j < length.length; j++){
			for(int i = 0; i < length[j]; i++) {
				if(Math.random() < mutation) {
					mutated = true;
					gens[j][i] = !gens[j][i];
				}
			}
		
		if(mutated)
			this.setAptitude(this.test());
		}
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
	abstract public double getPhenotype(int index);
	
	/**
	 * 
	 * @return
	 */
	abstract public Chromosome getChild();

	abstract public Chromosome clone();

	public void setGen(int gen, int i, boolean b) {
		this.gens[gen][i] = b;
	}
		
}
