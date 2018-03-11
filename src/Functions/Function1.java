package Functions;

import Models.Chromosome;
import Models.Gen;
import Models.Utils;

public class Function1 extends Chromosome {
	
	private double minX;
	private double maxX;
	private double precision;

	public Function1(double minX, double maxX, double precision) {
		this.minX = minX;
		this.maxX = maxX;
		this.precision = precision;
	}
	
	@Override
	public void init() {
		int length = this.calculateLength(minX, maxX, precision);
		Gen gens [] = new Gen[length];
		
		for(int i = 0; i < length; i ++) {
			Gen gen = new Gen(1);
			gen.init();
			gens[i] = gen;
		}
		
		this.setGens(gens);	
	}

	@Override
	public double test() {
		double x = getPhenotype(0);
		
		return 20 + Math.E - (20 * Math.E * (Math.pow(Math.E, -0.2 * Math.abs(x)))) - Math.pow(Math.E, Math.cos(2 * Math.PI * x));		
	}

	@Override
	public double getPhenotype(int index) {
		return (minX + (maxX - minX) * (Utils.bin2dec(this.getAlleles(index))) / (Math.pow(2, this.getLength()) - 1));
	}

	@Override
	public Chromosome getChild() {
		return new Function1(minX, maxX, precision);
	}
}
