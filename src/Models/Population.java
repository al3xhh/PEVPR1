package Models;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

/**
 * Class which represents the population.
 * 
 * @author Group 06.
 *
 */
public class Population {
	
	/**
	 * 
	 */
	private int _popultionSize;
	
	/**
	 * 
	 */
	private Chromosome[] _population;
	
	/**
	 * 
	 */
	private ArrayList<Chromosome> _bestChromosomes;
	
	/**
	 * 
	 */
	private double[] _bests;
	
	/**
	 * 
	 */
	private double[] _bestOfGeneration;
	
	/**
	 * 
	 */
	private double[] _mean;
	
	/**
	 * 
	 */
	private int _elitismCount;
	
	/**
	 * 
	 */
	private int _generation;
	
	/**
	 * 
	 */
	private String _type;
	
	/**
	 * 
	 */
	private Chromosome _bestChromosome;
	
	/**
	 * Constructor.
	 */
	public Population(int popultionSize, int generations, double elitism, Chromosome[] population, String type) {
		_popultionSize = popultionSize;
		_population = population;
		_elitismCount = new Double(elitism * popultionSize).intValue();
		_bestChromosomes = new ArrayList<>();
		_bests = new double[generations];
		_bestOfGeneration = new double[generations];;
		_mean = new double[generations];
		_generation = 0;
		_bestChromosome = population[0].clone();
		_type = type;
	}
	
	/**
	 * 
	 */
	public void init() {
		for(Chromosome chromosome: _population)
			chromosome.init();
	}
	
	public int getPopultionSize() {
		return _popultionSize;
	}
	
	public Chromosome getSingle(int pos) {
		return _population[pos];
	}
	
	public void setSingle(Chromosome single, int pos) {
		this._population[pos] = single;
	}

	public double[] getBests() {
		return _bests;
	}

	public double[] getBestOfGeneration() {
		return _bestOfGeneration;
	}

	public double[] getMean() {
		return _mean;
	}
	
	public void setPopulation(Chromosome[] population){
		this._population = population;
	}
	
	public Chromosome getBestChromosome() {
		return _bestChromosome;
	}
	
	public ArrayList<Chromosome> getBestChromosomes() {
		return _bestChromosomes;
	}
	
	public int getElitismCount() {
		return _elitismCount;
	}

	/**
	 * 
	 */
	public void test() {
		double aggregateScore = 0;
		double aggregateAptitude = 0;
		double bestAptitude = _type.equals("max") ? Double.MIN_VALUE : Double.MAX_VALUE;
		double maxMin = _type.equals("max") ? Double.MAX_VALUE : - Double.MAX_VALUE;
		
		for(Chromosome chromosome: _population) {			
			if(_type.equals("max") ? chromosome.test() < maxMin : chromosome.test() > maxMin)
				maxMin = chromosome.test();
				
			aggregateAptitude += chromosome.test();
		}
		
		_mean[_generation] = new Double(aggregateAptitude / _popultionSize).doubleValue();
		aggregateAptitude = 0;
		
		for(Chromosome chromosome: _population) {
			if (_type.equals("max"))
				chromosome.setAptitude(chromosome.test() + Math.abs(maxMin) + 1.0);
			else
				chromosome.setAptitude((Math.abs(maxMin) + 1.0) - chromosome.test());
			if(_type.equals("max") ? chromosome.test() > bestAptitude : chromosome.test() < bestAptitude)
				bestAptitude = chromosome.test();
			
			if(_type.equals("max") ? chromosome.test() > _bestChromosome.getAptitude() : chromosome.test() < _bestChromosome.getAptitude())
				_bestChromosome = chromosome.clone();

			aggregateAptitude += chromosome.getAptitude();
		}
		
		for(Chromosome chromosome: _population) {
			chromosome.setScore(new Double(chromosome.getAptitude() / aggregateAptitude).doubleValue());
			chromosome.setAggregateSocore(new Double(chromosome.getScore() + aggregateScore).doubleValue());
			aggregateScore += chromosome.getScore();
		}
		
		_bests[_generation] = _generation == 0 ? bestAptitude : _type.equals("max") ? Math.max(_bests[_generation - 1], bestAptitude) 
				: Math.min(_bests[_generation - 1], bestAptitude);
		_bestOfGeneration[_generation++] = bestAptitude;
	}
	
