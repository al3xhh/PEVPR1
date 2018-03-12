package Functions;

import Models.Chromosome;
import Models.Utils;

/**
 * Class which represents function 5.
 * 
 * @author Group 06.
 *
 */
public class Function5 extends Chromosome {

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
	 * Sum iterations.
	 */
	private int _n;

	/**
	 * Constructor.
	 */
	public Function5(double minX, double maxX, double precision, int n) {
		_minX = minX;
		_maxX = maxX;
		_precision = precision;
		_n = n;
		_length = new int[_n];
		
		for (int i = 0; i < _n; i++)
			_length[i] = calculateLength(minX, maxX, precision);
		
		_gens = new boolean[_n][calculateLength(minX, maxX, precision)];
	}

	@Override
	public double test() {
		double ret = 0.0;
		double xi = 0.0;
		
		for (int i = 1; i < _n + 1; i++){
			xi = getPhenotype(i - 1);
			ret += Math.sin(xi) * Math.pow(Math.sin(((i + 1) * Math.pow(xi, 2)) / Math.PI), 20);
		}
		
		return -ret;
	}

	@Override
	public double getPhenotype(int index) {
		return (_minX + (_maxX - _minX) * (Utils.bin2dec(_gens[index])) / (Math.pow(2, getLength(index)) - 1));
	}

	@Override
	public Chromosome getChild() {
		return new Function5(_minX, _maxX, _precision, this._n);
	}
	
	@Override
	public Chromosome clone() {
		Function5 ret = new Function5(_minX, _maxX, _precision, _n);
		
		ret.setGens(cloneGens());
		ret.setAptitude(_aptitude);
		ret.setScore(_score);
		ret.setAggregateSocore(_aggregateSocore);
		ret.setLength(_length);
		
		return ret;
	}
	
	@Override
	public String getPoint() {
		String ret = "";
		
		for (int i = 1; i < _n + 1; i++)
			ret += "x" + i + ": " + getPhenotype(i - 1) + ", ";
		
		return ret;
	}
	
	@Override
	public boolean[][] cloneGens() {
		boolean[][]ret = new boolean[_n][calculateLength(_minX, _maxX, _precision)];
		
		for(int i = 0; i < _n; i++)
			for(int j = 0; j < _gens[i].length; j++)
				ret[i][j] = _gens[i][j];
		
		return ret;
	}
}
