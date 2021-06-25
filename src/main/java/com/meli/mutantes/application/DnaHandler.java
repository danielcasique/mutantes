package com.meli.mutantes.application;

import com.meli.mutantes.domain.DnaSecuence;
import com.meli.mutantes.domain.Stats;

public interface DnaHandler {
	
	boolean save(DnaSecuence dna) throws Exception;
	
	Stats getStats() throws Exception;

}
