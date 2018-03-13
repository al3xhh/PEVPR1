package Selection;

import java.util.Random;

import Models.Chromosome;
import Models.Population;

public class Tournament implements SelectionAlgorithm {

	@Override
	public void selection(Population population) {
		int []survivors = new int[population.getPopultionSize()];
		int survivorPos;
		
		for(int i = 0; i < population.getPopultionSize(); i++) {
			int randomNum1 = new Random().nextInt((population.getPopultionSize()));
			int randomNum2 = new Random().nextInt((population.getPopultionSize()));
			
			survivorPos = population.getSingle(randomNum1).getAptitude() > population.getSingle(randomNum2).getAptitude() ? randomNum1 : randomNum2;
			survivors[i] = survivorPos;
		}		
		
		Chromosome[] populationAux = new Chromosome[population.getPopultionSize()];
		for(int i = 0; i < population.getPopultionSize(); i++) {
			populationAux[i] = population.getSingle(survivors[i]).clone();
		}
		
		population.setPopulation(populationAux);
	}
}
