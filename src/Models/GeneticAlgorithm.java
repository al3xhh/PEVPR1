package Models;

import Selection.SelectionAlgorithm;
import Views.Observer;

public class GeneticAlgorithm {
	private int generationNumber;
	private Population population;
	private SelectionAlgorithm selectionAlgorithm;
	private Observer observer;
	private double cross;
	private double mutation;
	
	public void setGenerationNumber(int generationNumber) {
		this.generationNumber = generationNumber;
	}

	public void setPopulation(Population population) {
		this.population = population;
	}

	public void setSelectionAlgorithm(SelectionAlgorithm selectionAlgorithm) {
		this.selectionAlgorithm = selectionAlgorithm;
	}

	public void setCross(double cross) {
		this.cross = cross;
	}

	public void setMutation(double mutation) {
		this.mutation = mutation;
	}


	public void run() {
		population.test();
		
		for(int i = 0; i < generationNumber - 1; i++) {
			selectionAlgorithm.selection(population);
			population.reproduction(cross);
			population.mutation(mutation);
			population.test();
		}
		
		observer.updatePlot(population.getMean(), population.getBestOfGeneration(), population.getBests(), generationNumber);
	}
	
	public void setObserver(Observer observer) {
		this.observer = observer;
	}
}
