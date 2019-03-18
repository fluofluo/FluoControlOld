package fluoregistration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fluoregistration.domain.Role;
import fluoregistration.domain.User;
import fluoregistration.repository.RoleRepository;
import fluoregistration.repository.UserRepository;

@Service
public class UserSearchService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;

	public User findUserById (String id) {
		return userRepository.findUserById(id);
	}
	
	public List<Role> getRoles(){
	    List<Role> roleList = roleRepository.findAll();
	    return roleList;
	}
	
}
