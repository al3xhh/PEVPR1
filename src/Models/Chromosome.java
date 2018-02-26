package Models;

/**
 * 
 * @author al3x_hh
 *
 */
public abstract class Chromosome {
	private boolean[] gens;
	private double aptitude;
	private double score;
	private double aggregateSocore;
	
	public Chromosome(boolean[] gens, double aptitude, double score, double aggregateSocore) {
		super();
		this.aptitude = aptitude;
		this.score = score;
		this.aggregateSocore = aggregateSocore;
	}

	public boolean[] getGens() {
		return gens;
	}

	public void setGens(boolean[] gens) {
		this.gens = gens;
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
	 */
	abstract public void initChromosome();
	
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
}
