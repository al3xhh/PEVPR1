package Crossover;

public class CrossOverFactory {
	
	public static CrossoverAlgorithm getCrossoverAlgorithm(String algorithm) {
		if (algorithm.equals("Point crossover"))
			return new Point();	
		else if (algorithm.equals("Multi point crossover"))
			return new Multi();
		else if (algorithm.equals("Uniform crossover"))
			return new Uniform();
		else
			return new Point();
	}
}
