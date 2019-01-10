package fluoregistration.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fluoregistration.domain.Client;


@Repository
public interface ClientRepository extends CrudRepository<Client, String>{
	
	Client findByClientName(String clientName);
}