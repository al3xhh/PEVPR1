package Functions;

import Models.Chromosome;
import Models.Utils;

/**
 * Class which represents function 1.
 * 
 * @author Group 06.
 *
 */
public class Function1 extends Chromosome {
	
	/**
	 * Minimum x value.
	 */
	private double _minX;
	
	/**
	 * Maximum x value.
	 */
	private double _maxX;
	
	/**
	 * Precision % to take numbers.
	 */
	private double _precision;

	/**
	 * Constructor.
	 */
	public Function1(double minX, double maxX, double precision) {
		_minX = minX;
		_maxX = maxX;
		_precision = precision;
		_length = new int[1];
		_length[0] = calculateLength(minX, maxX, precision);
		_gens = new boolean[1][calculateLength(minX, maxX, precision)];
	}

	@Override
	public double test() {
		double x = getPhenotype(0);
		
		return 20 + Math.E - (20 * (Math.pow(Math.E, -0.2 * Math.abs(x)))) - Math.pow(Math.E, Math.cos(2 * Math.PI * x));		
	}

	@Override
	public double getPhenotype(int index) {
		return (_minX + (_maxX - _minX) * (Utils.bin2dec(_gens[index])) / (Math.pow(2, getLength(index)) - 1));
	}

	@Override
	public Chromosome getChild() {
		return new Function1(_minX, _maxX, _precision);
	}

	@Override
	public Chromosome clone() {
		Function1 ret = new Function1(_minX, _maxX, _precision);
		
		ret.setGens(cloneGens());
		ret.setAptitude(_aptitude);
		ret.setScore(_score);
		ret.setAggregateSocore(_aggregateSocore);
		ret.setLength(_length);
		
		return ret;
	}

	@Override
	public String getPoint() {
		return "x = " + getPhenotype(0);
	}

	@Override
	public boolean[][] cloneGens() {
		boolean[][]ret = new boolean[1][calculateLength(_minX, _maxX, _precision)];
		
		for(int i = 0; i < _gens[0].length; i++)
			ret[0][i] = _gens[0][i];
		
		return ret;
	}
}
