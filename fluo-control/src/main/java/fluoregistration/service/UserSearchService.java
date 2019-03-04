package fluoregistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fluoregistration.domain.User;
import fluoregistration.repository.UserRepository;

@Service
public class UserSearchService {
	
	@Autowired
	UserRepository userRepository;

	public User findUserById (String id) {
		return userRepository.findUserById(id);
	}
	
}
