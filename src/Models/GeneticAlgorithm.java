package Models;

import Crossover.CrossoverAlgorithm;
import Selection.SelectionAlgorithm;
import Views.Observer;

/**
 * Class which represents the genetic algorithm.
 * 
 * @author Group 06.
 *
 */
public class GeneticAlgorithm {
	
	/**
	 * Number of generations to iterate.
	 */
	private int _generationNumber;
	
	/**
	 * Population to use.
	 */
	private Population _population;
	
	/**
	 * Selection algorithm type.
	 */
	private SelectionAlgorithm _selectionAlgorithm;
	
	/**
	 * Crossover algorithm type.
	 */
	private CrossoverAlgorithm _crossoverAlgorithm;
	
	/**
	 * Main view's observer.
	 */
	private Observer _observer;
	
	/**
	 * Cross %.
	 */
	private double _cross;
	
	/**
	 * Mutation %.
	 */
	private double _mutation;

	/**
	 * Run the genetic algorithm.
	 */
	public void run() {
		_population.test();
		
		for(int i = 0; i < _generationNumber - 1; i++) {
			if(_population.getElitismCount() > 0)
				_population.findElite();
			
			_selectionAlgorithm.selection(_population);
			_crossoverAlgorithm.crossover(_population, _cross);
			_population.mutation(_mutation);
			_population.setAptitude();
			
			if(_population.getElitismCount() > 0)
				_population.replaceElite();
			
			_population.test();
		}
		
		_population.resetBest();
		_observer.updatePlot(_population.getMean(), _population.getBestOfGeneration(), _population.getBests(), 
				_generationNumber, _population.getBestChromosome());
	}
	
	//SETTERS//

	public void setObserver(Observer observer) {
		_observer = observer;
	}
		
	public void setGenerationNumber(int generationNumber) {
		_generationNumber = generationNumber;
	}

	public void setPopulation(Population population) {
		_population = population;
	}

	public void setSelectionAlgorithm(SelectionAlgorithm selectionAlgorithm) {
		_selectionAlgorithm = selectionAlgorithm;
	}
	
	public void setCrossOverAlgorithm(CrossoverAlgorithm crossoverAlgorithm) {
		_crossoverAlgorithm = crossoverAlgorithm;
	}

	public void setCross(double cross) {
		_cross = cross;
	}

	public void setMutation(double mutation) {
		_mutation = mutation;
	}
}
