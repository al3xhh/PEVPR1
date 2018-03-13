package Controlers;

import Crossover.CrossOverFactory;
import Crossover.CrossoverAlgorithm;
import Functions.Function1;
import Functions.Function2;
import Functions.Function3;
import Functions.Function4;
import Functions.Function5;

import Models.Chromosome;
import Models.GeneticAlgorithm;
import Models.Population;

import Selection.SelectionAlgorithm;
import Selection.SelectionFactory;

import Views.Observer;

/**
 * Controller class.
 * 
 * @author Group 06
 *
 */
public class MainControler {
	
	/**
	 * Genetic algorithm attribute.
	 */
	private GeneticAlgorithm _ga;
	
	/**
	 * Constructor.
	 */
	public MainControler() {
		_ga = new GeneticAlgorithm();
	}
	
	/**
	 * Set observer.
	 * 
	 * @param observer which represents main view.
	 */
	public void setObserver(Observer observer) {
		_ga.setObserver(observer);
	}
	
	/**
	 * Run the genetic algorithm.
	 * 
	 * @param populationSize population size.
	 * @param generationNumber number of generation to iterate.
	 * @param selection selection's algorithm type.
	 * @param cross cross %.
	 * @param mutation mutation %.
	 * @param function problem to maximize of minimize.
	 * @param elitism elitism %:
	 * @param precision precision %.
	 * @param n iterations number for problem 5.
	 * @param truncation percentage of truncation selection.
	 */
	public void run(int populationSize, int generationNumber, 
					String selection, double cross, double mutation, 
					String function, double elitism, double precision, int n, double truncation, String crossOver) {
		
		Population population = null;
		SelectionAlgorithm selectionAlgorithm = SelectionFactory.getSelectionAlgorithm(selection, truncation);
		CrossoverAlgorithm crossoverAlgorithm = CrossOverFactory.getCrossoverAlgorithm(crossOver);
		
		if(function.equals("1")) {
			Chromosome chromosomes[] = new Function1[populationSize];
			
			for(int i = 0; i < populationSize; i++)
				chromosomes[i] = new Function1(0, 32, precision);
			
			population = new Population(populationSize, generationNumber, elitism, chromosomes, "max");
		} else if(function.equals("2")) {
			Chromosome chromosomes[] = new Function2[populationSize];
			
			for(int i = 0; i < populationSize; i++)
				chromosomes[i] = new Function2(-512, 512, precision);
			
			population = new Population(populationSize, generationNumber, elitism, chromosomes, "min");
		} else if(function.equals("3")) {
			Chromosome chromosomes[] = new Function3[populationSize];
			
			for(int i = 0; i < populationSize; i++)
				chromosomes[i] = new Function3(-3.0, 12.1, 4.1, 5.8, precision);
			
			population = new Population(populationSize, generationNumber, elitism, chromosomes, "max");
		} else if(function.equals("4")) {
			Chromosome chromosomes[] = new Function4[populationSize];
			
			for(int i = 0; i < populationSize; i++)
				chromosomes[i] = new Function4(-10, 10, precision);
			
			population = new Population(populationSize, generationNumber, elitism, chromosomes, "min");
		} else if(function.equals("5")) {
			Chromosome chromosomes[] = new Function5[populationSize];
			
			for(int i = 0; i < populationSize; i++)
				chromosomes[i] = new Function5(0, Math.PI, precision, n);
			
			population = new Population(populationSize, generationNumber, elitism, chromosomes, "min");
		}
		
		population.init();
		_ga.setGenerationNumber(generationNumber);
		_ga.setPopulation(population);
		_ga.setSelectionAlgorithm(selectionAlgorithm);
		_ga.setCrossOverAlgorithm(crossoverAlgorithm);
		_ga.setCross(cross);
		_ga.setMutation(mutation);
		_ga.run();
	}
}
