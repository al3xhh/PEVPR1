package Views;

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
	 */
	public void updatePlot(double [] mean, double [] bestGeneration, double [] best);
}
