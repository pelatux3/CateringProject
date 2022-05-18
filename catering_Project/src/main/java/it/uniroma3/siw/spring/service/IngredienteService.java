package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Ingrediente;
import it.uniroma3.siw.spring.repository.IngredienteRepository;

@Service
public class IngredienteService {
	
	@Autowired
	private IngredienteRepository ingredienteRepository; 
	
	@Transactional
	public Ingrediente inserisci(Ingrediente ingrediente) {
		return ingredienteRepository.save(ingrediente);
	}
	
	@Transactional
	public List<Ingrediente> studentiPerNomeePerOrigine(String nome, String origine) {
		return ingredienteRepository.findByNomeAndOrigine(nome, origine);
	}

	@Transactional
	public List<Ingrediente> tutti() {
		return (List<Ingrediente>) ingredienteRepository.findAll();
	}

	@Transactional
	public Ingrediente IngredientePerId(Long id) {
		Optional<Ingrediente> optional = ingredienteRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Ingrediente ingrediente) {
		List<Ingrediente> ingredienti = this.ingredienteRepository.findByNomeAndOrigine(ingrediente.getNome(), ingrediente.getOrigine());
		if (ingredienti.size() > 0)
			return true;
		else 
			return false;
	}

}

