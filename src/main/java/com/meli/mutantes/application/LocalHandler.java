package com.meli.mutantes.application;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.mutantes.domain.DnaSecuence;
import com.meli.mutantes.domain.Stats;

@Service
public class LocalHandler implements DnaHandler {

	List<DnaSecuence> db;
	Stats stats;

	@Autowired
	public LocalHandler() {
		super();
		db = new LinkedList<DnaSecuence>();
		stats = new Stats();
	}

	@Override
	public boolean save(DnaSecuence dna) throws Exception {
		// TODO Auto-generated method stub
		synchronized (this) {
			db.add(dna);
			if (dna.isMutant()) {
				stats.setCountMutantDna(stats.getCountMutantDna() + 1);
			} else {
				stats.setCountHumanDna(stats.getCountHumanDna() + 1);
			}
			stats.setRatio(stats.getCountMutantDna() / (float) db.size());
		}
		return true;
	}

	@Override
	public Stats getStats() throws Exception {
		Stats result = null;
		synchronized (this) {
			result = new Stats(stats.getCountHumanDna(), stats.getCountMutantDna(), stats.getRatio());
		}
		return result;
	}

}
