package Models;

public class Gen {
	private boolean []alleles;
	private int allelesNum;
	
	public Gen(int allelesNum) {
		this.alleles = new boolean[allelesNum];
		this.allelesNum = allelesNum;
	}

	public boolean[] getAlleles() {
		return alleles;
	}

	public int getAllelesNum() {
		return allelesNum;
	}
	
	public void setAllele(boolean allele, int pos) {
		this.alleles[pos] = allele;
	}
	
	public void mutate() {
		for(int i = 0; i < allelesNum; i++) {
			alleles[i] = !alleles[i];
		}
	}
	
	public void init() {
		for(int i = 0; i < allelesNum; i++) {
			alleles[i] = Math.random() < 0.5 ? false : true;
		}
	}
	
	public boolean getAllele(int index) {
		return this.alleles[index];
	}
}
