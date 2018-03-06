package Selection;

import Models.Population;

/**
 * 
 * @author al3x_hh
 *
 */
public class Roulette extends SelectionAlgorithm {

	@Override
	public void selection(Population population) {
		double prob;
		int survivorPos;
		
		for(int i = 0; i < population.getPopultionSize(); i++) {
			prob = Math.random();
			survivorPos = 0;
			
			while((prob > population.getSingle(survivorPos).getAggregateSocore()) && (survivorPos < population.getPopultionSize() - 1))
				survivorPos++;
			
			population.setSingle(population.getSingle(survivorPos), i);
		}		
	}
}
