package fluoregistration.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import fluoregistration.domain.Client;
import fluoregistration.repository.ClientRepository;

@Controller
public class ClientController {

	@Autowired
    ClientRepository clientRepository;
	 

	@RequestMapping("/clients")
    public String clients(Model model) {
        model.addAttribute("clientList", clientRepository.findAll());
        return "clients";
    }
	
	@RequestMapping(value = "/addClient", method = RequestMethod.POST)
    public String addClient(@ModelAttribute Client client) {
        clientRepository.save(client);
        return "redirect:clients";
    }
	
	//@RequestMapping(value = "/searchClient")
    //public String searchClient(@RequestParam String searchClient) {
		//model.addAttribute("clientList", clientRepository.findAll());
        //return "clients";
    //}
}
