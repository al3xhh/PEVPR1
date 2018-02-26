package Functions;

import Models.Chromosome;

public class Function1 extends Chromosome {
	
	private double minX;
	private double maxX;
	private double precision;

	public Function1(boolean[] gens, double phenotype, 
					double aptitude, double score, 
					double aggregateSocore,
					double minX, double maxX,
					double precision) {
		
		super(gens, aptitude, score, aggregateSocore);
		this.minX = minX;
		this.maxX = maxX;
		this.precision = precision;
	}
	
	@Override
	public void initChromosome() {
		int length = this.getLength(minX, maxX, precision);
		boolean gens [] = new boolean[length];
		
		for(int i = 0; i < length; i ++)
			gens[i] = Math.random() < 0.5 ? false : true;
		
		this.setGens(gens);		
	}

	@Override
	public double test() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPhenotype() {
		// TODO Auto-generated method stub
		return 0;
	}
}
