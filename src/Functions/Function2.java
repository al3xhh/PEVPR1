package Functions;

import Models.Chromosome;
import Models.Gen;
import Models.Utils;

public class Function2 extends Chromosome {

	private double minX;
	private double maxX;
	private double precision;

	public Function2(double minX, double maxX, double precision) {
		this.minX = minX;
		this.maxX = maxX;
		this.precision = precision;
	}
	
	@Override
	public void init() {
		int length = this.calculateLength(minX, maxX, precision);
		Gen gens [] = new Gen[length];
		
		for(int i = 0; i < length; i ++) {
			Gen gen = new Gen(2);
			gen.init();
			gens[i] = gen;
		}
		
		this.setGens(gens);	
	}

	@Override
	public double test() {
		double x1 = getPhenotype(0);
		double x2 = getPhenotype(1);
		
		return -((x2 + 47) * Math.sin(Math.sqrt(Math.abs(x2 + (x1 / 2) + 47)))) - (x1 * Math.sin(Math.sqrt(Math.abs(x1 - (x2 + 47)))));
	}

	@Override
	public double getPhenotype(int index) {
		return (minX + (maxX - minX) * (Utils.bin2dec(this.getAlleles(index))) / (Math.pow(2, this.getLength()) - 1));
	}

	@Override
	public Chromosome getChild() {
		return new Function2(minX, maxX, precision);
	}
}
