package com.meli.mutantes.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stats {
	
	@JsonProperty("count_mutant_dna")
	private int countMutantDna;
	@JsonProperty("count_human_dna")
	private int countHumanDna;
	private double ratio;

	public Stats() {
		super();
	}
	
	public Stats(int countMutantDna, int countHumanDna, double ratio) {
		super();
		this.countMutantDna = countMutantDna;
		this.countHumanDna = countHumanDna;
		this.ratio = ratio;
	}
	
	public int getCountMutantDna() {
		return countMutantDna;
	}
	public void setCountMutantDna(int countMutantDna) {
		this.countMutantDna = countMutantDna;
	}
	public int getCountHumanDna() {
		return countHumanDna;
	}
	public void setCountHumanDna(int countHumanDna) {
		this.countHumanDna = countHumanDna;
	}
	public double getRatio() {
		return ratio;
	}
	public void setRatio(double ratio) {
		this.ratio = ratio;
	}
	
	
	
}
