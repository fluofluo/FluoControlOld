package fluoregistration.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import fluoregistration.domain.User;
import fluoregistration.repository.UserRepository;
import fluoregistration.service.UserSearchService;

@Controller
public class UserController {
	
	@Autowired
	UserSearchService userSearchService;
	
	@Autowired
	UserRepository userRepository;
	
		

	@RequestMapping(value = "/user_profile/{userId}", method=RequestMethod.GET)
	public String showUser(@PathVariable String userId, Model model) {
		User user = userSearchService.findUserById(userId);
		model.addAttribute("user", user);
		return "user_profile";
	}


	@RequestMapping(value = "/user_profile/{userId}", method=RequestMethod.POST)
	public String editUser(@PathVariable String userId, @ModelAttribute("editUser") User user) {
		
		userRepository.save(user);
		return "user_profile";

	}
	
}
