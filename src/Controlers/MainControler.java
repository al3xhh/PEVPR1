package Controlers;

import Functions.Function1;
import Functions.Function2;
import Functions.Function3;
import Functions.Function4;
import Functions.Function5;
//import Functions.Function2;
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
			String function, double elitism, double precision, int n) {
		Population population = null;
		SelectionAlgorithm selectionAlgorithm = SelectionFactory.getSelectionAlgorithm(selection, 0);
		
		if(function.equals("1")) {
			Chromosome chromosomes[] = new Function1[populationSize];
			for(int i = 0; i < populationSize; i++) {
				chromosomes[i] = new Function1(0, 32, precision);
			}
			
			population = new Population(populationSize, generationNumber, elitism, chromosomes, "max");
		} else if(function.equals("2")) {
			Chromosome chromosomes[] = new Function2[populationSize];
			for(int i = 0; i < populationSize; i++) {
				chromosomes[i] = new Function2(-512, 512, precision);
			}
			
			population = new Population(populationSize, generationNumber, elitism, chromosomes, "min");
		} else if(function.equals("3")) {
			Chromosome chromosomes[] = new Function3[populationSize];
			for(int i = 0; i < populationSize; i++) {
				chromosomes[i] = new Function3(-3.0, 12.1, 4.1, 5.8, precision);
			}
			
			population = new Population(populationSize, generationNumber, elitism, chromosomes, "max");
		} else if(function.equals("4")) {
			Chromosome chromosomes[] = new Function4[populationSize];
			for(int i = 0; i < populationSize; i++) {
				chromosomes[i] = new Function4(-10, 10, precision);
			}
			
			population = new Population(populationSize, generationNumber, elitism, chromosomes, "min");
		} else if(function.equals("5")) {
			Chromosome chromosomes[] = new Function5[populationSize];
			for(int i = 0; i < populationSize; i++) {
				chromosomes[i] = new Function5(0, Math.PI, precision, n);
			}
			
			population = new Population(populationSize, generationNumber, elitism, chromosomes, "min");
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
