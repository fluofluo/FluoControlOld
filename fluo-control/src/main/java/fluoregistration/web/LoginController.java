package fluoregistration.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fluoregistration.domain.Client;
import fluoregistration.domain.User;
import fluoregistration.service.ClientSearchService;
import fluoregistration.service.CustomUserDetailsService;

@Controller
public class LoginController {

	@Autowired
	private CustomUserDetailsService userService;

	@Autowired
	private ClientSearchService clientCodeService;


	//model and view method for login page
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	//model and view method for sign up/register page
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signup() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();	    	
		modelAndView.addObject("user", user);

		modelAndView.setViewName("signup");
		return modelAndView;
	}






	//model and view method for saving the new user when form submitted form sign up/register page
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult, @ModelAttribute("client") Client client) {
		ModelAndView modelAndView = new ModelAndView();

		Client clientCodeExists = clientCodeService.findClientByClientCode(client.getClientCode());

		User userExists = userService.findUserByEmail(user.getEmail());

		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "Istnieje już użytkownik o podanym adresie e-mail.");
		}

		if (clientCodeExists == null) {
			bindingResult.rejectValue("email", "error.user", "Podany kod klienta nie istnieje.");
		}


		else if (userExists == null && clientCodeExists != null) {
			userService.saveUser(user);
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("login_after_signup");


		}
		return modelAndView;
	}


	//model and view method for hyperadmin dashboard page which is a secure page showing a data from the successful login
	@RequestMapping(value = "/hyperadmin_dashboard", method = RequestMethod.GET)
	public ModelAndView hyperadmin_dashboard() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("currentUser", user);
		modelAndView.addObject("fullName", "Hiperadministrator " + user.getFirstName() + " " + user.getLastName() );
		modelAndView.addObject("userId", user.getId());
		modelAndView.addObject("hyperadminMessage", "Content Available Only for Users with hyperadmindmin Role");
		modelAndView.setViewName("hyperadmin_dashboard");
		return modelAndView;
	}

	//model and view method for admin dashboard page which is a secure page showing a data from the successful login
	@RequestMapping(value = "/admin_dashboard", method = RequestMethod.GET)
	public ModelAndView admin_dashboard() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("currentUser", user);
		modelAndView.addObject("fullName", "Administrator " + user.getFirstName() + " " + user.getLastName() );
		modelAndView.addObject("adminMessage", "Content Available Only for Users with admin Role");
		modelAndView.setViewName("admin_dashboard");
		return modelAndView;
	}

	//model and view method for user dashboard page which is a secure page showing a data from the successful login
	@RequestMapping(value = "/user_dashboard", method = RequestMethod.GET)
	public ModelAndView user_dashboard() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("currentUser", user);
		modelAndView.addObject("fullName", "Użytkownik " + user.getFirstName() + " " + user.getLastName() );
		modelAndView.addObject("userMessage", "Content Available Only for Users with User Role");
		modelAndView.setViewName("user_dashboard");
		return modelAndView;
	}



	//model and view for initial page that load in the front of the browser
	@RequestMapping(value = {"/home"}, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}

	//model and view for initial page that load after log out
	@RequestMapping(value = {"/loggedout"}, method = RequestMethod.GET)
	public ModelAndView logedout() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("loggedout");
		return modelAndView;
	}
}
