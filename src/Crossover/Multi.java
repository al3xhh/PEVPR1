package Crossover;

import java.util.Random;

import Models.Chromosome;
import Models.Population;

public class Multi implements CrossoverAlgorithm {

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
		
		int crossPoint1 = new Random().nextInt((population.getSingle(0).getLength(0)) + 1);
		int crossPoint2 = new Random().nextInt((population.getSingle(0).getLength(0)) + 1);
		
		Chromosome child1, child2;
		Chromosome parent1, parent2;
		
		for(int i = 0; i < selectedNum; i+= 2) {
			parent1 = population.getSingle(crossSelection[i]);
			parent2 = population.getSingle(crossSelection[i + 1]);
			child1 = parent1.getChild();
			child2 = parent2.getChild();
			
			cross(parent1, parent2, child1, child2, crossPoint1, crossPoint2);
			
			population.setSingle(child1, crossSelection[i]);
			population.setSingle(child2, crossSelection[i + 1]);
		}
	}

	private void cross(Chromosome parent1, Chromosome parent2, Chromosome child1, Chromosome child2, int crossPoint1, int crossPoint2) {
		child1.init();
		child2.init();
		
		for (int j = 0; j < parent1.getLength().length; j++){
			for(int i = 0; i < crossPoint1; i++) {
				child1.setGen(j, i, parent1.getGens()[j][i]);
				child2.setGen(j, i, parent2.getGens()[j][i]);
			}
			
			for(int i = crossPoint1; i < crossPoint2; i++) {
				child1.setGen(j, i, parent2.getGens()[j][i]);
				child2.setGen(j, i, parent1.getGens()[j][i]);
			}
			
			for(int i = crossPoint2; i < parent1.getLength()[j]; i++) {
				child1.setGen(j, i, parent1.getGens()[j][i]);
				child2.setGen(j, i, parent2.getGens()[j][i]);
			}
		}
		child1.setAptitude(child1.test());
		child2.setAptitude(child2.test());
	}
}
