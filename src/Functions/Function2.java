package Functions;

import Models.Chromosome;
import Models.Utils;

/**
 * Class which represents function 2.
 * 
 * @author Group 06.
 *
 */
public class Function2 extends Chromosome {

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
	public Function2(double minX, double maxX, double precision) {
		_minX = minX;
		_maxX = maxX;
		_precision = precision;
		_length = new int[2];
		_length[0] = calculateLength(minX, maxX, precision);
		_length[1] = calculateLength(minX, maxX, precision);
		_gens = new boolean[2][calculateLength(minX, maxX, precision)];
	}

	@Override
	public double test() {
		double x1 = getPhenotype(0);
		double x2 = getPhenotype(1);
		
		return -((x2 + 47) * Math.sin(Math.sqrt(Math.abs(x2 + (x1 / 2) + 47)))) - x1 * (Math.sin(Math.sqrt(Math.abs(x1 - (x2 + 47)))));
	}

	@Override
	public double getPhenotype(int index) {
		return (_minX + (_maxX - _minX) * (Utils.bin2dec(_gens[index])) / (Math.pow(2, getLength(index)) - 1));
	}

	@Override
	public Chromosome getChild() {
		return new Function2(_minX, _maxX, _precision);
	}
	
	@Override
	public Chromosome clone() {
		Function2 ret = new Function2(_minX, _maxX, _precision);
		
		ret.setGens(cloneGens());
		ret.setAptitude(_aptitude);
		ret.setScore(_score);
		ret.setAggregateSocore(_aggregateSocore);
		ret.setLength(_length);
		
		return ret;
	}
	
	@Override
	public String getPoint() {
		return "x1 = " + getPhenotype(0) + ", x2 = " + getPhenotype(1);
	}
	
	@Override
	public boolean[][] cloneGens() {
		boolean[][]ret = new boolean[2][calculateLength(_minX, _maxX, _precision)];
		
		for(int i = 0; i < 2; i++)
			for(int j = 0; j < _gens[i].length; j++)
				ret[i][j] = _gens[i][j];
		
		return ret;
	}
}
