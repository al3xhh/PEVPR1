package Views;

public interface Observer {
	public void updatePlot(double [] meanX, double [] meanY,
					       double [] bestGenerationX, double [] bestGenerationY,
						   double [] bestX,double [] bestY);
}
