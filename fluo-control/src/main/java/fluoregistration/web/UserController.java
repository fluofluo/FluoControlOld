package fluoregistration.web;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import fluoregistration.domain.Role;
import fluoregistration.domain.User;
import fluoregistration.repository.RoleRepository;
import fluoregistration.repository.UserRepository;
import fluoregistration.service.CustomUserDetailsService;
import fluoregistration.service.UserSearchService;

@Controller
public class UserController {
	
	@Autowired
	UserSearchService userSearchService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CustomUserDetailsService userService;
	
	@Autowired
	RoleRepository roleRepository;
		

	@RequestMapping(value = "/user_profile/{userId}", method=RequestMethod.GET)
	public String showUser(@PathVariable String userId, Model model) {
		User user = userSearchService.findUserById(userId);
		List<Role> roleList = userSearchService.getRoles();
		
		model.addAttribute("user", user);
		model.addAttribute("roleList", roleList);
		return "user_profile";
	}


	@RequestMapping(value = "/user_profile/{userId}", method=RequestMethod.POST)
	public ModelAndView editUser(@Valid User user, @PathVariable String userId) {
		ModelAndView modelAndView = new ModelAndView();
		user.setId(userId);
		
		
		userService.updateUser(user);
		
		modelAndView.setViewName("user_profile");
		return modelAndView;
	}
	
}
