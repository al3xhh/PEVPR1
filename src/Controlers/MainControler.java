package Controlers;

import Functions.Function1;
import Models.Chromosome;
import Models.GeneticAlgorithm;
import Models.Population;
import Selection.SelectionAlgorithm;
import Selection.SelectionFactory;
import Views.Observer;

public class MainControler {
	private GeneticAlgorithm ga;
	
	public MainControler() {
		ga = new GeneticAlgorithm();
	}
	
	public void setObserver(Observer observer) {
		ga.setObserver(observer);
	}
	
	public void run(int populationSize, int generationNumber, String selection, double cross, double mutation, 
			String function, double elitism, double precision) {
		Population population = null;
		SelectionAlgorithm selectionAlgorithm = SelectionFactory.getSelectionAlgorithm(selection, 0);
		
		if(function.equals("1")) {
			Chromosome chromosomes[] = new Function1[populationSize];
			for(int i = 0; i < populationSize; i++) {
				chromosomes[i] = new Function1(0, 32, precision);
			}
			
			population = new Population(populationSize, elitism, chromosomes);
		}
		
		population.init();
		ga.setGenerationNumber(generationNumber);
		ga.setPopulation(population);
		ga.setSelectionAlgorithm(selectionAlgorithm);
		ga.setCross(cross);
		ga.setMutation(mutation);
		ga.run();
	}
}
