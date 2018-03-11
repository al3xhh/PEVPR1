package Functions;

import Models.Chromosome;
import Models.Utils;

public class Function4 extends Chromosome {

	private double minX;
	private double maxX;
	private double precision;

	public Function4(double minX, double maxX, double precision) {
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
		double aux1 = 0.0;
		double aux2 = 0.0;
		
		for (int i = 1; i < 6; i++){
			aux1 += i * Math.cos((i+1) * x1 + i);
		}
		
		for (int i = 1; i < 6; i++){
			aux2 += i * Math.cos((i+1) * x2 + i);
		}
		
		return aux1 * aux2;
	}

	@Override
	public double getPhenotype(int index) {
		return (minX + (maxX - minX) * (Utils.bin2dec(this.gens[index])) / (Math.pow(2, this.getLength()[index]) - 1));
	}

	@Override
	public Chromosome getChild() {
		return new Function4(minX, maxX, precision);
	}
	
	@Override
	public Chromosome clone() {
		Function4 aux = new Function4(this.minX, this.maxX, this.precision);
		aux.setGens(this.gens);
		aux.setAptitude(this.aptitude);
		aux.setScore(this.score);
		aux.setAggregateSocore(this.aggregateSocore);
		aux.setLength(this.length);
		
		return aux;
	}
}
