package com.meli.mutantes.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.meli.mutantes.domain.HumanDna;


/**
 * Unit test para probar la clase HumanDNA
 * "Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras 
 * iguales​, de forma oblicua, horizontal o vertical."
 * @author daniel.casique
 *
 */
public class HumanDnaTest {
	
	/** Probar si una matrix es NxN
	 * ATGCG
	 * AATGG
	 * ACATG
	 * AGCTG
	 * CCGCA
	 */
	@Test
	public void validDna() {
		HumanDna ind = new HumanDna();
		String []dna = {"ATGCG","AATGG","ACATG","AGCTG","CCGCA"};
		Assertions.assertEquals(true, ind.isValid(dna));
	}
	
	/** Probar que la matrix no contiene el tamaño minimo necesario
	 * ATG
	 * AAT
	 * ACA
	 */
	@Test
	public void invalidDnaBySize() {
		HumanDna ind = new HumanDna();
		String []dna = {"ATG","AAT","ACA"};
		Assertions.assertEquals(false, ind.isValid(dna));
	}
	
	/** probar que una matrix tiene una linea invalida
	 * ATGCG
	 * AATGG
	 * ACAT
	 * AGCTG
	 * CCGCA
	 */
	@Test
	public void invalidDnaByWrongLine() {
		HumanDna ind = new HumanDna();
		String []dna = {"ATGCG","AATGG","ACAT","AGCTG","CCGCA"};
		Assertions.assertEquals(false, ind.isValid(dna));
	}
	
	/**
	 * Esta cadena de ADN NO tiene una secuencia con la misma letra 
	 * Resultado esperado falso
	 * ATGCG
	 * CGTGG
	 * TTATG
	 * AGAAC
	 * CCGCT
	 */
	@Test
	public void testNoMatch() {
		System.out.println("testNoMatch...");
		HumanDna ind = new HumanDna();
		String []dna = {"ATGCG","CGTGG","TTATG","AGAAC","CCGCT"};
		Assertions.assertEquals(false, ind.isMutant(dna));
	}
	
	/**
	 * Esta cadena de ADN tiene una sola secuencia con la misma letra la 5 fila
	 * Resultado esperado falso
	 * ATGCG
	 * CAGTG
	 * TTATG
	 * AGATC
	 * CCCCC
	 */
	@Test
	public void testHorizontalOneMatch() {
		System.out.println("testHorizontalOneMatch...");
		HumanDna ind = new HumanDna();
		String []dna = {"ATGCG","CAGTG","TTATG","AGATC","CCCCC"};
		Assertions.assertEquals(false, ind.isMutant(dna));
	}
	
	/**
	 * Esta cadena de ADN tiene dos secuencias con la misma letram filas 2 y 5
	 * Resultado esperado true
	 * ATGCG
	 * CGGGG
	 * TTATG
	 * AGAAC
	 * CCCCT
	 */
	@Test
	public void testHorizontalTwoMatch() {
		System.out.println("testHorizontalTwoMatch...");
		HumanDna ind = new HumanDna();
		String []dna = {"ATGCG","CGGGG","TTATG","AGAAC","CCCCT"};
		Assertions.assertEquals(true, ind.isMutant(dna));
	}

	
	/**
	 * Esta cadena tiene una secuencia de 4 letras de forma vertical
	 * Resultado esperado false
	 * ATGCG
	 * AATGG
	 * ACATG
	 * AGCTC
	 * CCGCA
	 */
	@Test
	public void testVerticalOneMatch() {
		System.out.println("testVerticalOneMatch...");
		HumanDna ind = new HumanDna();
		String []dna = {"ATGCG","AATGG","ACATG","AGCTC","CCGCA"};
		Assertions.assertEquals(false, ind.isMutant(dna));
	}
	
	/**
	 * Esta cadena tiene dos secuencia de 4 letras igual de forma vertical
	 * Resultado esperado false
	 * ATGCG
	 * AATGG
	 * ACATG
	 * AGCTG
	 * CCGCA
	 */
	@Test
	public void testVerticalTwoMatch() {
		System.out.println("testVerticalTwoMatch...");
		HumanDna ind = new HumanDna();
		String []dna = {"ATGCG","AATGG","ACATG","AGCTG","CCGCA"};
		Assertions.assertEquals(true, ind.isMutant(dna));
	}
	
	/**
	 * Esta cadena de ADN tiene una secuencia de 4 letras, oblicua principal de derecha a izquierda
	 * Resultado esperado falso
	 * ATGCG
	 * CGTGG
	 * TTGTG
	 * AGAAC
	 * CCGCT
	 */
	@Test
	public void testOblicuaOneMatch() {
		System.out.println("testOblicuaOneMatch...");
		HumanDna ind = new HumanDna();
		String []dna = {"ATGCG","CGTGG","TTGTG","AGAAC","CCGCT"};
		Assertions.assertEquals(false, ind.isMutant(dna));
	}
	
	/**
	 * Esta cadena de ADN tiene 2 secuencia de 4 letras, oblicuas de derecha a izquierda
	 * Resultado esperado falso
	 * ATGCG
	 * CGTGG
	 * TTGGG
	 * AGGAC
	 * CGGCT
	 */
	@Test
	public void testOblicuaTwoMatch() {
		System.out.println("testOblicuaTwoMatch...");
		HumanDna ind = new HumanDna();
		String []dna = {"ATGCG","CGTGG","TTGGG","AGGAC","CGGCT"};
		Assertions.assertEquals(true, ind.isMutant(dna));
	}
	

}
