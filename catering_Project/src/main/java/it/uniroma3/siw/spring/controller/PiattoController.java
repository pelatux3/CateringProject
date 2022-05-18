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

import it.uniroma3.siw.spring.model.Piatto;
import it.uniroma3.siw.spring.service.PiattoService;

@Controller
public class PiattoController {
	
	@Autowired
	private PiattoService piattoService;
	
    @Autowired
    private PiattoValidator piattoValidator;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	 @RequestMapping(value="/admin/addPiatto", method = RequestMethod.GET)
	    public String addPiatto(Model model) {
	    	logger.debug("addPiatto");
	    	model.addAttribute("piatto", new Piatto());
	        return "piattoForm.html";
	    }

	    @RequestMapping(value = "/piatto/{id}", method = RequestMethod.GET)
	    public String getPiatto(@PathVariable("id") Long id, Model model) {
	    	Piatto p=this.piattoService.PiattoPerId(id);
	    	model.addAttribute("piatto", p);
	    //	model.addAttribute("opereArtista",a.getOpere());
	    	return "piatto.html";
	    }

	    @RequestMapping(value = "/piattp", method = RequestMethod.GET)
	    public String getPiatti(Model model) {
	    		model.addAttribute("piatti", this.piattoService.tutti());
	    		return "piatti.html";
	    }
	    
	    @RequestMapping(value = "/admin/piatto", method = RequestMethod.POST)
	    public String newPiatto(@ModelAttribute("piatto") Piatto piatto, 
	    									Model model, BindingResult bindingResult) {
	    	this.piattoValidator.validate(piatto, bindingResult);
	        if (!bindingResult.hasErrors()) {
	        	this.piattoService.inserisci(piatto);
	            model.addAttribute("piatti", this.piattoService.tutti());
	            return "piatti.html";
	        }
	        return "piattoForm.html";
	    }
	
}
