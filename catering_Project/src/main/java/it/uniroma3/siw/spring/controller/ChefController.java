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

import it.uniroma3.siw.spring.model.Chef;
import it.uniroma3.siw.spring.service.ChefService;

@Controller
public class ChefController {
	
	@Autowired
	private ChefService chefService;
	
    @Autowired
    private ChefValidator chefValidator;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	 @RequestMapping(value="/admin/addChef", method = RequestMethod.GET)
	    public String addChef(Model model) {
	    	logger.debug("addChef");
	    	model.addAttribute("chef", new Chef());
	        return "artistaForm.html";
	    }

	    @RequestMapping(value = "/chef/{id}", method = RequestMethod.GET)
	    public String getChef(@PathVariable("id") Long id, Model model) {
	    	Chef c=this.chefService.ArtistaPerId(id);
	    	model.addAttribute("chef", c);
	    //	model.addAttribute("opereArtista",c.getOpere());
	    	return "chef.html";
	    }

	    @RequestMapping(value = "/chef", method = RequestMethod.GET)
	    public String getchefs(Model model) {
	    		model.addAttribute("chefs", this.chefService.tutti());
	    		return "chefs.html";
	    }
	    
	    @RequestMapping(value = "/admin/chef", method = RequestMethod.POST)
	    public String newChef(@ModelAttribute("chef") Chef chef, 
	    									Model model, BindingResult bindingResult) {
	    	this.chefValidator.validate(chef, bindingResult);
	        if (!bindingResult.hasErrors()) {
	        	this.chefService.inserisci(chef);
	            model.addAttribute("chefs", this.chefService.tutti());
	            return "chefs.html";
	        }
	        return "chefForm.html";
	    }
	
}
