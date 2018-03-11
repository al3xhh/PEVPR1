package Models;

/**
 * Abstract class which represents a chromosome.
 * 
 * @author Group 06.
 *
 */
public abstract class Chromosome {
	
	/**
	 * Boolean matrix, in each position it has an array with alleles.
	 */
	protected boolean[][] _gens;
	
	/**
	 *  Integer array with the length of each alleles array.
	 */
	protected int[] _length;
	
	/**
	 * Chromosome aptitude.
	 */
	protected double _aptitude;
	
	/**
	 * Chromosome score.
	 */
	protected double _score;
	
	/**
	 * Chromosome aggregate score.
	 */
	protected double _aggregateSocore;
	
	/**
	 * Initialize chromosome with random values.
	 */
	public void init() {
		for (int j = 0; j < _length.length; j++){
			for(int i = 0; i < _length[j]; i ++) {
				_gens[j][i] = Math.random() < 0.5 ? false : true;
			}
		}
	}
	
	/**
	 * Calculate the chromosome length.
	 * 
	 * @param minX minimum x value.
	 * @param maxX maximum x value.
	 * @return chromosome length.
	 */
	public int calculateLength(double minX, double maxX, double precision) {
		return Utils.log(1 + ((maxX - minX) / precision), 2);
	}

	/**
	 * Mutate the chromosome.
	 * 
	 * @param mutation mutation %.
	 */
	public void mutation(double mutation) {
		boolean mutated = false;
		
		for(int j = 0; j < _length.length; j++){
			for(int i = 0; i < _length[j]; i++) {
				if(Math.random() < mutation) {
					mutated = true;
					_gens[j][i] = !_gens[j][i];
				}
			}
		
		if(mutated)
			setAptitude(test());
		}
	}
	
	//ABSTRACT FUNCTIONS//
	
	/**
	 * Test the chromosome to calculate the aptitude.
	 * 
	 * @return chromosome aptitude.
	 */
	abstract public double test();
	
	/**
	 * Calculate the phenotype.
	 * 
	 * @param index allele number.
	 * @return chromosome phenotype.
	 */
	abstract public double getPhenotype(int index);
	
	/**
	 * Create new child of each chromosome type.
	 * 
	 * @return new chromosome.
	 */
	abstract public Chromosome getChild();

	/**
	 * Clone the chromosome.
	 */
	abstract public Chromosome clone();
	
	/** 
	 * @return string with the phenotype point.
	 */
	abstract public String getPoint();

	
	//GETTERS//
	public boolean[][] getGens() {
		return _gens;
	}
	
	public int getLength(int i) {
		return _length[i];
	}
	
	public int[] getLength() {
		return _length;
	}
	
	public double getAptitude() {
		return _aptitude;
	}
	
	public double getScore() {
		return _score;
	}
	
	public double getAggregateSocore() {
		return _aggregateSocore;
	}
	
	//SETTERS//
	public void setGen(int gen, int i, boolean b) {
		_gens[gen][i] = b;
	}

	public void setGens(boolean[][] gens) {
		_gens = gens;
	}

	public void setLength(int[] length) {
		_length = length;
	}

	public void setAptitude(double aptitude) {
		_aptitude = aptitude;
	}

	public void setScore(double score) {
		_score = score;
	}

	public void setAggregateSocore(double aggregateSocore) {
		_aggregateSocore = aggregateSocore;
	}
}
