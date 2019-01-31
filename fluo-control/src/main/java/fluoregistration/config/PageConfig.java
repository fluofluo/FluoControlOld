package fluoregistration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PageConfig implements WebMvcConfigurer{

	//BCryptPasswordEncoder bean
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	    return bCryptPasswordEncoder;
	}
	
	//Override method to register the controllers and the views
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/home").setViewName("home");
	    registry.addViewController("/").setViewName("home");
	    
	    registry.addViewController("/dashboard").setViewName("dashboard");
	    
	    
	    
	    registry.addViewController("/login").setViewName("login");
	    
	    registry.addViewController("/loggedout").setViewName("loggedout");
	    
	    registry.addViewController("/clients").setViewName("clients");
	    registry.addViewController("/clients_show").setViewName("clients_show");
	    registry.addViewController("/edit_client").setViewName("edit_client");
	}
}
