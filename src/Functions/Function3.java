package Functions;

import Models.Chromosome;
import Models.Utils;

public class Function3 extends Chromosome {

	private double[] minX;
	private double[] maxX;
	private double precision;

	public Function3(double minX, double maxX, double minX2, double maxX2, double precision) {
		this.minX = new double[2];
		this.maxX = new double[2];
		this.minX[0] = minX;
		this.minX[1] = minX2;
		this.maxX[0] = maxX;
		this.maxX[1] = maxX2;
		
		this.precision = precision;
		this.length = new int[2];
		length[0] = this.calculateLength(minX, maxX, precision);
		length[1] = this.calculateLength(minX2, maxX2, precision);
		this.gens = new boolean[2][Math.max(length[0], length[1])];
	}
	
	@Override
	public void init() {
		for (int j = 0; j < this.length.length; j++){
			for(int i = 0; i < this.length[j]; i ++) {
				this.gens[j][i] = Math.random() < 0.5 ? false : true;
			}
		}
	}

	@Override
	public double test() {
		double x1 = getPhenotype(0);
		double x2 = getPhenotype(1);
		
		return 21.5 + (x1 * (Math.sin(4 * Math.PI * x1))) + (x2 * Math.sin(20 * Math.PI * x2));
	}

	@Override
	public double getPhenotype(int index) {
		return (minX[index] + (maxX[index] - minX[index]) * (Utils.bin2dec(this.gens[index])) / (Math.pow(2, this.getLength()[index]) - 1));
	}

	@Override
	public Chromosome getChild() {
		return new Function3(minX[0], maxX[0], minX[1], maxX[1], precision);
	}
	
	@Override
	public Chromosome clone() {
		Function3 aux = new Function3(minX[0], maxX[0], minX[1], maxX[1], this.precision);
		aux.setGens(this.gens);
		aux.setAptitude(this.aptitude);
		aux.setScore(this.score);
		aux.setAggregateSocore(this.aggregateSocore);
		aux.setLength(this.length);
		
		return aux;
	}
}
