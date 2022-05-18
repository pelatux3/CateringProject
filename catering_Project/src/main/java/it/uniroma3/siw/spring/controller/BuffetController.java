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

import it.uniroma3.siw.spring.model.Buffet;
import it.uniroma3.siw.spring.service.BuffetService;

@Controller
public class BuffetController {
	
	@Autowired
	private BuffetService buffetService;
	
    @Autowired
    private BuffetValidator buffetValidator;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	 @RequestMapping(value="/admin/addBuffet", method = RequestMethod.GET)
	    public String addBuffet(Model model) {
	    	logger.debug("addBuffet");
	    	model.addAttribute("buffet", new Buffet());
	        return "buffetForm.html";
	    }

	    @RequestMapping(value = "/buffet/{id}", method = RequestMethod.GET)
	    public String getArtista(@PathVariable("id") Long id, Model model) {
	    	Buffet b=this.buffetService.BuffetPerId(id);
	    	model.addAttribute("buffet", b);
	     // model.addAttribute("opereArtista",a.getOpere());
	    	return "buffet.html";
	    }

	    @RequestMapping(value = "/buffet", method = RequestMethod.GET)
	    public String getBuffets(Model model) {
	    		model.addAttribute("Buffets", this.buffetService.tutti());
	    		return "buffets.html";
	    }
	    
	    @RequestMapping(value = "/admin/buffet", method = RequestMethod.POST)
	    public String newBuffet(@ModelAttribute("buffet") Buffet buffet, 
	    									Model model, BindingResult bindingResult) {
	    	this.buffetValidator.validate(buffet, bindingResult);
	        if (!bindingResult.hasErrors()) {
	        	this.buffetService.inserisci(buffet);
	            model.addAttribute("buffets", this.buffetService.tutti());
	            return "buffets.html";
	        }
	        return "buffetForm.html";
	    }
	
}
