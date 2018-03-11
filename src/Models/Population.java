package Models;

import java.util.Random;

public class Population {
	private int popultionSize;
	private Chromosome[] population;
	private Chromosome[] bestChromosomes;
	private double[] bests;
	private double[] bestOfGeneration;
	private double[] mean;
	private int elitismCount;
	private int generation;
	private String type;
	
	public Population(int popultionSize, int generations, double elitism, Chromosome[] population, String type) {
		super();
		this.popultionSize = popultionSize;
		this.population = population;
		this.bestChromosomes = new Chromosome[(int) (popultionSize * elitism / 100)];
		this.bests = new double[generations];
		this.bestOfGeneration = new double[generations];;
		this.mean = new double[generations];
		this.elitismCount = 0;
		this.generation = 0;
		this.type = type;
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
	 * @return
	 */
	public int getPopultionSize() {
		return popultionSize;
	}
	
	/**
	 * 
	 * @param pos
	 * @return
	 */
	public Chromosome getSingle(int pos) {
		return this.population[pos];
	}
	
	/**
	 * 
	 * @param citizen
	 * @param pos
	 */
	public void setSingle(Chromosome single, int pos) {
		this.population[pos] = single;
	}

	public double[] getBests() {
		return bests;
	}

	public double[] getBestOfGeneration() {
		return bestOfGeneration;
	}

	public double[] getMean() {
		return mean;
	}
	
	public void setPopulation(Chromosome[] population){
		this.population = population;
	}

	/**
	 * 
	 */
	public void test() {
		double aggregateScore = 0;
		double aggregateAptitude = 0;
		double bestAptitude = type.equals("max") ? Double.MIN_VALUE : Double.MAX_VALUE;
		double maxMin = type.equals("max") ? Double.MAX_VALUE : - Double.MAX_VALUE;
		
		for(Chromosome chromosome: population) {			
			if(type.equals("max") ? chromosome.test() < maxMin : chromosome.test() > maxMin) {
				maxMin = chromosome.test();// chromosome.getAptitude();
			}
			aggregateAptitude += chromosome.test();
		}
		
		mean[generation] = new Double(aggregateAptitude / popultionSize).doubleValue();
		aggregateAptitude = 0;
		
		for(Chromosome chromosome: population) {
			if (type.equals("max"))
				chromosome.setAptitude(chromosome.test() + Math.abs(maxMin) + 1.0);
			else
				chromosome.setAptitude((Math.abs(maxMin) + 1.0) - chromosome.test());
			if(type.equals("max") ? chromosome.test() > bestAptitude : chromosome.test() < bestAptitude) {
				bestAptitude = chromosome.test();
			}
			aggregateAptitude += chromosome.getAptitude();
		}
		
		for(Chromosome chromosome: population) {
			chromosome.setScore(new Double(chromosome.getAptitude() / aggregateAptitude).doubleValue());
			chromosome.setAggregateSocore(new Double(chromosome.getScore() + aggregateScore).doubleValue());
			aggregateScore += chromosome.getScore();
		}
		
		bests[generation] = generation == 0 ? bestAptitude : type.equals("max") ? Math.max(bests[generation - 1], bestAptitude) : Math.min(bests[generation - 1], bestAptitude);
		bestOfGeneration[generation++] = bestAptitude;
	}
	
	public void mutation(double mutation) {
		for(Chromosome chromosome: population) {
			chromosome.mutation(mutation);
		}
	}
	
	/**
	 * 
	 * @param cross
	 */
	public void reproduction(double cross) {
		int crossSelection[] = new int[popultionSize];
		int selectedNum = 0;
		
		for(int i = 0; i < popultionSize; i ++) {
			if(Math.random() < cross) {
				crossSelection[selectedNum] = i;
				selectedNum++;
			}
		}
		
		if(selectedNum % 2 == 1) selectedNum --;
		
		int crossPoint = new Random().nextInt((population[0].getLength()[0] - 0) + 1) + 0;
		Chromosome child1, child2;
		Chromosome parent1, parent2;
		
		for(int i = 0; i < selectedNum; i+= 2) {
			parent1 = population[crossSelection[i]];
			parent2 = population[crossSelection[i + 1]];
			child1 = parent1.getChild();
			child2 = parent2.getChild();
			
			cross(parent1, parent2, child1, child2, crossPoint);
			
			population[crossSelection[i]] = child1;
			population[crossSelection[i + 1]] = child2;
		}
	}
	
	/**
	 * 
	 * @param parent1
	 * @param parent2
	 * @param child1
	 * @param child2
	 * @param crossPoint
	 */
	private void cross(Chromosome parent1, Chromosome parent2, Chromosome child1, Chromosome child2, int crossPoint) {
		child1.init();
		child2.init();
		
		for (int j = 0; j < parent1.getLength().length; j++){
			for(int i = 0; i < crossPoint; i++) {
				child1.setGen(j, i, parent1.getGens()[j][i]);
				child2.setGen(j, i, parent2.getGens()[j][i]);
			}
			
			for(int i = crossPoint; i < parent1.getLength()[j]; i++) {
				child1.setGen(j, i, parent2.getGens()[j][i]);
				child2.setGen(j, i, parent1.getGens()[j][i]);
			}
		}
		child1.setAptitude(child1.test());
		child2.setAptitude(child2.test());
	}
}
