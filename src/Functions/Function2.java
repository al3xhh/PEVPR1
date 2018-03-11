package Functions;

import Models.Chromosome;
import Models.Utils;

public class Function2 extends Chromosome {

	private double minX;
	private double maxX;
	private double precision;

	public Function2(double minX, double maxX, double precision) {
		this.minX = minX;
		this.maxX = maxX;
		this.precision = precision;
		this.length = new int[2];
		length[0] = this.calculateLength(minX, maxX, precision);
		length[1] = length[0];
		this.gens = new boolean[2][length[0]];
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
		
		return -((x2 + 47) * Math.sin(Math.sqrt(Math.abs(x2 + (x1 / 2) + 47)))) - x1 * (Math.sin(Math.sqrt(Math.abs(x1 - (x2 + 47)))));
	}

	@Override
	public double getPhenotype(int index) {
		return (minX + (maxX - minX) * (Utils.bin2dec(this.gens[index])) / (Math.pow(2, this.getLength()[index]) - 1));
	}

	@Override
	public Chromosome getChild() {
		return new Function2(minX, maxX, precision);
	}
	
	@Override
	public Chromosome clone() {
		Function2 aux = new Function2(this.minX, this.maxX, this.precision);
		aux.setGens(this.gens);
		aux.setAptitude(this.aptitude);
		aux.setScore(this.score);
		aux.setAggregateSocore(this.aggregateSocore);
		aux.setLength(this.length);
		
		return aux;
	}
}
