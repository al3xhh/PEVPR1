package Selection;

import Models.Chromosome;
import Models.Population;

public class Truncation implements SelectionAlgorithm {
	
	private double _truncation;
	
	public Truncation(double truncation) {
		_truncation = truncation;
	}

	@Override
	public void selection(Population population) {
		int []survivors = new int[population.getPopultionSize()];
		
		population.sort();
		
		for(int i = 0; i < _truncation * population.getPopultionSize(); i++) {					
			for(int j = 0; j < 1 / _truncation; j++) {
				int aux = (int) (i * 1 / _truncation + j);
				survivors[aux] = i;
			}
		}
		
		Chromosome[] populationAux = new Chromosome[population.getPopultionSize()];
		for(int i = 0; i < population.getPopultionSize(); i++) {
			populationAux[i] = population.getSingle(survivors[i]).clone();
		}
		
		population.setPopulation(populationAux);
	}
}
