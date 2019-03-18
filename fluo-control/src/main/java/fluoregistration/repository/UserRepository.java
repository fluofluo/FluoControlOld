package fluoregistration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import fluoregistration.domain.User;

public interface UserRepository extends MongoRepository<User, String> {

	User findByEmail(String email);
	
	User findUserById(String id);
	
	
}
