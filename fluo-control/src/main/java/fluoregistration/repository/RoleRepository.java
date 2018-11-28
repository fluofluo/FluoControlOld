package fluoregistration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import fluoregistration.domain.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String role);

}


//getting Role data by role name