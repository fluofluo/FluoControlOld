package fluoregistration.repository;



import org.springframework.data.mongodb.repository.MongoRepository;


import fluoregistration.domain.Client;


public interface ClientRepository extends MongoRepository<Client, String>{

	Client findByClientCode(String clientCode);

	Client findClientById(String id);



}