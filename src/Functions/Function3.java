package Functions;

import Models.Chromosome;
import Models.Utils;

/**
 * Class which represents function 5.
 * 
 * @author Group 06.
 *
 */
public class Function3 extends Chromosome {

	/**
	 * Minimum x values array.
	 */
	private double[] _minX;
	
	/**
	 * Maximum x values array.
	 */
	private double[] _maxX;
	
	/**
	 * Precision % to take numbers.
	 */
	private double _precision;

	/**
	 * Constructor.
	 */
	public Function3(double minX, double maxX, double minX2, double maxX2, double precision) {
		_minX = new double[2];
		_maxX = new double[2];
		_minX[0] = minX;
		_minX[1] = minX2;
		_maxX[0] = maxX;
		_maxX[1] = maxX2;
		_precision = precision;
		_length = new int[2];
		_length[0] = calculateLength(minX, maxX, precision);
		_length[1] = calculateLength(minX2, maxX2, precision);
		_gens = new boolean[2][Math.max(_length[0], _length[1])];
	}

	@Override
	public double test() {
		double x1 = getPhenotype(0);
		double x2 = getPhenotype(1);
		
		return 21.5 + (x1 * (Math.sin(4 * Math.PI * x1))) + (x2 * Math.sin(20 * Math.PI * x2));
	}

	@Override
	public double getPhenotype(int index) {
		return (_minX[index] + (_maxX[index] - _minX[index]) * (Utils.bin2dec(_gens[index])) / (Math.pow(2, getLength(index)) - 1));
	}

	@Override
	public Chromosome getChild() {
		return new Function3(_minX[0], _maxX[0], _minX[1], _maxX[1], _precision);
	}
	
	@Override
	public Chromosome clone() {
		Function3 ret = new Function3(_minX[0], _maxX[0], _minX[1], _maxX[1], _precision);
		
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
		boolean[][]ret = new boolean[2][Math.max(_length[0], _length[1])];
		
		for(int i = 0; i < 2; i++)
			for(int j = 0; j < _gens[i].length; j++)
				ret[i][j] = _gens[i][j];
		
		return ret;
	}
}
