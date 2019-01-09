package fluoregistration.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import fluoregistration.domain.Client;
import fluoregistration.repository.ClientRepository;

@Controller
public class ClientController {

	 @Autowired
	    ClientRepository clientRepository;
	 
	 @RequestMapping("/clients")
	    public String client(Model model) {
	        model.addAttribute("client", clientRepository.findAll());
	        return "client";
	    }
	 
	 @RequestMapping("/create")
	    public String create(Model model) {
	        return "create";
	    }
	 
	 @RequestMapping("/save")
	    public String save(@RequestParam String clientName, @RequestParam String clientAddress, @RequestParam String clientCode) {
	        Client client = new Client();
	        client.setClientName(clientName);
	        client.setClientAddress(clientAddress);
	        client.setClientCode(clientCode);
	        
	        return "redirect:/show/" + client.getId();
	 	}	        
	 
	  @RequestMapping("/show/{id}")
	    public String show(@PathVariable String id, Model model) {
	        model.addAttribute("client", clientRepository.findOne(id));
	        return "show";
	    }
	        
	  @RequestMapping("/delete")
	    public String delete(@RequestParam String id) {
	         Client client = clientRepository.findOne(id);
	         clientRepository.delete(client);

	        return "redirect:/client";
	    }
	  
	  @RequestMapping("/edit/{id}")
	    public String edit(@PathVariable String id, Model model) {
	        model.addAttribute("client", clientRepository.findOne(id));
	        return "edit";
	    }
	  
	  @RequestMapping("/update")
	    public String update(@RequestParam String id, @RequestParam String clientName, @RequestParam String clientAddress, @RequestParam String clientCode) {
		    Client client = clientRepository.findOne(id);
		    client.setClientName(clientName);
		    client.setClientAddress(clientAddress);
		    client.setClientCode(clientCode);
	       

	        return "redirect:/show/" + client.getId();
	    }

}
