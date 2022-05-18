package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.repository.ChefRepository;

@Service
public class ChefService {
	
	@Autowired
	private ChefRepository chefRepository; 
	
	@Transactional
	public Chef inserisci(Chef chef) {
		return chefRepository.save(chef);
	}
	
	@Transactional
	public List<Chef> studentiPerNomeAndCognome(String nome, String cognome) {
		return chefRepository.findByNomeAndCognome(nome, cognome);
	}

	@Transactional
	public List<Chef> tutti() {
		return (List<Chef>) chefRepository.findAll();
	}

	@Transactional
	public Chef ArtistaPerId(Long id) {
		Optional<Chef> optional = chefRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Chef chef) {
		List<Chef> chefs = this.chefRepository.findByNomeAndCognome(chef.getNome(), chef.getCognome());
		if (chefs.size() > 0)
			return true;
		else 
			return false;
	}

}

