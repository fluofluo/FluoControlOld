package fluoregistration.web;



import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fluoregistration.domain.Client;
import fluoregistration.domain.User;
import fluoregistration.repository.ClientRepository;
import fluoregistration.service.ClientSearchService;
import fluoregistration.service.CustomUserDetailsService;


@Controller
public class ClientController {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	ClientSearchService clientSearchService;

	@Autowired
	private CustomUserDetailsService userService;




	@RequestMapping(value = "/clients", method=RequestMethod.GET)
	public ModelAndView clients(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("currentUser", user);
		modelAndView.addObject("fullName", "Hiperadministrator " + user.getFirstName() + " " + user.getLastName() );
		modelAndView.setViewName("clients");
		model.addAttribute("clientList", clientRepository.findAll());

		return modelAndView;
	}


	@RequestMapping(value = "/addClient", method = RequestMethod.POST)
	public String addClient(@Valid Client client, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "redirect:clients";
		}else {

			clientRepository.save(client);
			return "redirect:clients";
		}
	}

	
	@RequestMapping(value = "/searchClient")
	public String searchClient(Model model, @RequestParam String searchClient) {
		model.addAttribute("clientList", clientSearchService.searchClient(searchClient));
		model.addAttribute("search", searchClient);
		return "clients";
	}

	
	@RequestMapping(value = "/clients_show/{clientId}", method=RequestMethod.GET)
	public String showClient(@PathVariable String clientId, Model model) {
		Client client = clientSearchService.findClientById(clientId);
		model.addAttribute("client", client);
		return "clients_show";
	}

	//EDIT
	@RequestMapping(value = "/clients_show/{clientId}", method=RequestMethod.POST)
	public String editClient(@PathVariable String clientId, @ModelAttribute("editClient") Client client) {
		client.setId(clientId);
		clientRepository.save(client);
		return "redirect:/clients";

	}

	@RequestMapping(value = "/delete_client/{clientId}")
	public String deleteClient(@PathVariable String clientId, @ModelAttribute("deleteClient") Client client) {
		client.setId(clientId);
		clientRepository.delete(client);
		return "redirect:/clients";

	}

	@RequestMapping(value = "/sound")
	public static synchronized void playSound(final String url) {
		new Thread(new Runnable() {
			// The wrapper thread is unnecessary, unless it blocks on the
			// Clip finishing; see comments.
			public void run() {
				try {
					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(
							Clip.class.getResourceAsStream("/static/sounds/alert2.wav"));
					clip.open(inputStream);
					clip.start(); 
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}


}
