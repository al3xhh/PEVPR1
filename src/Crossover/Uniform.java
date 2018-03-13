package Crossover;

import Models.Chromosome;
import Models.Population;

public class Uniform implements CrossoverAlgorithm {

	@Override
	public void crossover(Population population, double crossOver) {
		int crossSelection[] = new int[population.getPopultionSize()];
		int selectedNum = 0;
		
		for(int i = 0; i < population.getPopultionSize(); i ++) {
			if(Math.random() < crossOver) {
				crossSelection[selectedNum] = i;
				selectedNum++;
			}
		}
		
		if(selectedNum % 2 == 1) selectedNum --;
		
		Chromosome parent1, parent2;
		
		for(int i = 0; i < selectedNum; i+= 2) {
			parent1 = population.getSingle(crossSelection[i]);
			parent2 = population.getSingle(crossSelection[i + 1]);
			
			cross(parent1, parent2);
		}
	}
	
	private void cross(Chromosome parent1, Chromosome parent2) {
		double prob;
		boolean aux;
		
		for (int j = 0; j < parent1.getLength().length; j++) {
			for(int i = 0; i < parent1.getLength()[j]; i++) {
				prob = Math.random();
				
				if(prob < 0.5) {
					aux = parent2.getGens()[j][i];
					parent2.getGens()[j][i] = parent1.getGens()[j][i];
					parent1.getGens()[j][i] = aux;
				}
			}
		}
		
		parent1.setAptitude(parent1.test());
		parent2.setAptitude(parent2.test());
	}
}
