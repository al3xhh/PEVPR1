package Functions;

import Models.Chromosome;
import Models.Utils;

public class Function5 extends Chromosome {

	private double minX;
	private double maxX;
	private double precision;
	private int n;

	public Function5(double minX, double maxX, double precision, int n) {
		this.n = n;
		this.minX = minX;
		this.maxX = maxX;
		this.precision = precision;
		this.length = new int[n];
		length[0] = this.calculateLength(minX, maxX, precision);
		for (int i = 1; i < n; i++)
			length[i] = length[0];
		
		this.gens = new boolean[n][length[0]];
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
		double aux = 0.0;
		
		for (int i = 1; i < this.n + 1; i++){
			double xi = getPhenotype(i - 1);
			aux += Math.sin(xi) * Math.pow(Math.sin(((i + 1) * Math.pow(xi, 2))/Math.PI), 20);
		}
		
		return -aux;
	}

	@Override
	public double getPhenotype(int index) {
		return (minX + (maxX - minX) * (Utils.bin2dec(this.gens[index])) / (Math.pow(2, this.getLength()[index]) - 1));
	}

	@Override
	public Chromosome getChild() {
		return new Function5(minX, maxX, precision, this.n);
	}
	
	@Override
	public Chromosome clone() {
		Function5 aux = new Function5(this.minX, this.maxX, this.precision, this.n);
		aux.setGens(this.gens);
		aux.setAptitude(this.aptitude);
		aux.setScore(this.score);
		aux.setAggregateSocore(this.aggregateSocore);
		aux.setLength(this.length);
		
		return aux;
	}
}
