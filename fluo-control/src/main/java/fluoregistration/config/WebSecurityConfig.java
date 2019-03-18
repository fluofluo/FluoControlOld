package fluoregistration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import fluoregistration.service.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	//Declare the variable for BCryptPasswordEncoder and CustomizeAuthenticationSuccessHandler class that will created later
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;


	//Create a bean for the Spring Security UserDetailsService that use the CustomUserDetailsService class
	@Bean
	public UserDetailsService mongoUserDetails() {
		return new CustomUserDetailsService();
	}



	//Add an override method for manage authentication mechanism that uses UserDetailsService and Bcrypt password encoder
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		UserDetailsService userDetailsService = mongoUserDetails();
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(bCryptPasswordEncoder);

	}


	//Add an override method for securing the HTTP requests. That configuration also adds custom login success handler using the custom class.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/default").permitAll()
		
		.antMatchers("/").permitAll()
		.antMatchers("/home").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/signup").permitAll()
		.antMatchers("/loggedout").permitAll()
	
		
		
		.antMatchers("/static/**").permitAll()
		.antMatchers("/css/**").permitAll()
		.antMatchers("/js/**").permitAll()
		.antMatchers("/images/**").permitAll()
		.antMatchers("/sounds/**").permitAll()

		
		.antMatchers("/hyperadmin_dashboard/*").hasAuthority("HYPERADMIN").anyRequest()
		

		.authenticated().and().csrf().disable().formLogin().successHandler(customizeAuthenticationSuccessHandler)
		.loginPage("/login").failureUrl("/login?error=true")
		.usernameParameter("email")
		.passwordParameter("password")
		.and().logout()
		.deleteCookies("JSESSIONID")
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/loggedout").and().exceptionHandling();

	}


	 
}
