package Selection;

import java.util.Random;

import Models.Chromosome;
import Models.Population;

public class UniversalStochastic implements SelectionAlgorithm {

	@Override
	public void selection(Population population) {
		int []survivors = new int[population.getPopultionSize()];
		double r = population.getPopultionSize() * new Random().nextDouble();
		int survivorPos;
		
		for(int i = 0; i < population.getPopultionSize(); i++) {
			r += r;
			survivorPos = 0;
			
			while((r > population.getSingle(survivorPos).getAggregateSocore()) && (survivorPos < population.getPopultionSize() - 1))
				survivorPos++;
			
			survivors[i] = survivorPos;
		}		
		
		Chromosome[] populationAux = new Chromosome[population.getPopultionSize()];
		for(int i = 0; i < population.getPopultionSize(); i++) {
			populationAux[i] = population.getSingle(survivors[i]).clone();
		}
		
		population.setPopulation(populationAux);
	}
}
