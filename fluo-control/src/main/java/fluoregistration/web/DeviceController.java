package fluoregistration.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import fluoregistration.domain.Device;
import fluoregistration.domain.User;
import fluoregistration.repository.DeviceRepository;
import fluoregistration.service.CustomUserDetailsService;
import fluoregistration.service.DeviceSearchService;




@Controller
public class DeviceController {
	
	@Autowired
	private CustomUserDetailsService userService;
	
	@Autowired
	DeviceRepository deviceRepository;
	
	@Autowired
	DeviceSearchService deviceSearchService;
	
	

	@RequestMapping("/devices")
	public ModelAndView devices(Model model) {
		ModelAndView modelAndView = new ModelAndView();
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    User user = userService.findUserByEmail(auth.getName());
	    modelAndView.addObject("currentUser", user);
	    modelAndView.addObject("fullName", "Hiperadministrator " + user.getFirstName() + " " + user.getLastName() );
	    modelAndView.setViewName("devices");
		model.addAttribute("deviceList", deviceRepository.findAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "/addDevice", method = RequestMethod.POST)
	public String addDevice(@ModelAttribute("addDevice") Device device) {
		deviceRepository.save(device);
		return "redirect:devices";
	}
	

	@RequestMapping(value = "/searchDevice")
	public String searchDevice(Model model, @RequestParam String searchDevice) {
		model.addAttribute("deviceList", deviceSearchService.searchDevice(searchDevice));
		model.addAttribute("search", searchDevice);
		return "devices";
	}
	
	@RequestMapping(value = "/devices_show/{deviceId}", method=RequestMethod.GET)
	public String showDevice(@PathVariable String deviceId, Model model) {
		Device device = deviceSearchService.findDeviceById(deviceId);
		model.addAttribute("device", device);
		return "devices_show";
	}
	
	@RequestMapping(value = "/devices_show/{deviceId}", method=RequestMethod.POST)
	public String editDevice(@PathVariable String deviceId, @ModelAttribute("editDevice") Device device) {
		device.setId(deviceId);
		deviceRepository.save(device);
		return "redirect:/devices";

	}
	
	@RequestMapping(value = "/delete_device/{deviceId}")
	public String deleteDevice(@PathVariable String deviceId, @ModelAttribute("deleteDevice") Device device) {
		device.setId(deviceId);
		deviceRepository.delete(device);
		return "redirect:/devices";

	}
}
