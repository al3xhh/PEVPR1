package Models;

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
			_selectionAlgorithm.selection(_population);
			_population.reproduction(_cross);
			_population.mutation(_mutation);
			_population.test();
		}
		
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

	public void setCross(double cross) {
		_cross = cross;
	}

	public void setMutation(double mutation) {
		_mutation = mutation;
	}
}
