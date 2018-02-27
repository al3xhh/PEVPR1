package Models;

public class Population {
	private int popultionSize;
	private Chromosome[] population;
	private Chromosome[] bestChromosomes;
	private double[] bests;
	private double[] bestOfGeneration;
	private double[] mean;
	private int elitismCount;
	private int generation;
	
	public Population(int popultionSize, double elitism, Chromosome[] population) {
		super();
		this.popultionSize = popultionSize;
		this.population = population;
		this.bestChromosomes = new Chromosome[(int) (popultionSize * elitism / 100)];
		this.bests = new double[popultionSize];
		this.bestOfGeneration = new double[popultionSize];;
		this.mean = new double[popultionSize];
		this.elitismCount = 0;
		this.generation = 0;
	}
	
	/**
	 * 
	 */
	public void init() {
		for(Chromosome chromosome: population)
			chromosome.init();
	}
	
	/**
	 * 
	 */
	public void test() {
		double aggregateScore = 0;
		double aggregateAptitude = 0;
		double bestAptitude = 0;
		
		for(Chromosome chromosome: population) {
			chromosome.setAptitude(chromosome.test());
			aggregateAptitude += chromosome.getAptitude();
			
			if(chromosome.getAptitude() > bestAptitude) {
				bestAptitude = chromosome.getAptitude();
			}
		}	

		for(Chromosome chromosome: population) {
			chromosome.setScore(chromosome.getAptitude() / aggregateAptitude);
			chromosome.setAggregateSocore(chromosome.getScore() + aggregateScore);
			aggregateScore += (chromosome.getAptitude() / aggregateAptitude);
		}
		
		mean[generation] = aggregateScore / popultionSize;
		bests[generation] = generation == 0 ? bestAptitude : Math.max(bests[generation - 1], bests[generation]);
		bestOfGeneration[generation++] = bestAptitude;
	}
}
