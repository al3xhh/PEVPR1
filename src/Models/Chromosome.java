package Models;

/**
 * 
 * @author al3x_hh
 *
 */
public abstract class Chromosome {
	protected Gen[] gens;
	protected double aptitude;
	protected double score;
	protected double aggregateSocore;
	protected int length;

	
	public Gen[] getGens() {
		return gens;
	}

	public void setGens(Gen[] gens) {
		this.gens = gens;
	}
	
	public void setGenByAllele(boolean gen, int pos, int allele) {
		this.gens[pos].setAllele(gen, allele);
	}
	
	public void setGen(Gen gen, int pos) {
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
	
	public int getLength() {
		return length;
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public boolean[] getAlleles(int index) {
		boolean[] ret = new boolean[length];
		
		for(int i = 0; i < length; i++) {
			ret[i] = gens[i].getAllele(index);
		}
		
		return ret;
	}
	
	/**
	 * 
	 * @param minX
	 * @param maxX
	 * @return
	 */
	public int calculateLength(double minX, double maxX, double precision) {
		length = Utils.log(1 + ((maxX - minX) / precision), 2);
		return length;
	}
	
	/**
	 * 
	 * @param mutation
	 */
	public void mutation(double mutation) {
		boolean mutated = false;
		
		for(int i = 0; i < length; i++) {
			if(Math.random() < mutation) {
				mutated = true;
				gens[i].mutate();
			}
		}
		
		if(mutated)
			this.setAptitude(this.test());
	}
	
	public void setLength(int length) {
		this.length = length;
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
		
}
