package Functions;

import Models.Chromosome;
import Models.Utils;

public class Function1 extends Chromosome {
	
	private double minX;
	private double maxX;
	private double precision;

	public Function1(double minX, double maxX, double precision) {
		this.minX = minX;
		this.maxX = maxX;
		this.precision = precision;
		this.length = new int[1];
		length[0] = this.calculateLength(minX, maxX, precision);
		this.gens = new boolean[1][length[0]];
	}
	
	@Override
	public void init() {

		for(int i = 0; i < length[0]; i ++) {
			this.gens[0][i] = Math.random() < 0.5 ? false : true;
		}
			
	}

	@Override
	public double test() {
		double x = getPhenotype(0);
		
		return 20 + Math.E - (20 * (Math.pow(Math.E, -0.2 * Math.abs(x)))) - Math.pow(Math.E, Math.cos(2 * Math.PI * x));		
	}

	@Override
	public double getPhenotype(int index) {
		return (minX + (maxX - minX) * (Utils.bin2dec(this.getAlleles(index))) / (Math.pow(2, this.getLength()[0]) - 1));
	}

	@Override
	public Chromosome getChild() {
		return new Function1(minX, maxX, precision);
	}

	@Override
	public Chromosome clone() {
		Function1 aux = new Function1(this.minX, this.maxX, this.precision);
		aux.setGens(this.gens);
		aux.setAptitude(this.aptitude);
		aux.setScore(this.score);
		aux.setAggregateSocore(this.aggregateSocore);
		aux.setLength(this.length);
		
		return aux;
	}
	
	
}
