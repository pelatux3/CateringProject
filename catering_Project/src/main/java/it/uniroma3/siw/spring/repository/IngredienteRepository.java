package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Ingrediente;



public interface IngredienteRepository extends CrudRepository<Ingrediente, Long>{

	public List<Ingrediente> findByNome(String nome);
	public List<Ingrediente> findByOrigine(String origine);
	public List<Ingrediente> findByNomeAndOrigine(String name, String origine);
}
