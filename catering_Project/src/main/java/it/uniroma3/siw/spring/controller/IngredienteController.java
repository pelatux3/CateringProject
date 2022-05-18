package it.uniroma3.siw.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.model.Ingrediente;
import it.uniroma3.siw.spring.service.IngredienteService;

@Controller
public class IngredienteController {
	
	@Autowired
	private IngredienteService ingredienteService;
	
    @Autowired
    private IngredienteValidator ingredienteValidator;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	 @RequestMapping(value="/admin/addIngrediente", method = RequestMethod.GET)
	    public String addIngrediente(Model model) {
	    	logger.debug("addIngrediente");
	    	model.addAttribute("ingrediente", new Ingrediente());
	        return "ingredeinteForm.html";
	    }

	    @RequestMapping(value = "/ingrediente/{id}", method = RequestMethod.GET)
	    public String getIngrediente(@PathVariable("id") Long id, Model model) {
	    	Ingrediente i=this.ingredienteService.IngredientePerId(id);
	    	model.addAttribute("ingrediente", i);
	    //	model.addAttribute("opereArtista",a.getOpere());
	    	return "ingrediente.html";
	    }

	    @RequestMapping(value = "/ingrediente", method = RequestMethod.GET)
	    public String getIngredienti(Model model) {
	    		model.addAttribute("ingredienti", this.ingredienteService.tutti());
	    		return "ingredienti.html";
	    }
	    
	    @RequestMapping(value = "/admin/ingrediente", method = RequestMethod.POST)
	    public String newIngrediente(@ModelAttribute("ingrediente") Ingrediente ingrediente, 
	    									Model model, BindingResult bindingResult) {
	    	this.ingredienteValidator.validate(ingrediente, bindingResult);
	        if (!bindingResult.hasErrors()) {
	        	this.ingredienteService.inserisci(ingrediente);
	            model.addAttribute("ingredienti", this.ingredienteService.tutti());
	            return "ingredienti.html";
	        }
	        return "ingredienteForm.html";
	    }
	
}
