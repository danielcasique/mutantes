package com.meli.mutantes.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class HumanDna {

	private static final String REGEX_MUTANT = "([A|C|G|T])\\1{3}";

	/**
	 * To check if the DNA has the enough characters and correct size
	 * 
	 * @param dna arrays of string
	 * @return true if the DNA is valid
	 */
	public boolean isValid(String[] dna) {
		long invalid = Arrays.asList(dna).parallelStream().filter(x -> x.length() != dna.length).count();
		return invalid == 0 && dna.length >= 4;
	}

	/**
	 * convierte una lista dna en una matrix dna 
	 * @param dna matrix de DNA
	 * @return matrix dna
	 */
	public char[][] getMatrix(String[] dna) {
		char[][] dnaMx = new char[dna.length][dna.length];
		for (int i = 0; i < dna.length; i++) {
			dnaMx[i] = dna[i].toCharArray();
		}
		return dnaMx;
	}

	/**
	 * obtiene lista de secuencias verticales
	 * @param dna matrix de DNA
	 * @return lista de posibles secuencias oblicuas viables
	 */
	public List<String> getVerticales(char[][] dna) {
		List<String> result = new LinkedList<>();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < dna.length; i++) {
			sb = sb.delete(0, sb.length());
			for (int j = 0; j < dna.length; j++) {
				sb.append(dna[j][i]);
			}
			result.add(sb.toString());
		}
		return result;
	}

	/**
	 * Obtiene una lista con las oblicuas posibles, es decir, que la secuencia tenga al menos 4 caracteres
	 * @param dna matrix de DNA
	 * @return lista de posibles secuencias oblicuas viables
	 */
	public List<String> getOblicuas(char[][] dna) {
		List<String> result = new LinkedList<>();
		int i = 0;
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		boolean dCentral = true;
		for (int j = 0; j <= dna.length - 4; j++) {
			sb1 = sb1.delete(0, sb1.length());
			sb2 = sb2.delete(0, sb2.length());
			for (int k = 0; i + k < dna.length && j + k < dna.length; k++) {
				if (i + k == j + k) {
					// diagonal central
					sb1.append(dna[i + k][j + k]);
				} else {
					// diagonales gemelas
					sb1.append(dna[i + k][j + k]);
					sb2.append(dna[j + k][i + k]);
				}
			}
			if (dCentral) {
				result.add(sb1.toString());
				dCentral = false;
			} else {
				result.add(sb1.toString());
				result.add(sb2.toString());
			}
		}
		return result;
	}
	
	/**
	 * verifica si cada secuencia dna tiene 4 letras iguales, haciendo el chequeo con una expresion regular
	 * @param dna lista de secuencias de DNA
	 * @return número de mutantes
	 */
	private long checkDna(List<String> dna) {
		Pattern checkDna = Pattern.compile(REGEX_MUTANT);
		return dna.parallelStream().mapToLong(x -> checkDna.matcher(x).results().count()).sum();
	}

	/**
	 * verifica mutantes en las secuencias verticales
	 * @param dna matrix de DNA
	 * @param mutants cuantas secuencias de mutante se ha encontrado hasta el momento de llamar a este metodo
	 * @return número de mutantes
	 */
	private long checkVertical(char[][] dna, long mutants) {
		if (mutants <= 1) {
			List<String> vertical = getVerticales(dna);
			mutants += checkDna(vertical);
		}
		return mutants;
	}
	
	/**
	 * Verifica si hay letras igual en las oblicuas: primero izquierda-derecha y luego derecha-izquierda
	 * @param originalDna DNA original
	 * @param dna Matriz de la secuencia DNA
	 * @param mutants cuantas secuencias de mutante se ha encontrado hasta el momento de llamar a este metodo
	 * @return número de mutantes
	 */
	private long checkOblicuas(String[] originalDna, char[][] dna, long mutants) {
		if (mutants <= 1) {
			//oblicuas de izquierda a derecha
			List<String> oblicuas1 = getOblicuas(dna);
			mutants += checkDna(oblicuas1);
			if(mutants <= 1) {
				//oblicuas de derecha a izquierda
				Collections.reverse(Arrays.asList(dna));
				for(int i = 0; i < originalDna.length; i++) {
					originalDna[i] = StringUtils.reverse(originalDna[i].toString());
				}
				char[][] dnaOblicua = getMatrix(originalDna);
				List<String> oblicuas2 = getOblicuas(dnaOblicua);
				mutants += checkDna(oblicuas2);
			}
		}
		return mutants;
	}
	
	/**
	 * Verifica si una secuencia pertence a un mutante buscando si hay 4 letras repetidas.
	 * Se trata de detener la busqueda cuando se consiga al menos 2 secuencias de 4 letras iguales. Ya que
	 * Sabrás si un humano es mutante, si encuentras más de una secuencia de cuatro letras 
	 * iguales​, de forma oblicua, horizontal o vertical."
	 * @param dna Secuencia de DNA
	 * @return Verdadero si dna corresponde a un mutante
	 */
	public boolean isMutant(String[] dna) {

		long result = checkDna(Arrays.asList(dna));
		
		if(result <= 1) {
			char [][]dnaMatrix = getMatrix(dna);
			
			result = checkVertical(dnaMatrix, result);
			
			result = checkOblicuas(dna, dnaMatrix, result);
		}
		System.out.println("nro de secuencias encontradas: " + result + " es Mutante " + (result>1));
		return result>1;
	}

}
