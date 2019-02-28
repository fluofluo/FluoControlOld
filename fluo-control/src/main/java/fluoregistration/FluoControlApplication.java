package fluoregistration;




import fluoregistration.repository.RoleRepository;
import fluoregistration.domain.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;





@SpringBootApplication
@ComponentScan("fluoregistration")
@EnableMongoRepositories("fluoregistration")
@Configuration
public class FluoControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(FluoControlApplication.class, args);
	}

		
	
	@Bean
	CommandLineRunner init(RoleRepository roleRepository) {

	    return args -> {
	    	
	    	Role hyperadminRole = roleRepository.findByRole("HYPERADMIN");
	        if (hyperadminRole == null) {
	            Role newHyperadminRole = new Role();
	            newHyperadminRole.setRole("HYPERADMIN");
	            roleRepository.save(newHyperadminRole);
	        }

	        Role adminRole = roleRepository.findByRole("ADMIN");
	        if (adminRole == null) {
	            Role newAdminRole = new Role();
	            newAdminRole.setRole("ADMIN");
	            roleRepository.save(newAdminRole);
	        }

	        Role userRole = roleRepository.findByRole("USER");
	        if (userRole == null) {
	            Role newUserRole = new Role();
	            newUserRole.setRole("USER");
	            roleRepository.save(newUserRole);
	        }
	    };

	}


}

