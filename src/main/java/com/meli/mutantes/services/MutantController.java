package com.meli.mutantes.services;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meli.mutantes.application.DnaHandler;
import com.meli.mutantes.application.LocalHandler;
import com.meli.mutantes.domain.DnaDto;
import com.meli.mutantes.domain.DnaSecuence;
import com.meli.mutantes.domain.HumanDna;
import com.meli.mutantes.domain.Stats;

import org.apache.commons.lang3.StringUtils;

@RestController
public class MutantController {
	
	DnaHandler handler;
	
	@Autowired	
	public MutantController(LocalHandler handler) {
		super();
		this.handler = handler;
	}



	@PostMapping(value = "/mutant", consumes = "application/json")
	public void checkDna(@RequestBody DnaDto input, HttpServletResponse response) throws Exception {
		HumanDna checker = new HumanDna();
		DnaSecuence dna = new DnaSecuence();
		dna.setSecuence(StringUtils.join(input.getDna(), ","));
		dna.setMutant(checker.isMutant(input.getDna()));
		handler.save(dna);
		if(dna.isMutant()) {
			response.setStatus(HttpServletResponse.SC_OK);
		}else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}
	
	@GetMapping(value = "/stats", produces = "application/json")
	public Stats getStats() throws Exception {
		return handler.getStats();
	}

}
