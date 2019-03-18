package fluoregistration.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import fluoregistration.domain.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	
	public List<Role> findAll();

    Role findByRole(String role);
    

}


//getting Role data by role name