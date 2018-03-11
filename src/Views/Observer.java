package Views;

import Models.Chromosome;

/**
 * 
 * @author al3x_hh
 *
 */
public interface Observer {
	
	/**
	 * 
	 * @param mean
	 * @param bestGeneration
	 * @param best
	 * @param generations
	 * @param bestChromosome
	 */
	public void updatePlot(double [] mean, double [] bestGeneration, double [] best, int generations, Chromosome bestChromosome);
}