	public void setAptitude() {
		double maxMin = _type.equals("max") ? Double.MAX_VALUE : - Double.MAX_VALUE;
		
		for(Chromosome chromosome: _population) {			
			if(_type.equals("max") ? chromosome.test() < maxMin : chromosome.test() > maxMin)
				maxMin = chromosome.test();
		}
		
		
		for(Chromosome chromosome: _population) {
			if (_type.equals("max"))
				chromosome.setAptitude(chromosome.test() + Math.abs(maxMin) + 1.0);
			else
				chromosome.setAptitude((Math.abs(maxMin) + 1.0) - chromosome.test());
		}
	}
	
	public void mutation(double mutation) {
		for(Chromosome chromosome: _population) {
			chromosome.mutation(mutation);
		}
	}
	
	/**
	 * 
	 * @param cross
	 */
	public void reproduction(double cross) {
		int crossSelection[] = new int[_popultionSize];
		int selectedNum = 0;
		
		for(int i = 0; i < _popultionSize; i ++) {
			if(Math.random() < cross) {
				crossSelection[selectedNum] = i;
				selectedNum++;
			}
		}
		
		if(selectedNum % 2 == 1) selectedNum --;
		
		int crossPoint = new Random().nextInt((_population[0].getLength(0) - 0) + 1) + 0;
		Chromosome child1, child2;
		Chromosome parent1, parent2;
		
		for(int i = 0; i < selectedNum; i+= 2) {
			parent1 = _population[crossSelection[i]];
			parent2 = _population[crossSelection[i + 1]];
			child1 = parent1.getChild();
			child2 = parent2.getChild();
			
			cross(parent1, parent2, child1, child2, crossPoint);
			
			_population[crossSelection[i]] = child1;
			_population[crossSelection[i + 1]] = child2;
		}
	}
	
	public void replaceElite() {
		double aptitude;
		ArrayList<Integer> worstChromosomes = new ArrayList<>();
		int best;
		
		for(int i = 0; i < _popultionSize; i++) {
			aptitude = -Double.MAX_VALUE;
			best = 0;
			
			if(worstChromosomes.size() == _elitismCount) {
				for(int c: worstChromosomes) {
					if(_population[c].getAptitude() > aptitude) {
						aptitude = _population[c].getAptitude();
						best = c;
					}
				}
				if(_population[i].getAptitude() < _population[best].getAptitude()) {
					worstChromosomes.remove(worstChromosomes.indexOf(best));
					worstChromosomes.add(i);
				}
			} else {
				worstChromosomes.add(i);
			}
		}
		
		for(int i = 0; i < _elitismCount; i++)
			_population[worstChromosomes.get(i)] = _bestChromosomes.get(i);
	}
	
	public void findElite() {
		double aptitude;
		Chromosome worst;
		
		for(Chromosome chromosome: _population) {
			aptitude = Double.MAX_VALUE;
			worst = null;
			
			if(_bestChromosomes.size() == _elitismCount) {
				for(Chromosome c: _bestChromosomes) {
					if(c.getAptitude() < aptitude) {
						aptitude = c.getAptitude();
						worst = c;
					}
				}
				
				if(chromosome.getAptitude() > worst.getAptitude()) {
					_bestChromosomes.remove(_bestChromosomes.indexOf(worst));
					_bestChromosomes.add(chromosome.clone());
				}
			} else {
				_bestChromosomes.add(chromosome.clone());
			}
		}
	}
	
	public void sort() {   
        quickSort(0, _popultionSize - 1);
    }
	
	private void quickSort(int lowerIndex, int higherIndex) {
        
        int i = lowerIndex;
        int j = higherIndex;
        Chromosome pivot = _population[lowerIndex + (higherIndex - lowerIndex) / 2];
        
        // Divide into two arrays
        while (i <= j) {
            while (_population[i].getAptitude() < pivot.getAptitude()) {
                i++;
            }
            
            while (_population[j].getAptitude() > pivot.getAptitude()) {
                j--;
            }
            
            if (i <= j) {
                exchangeNumbers(i, j);
                i++;
                j--;
            }
        }
        
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }
 
    private void exchangeNumbers(int i, int j) {
        Chromosome temp = _population[i];
        
        _population[i] = _population[j];
        _population[j] = temp;
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

	public void resetBest() {
		_bestChromosomes = new ArrayList<>();
	}
}
