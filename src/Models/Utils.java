package Models;

/**
 * 
 * @author al3x_hh
 *
 */
public class Utils {
	
	/**
	 * 
	 * @param x
	 * @param base
	 * @return
	 */
	public static int log(double x, int base) {
	    return (int) (Math.log(x) / Math.log(base));
	}
	
	/**
	 * 
	 * @param bin
	 * @return
	 */
	public static int bin2dec(boolean []bin) {
		
	    double ret  = 0;
	    
	    for(int i = 0; i < bin.length; i++) {
	    	
	        if(bin[i])
	         ret = ret + Math.pow(2, i);
	    }
	    
	    return (int) ret;
	}
}
