package fluoregistration.service;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import fluoregistration.domain.Client;
import fluoregistration.repository.ClientRepository;

@Service
public class ClientSearchService {

	@Autowired
	MongoTemplate mongoTemplate;	

	@Autowired
	ClientRepository clientRepository;



	//method for client by clientCode
	public Client findClientByClientCode(String clientCode) {
		return clientRepository.findByClientCode(clientCode);
	}

	public Client findClientById (String id) {
		return clientRepository.findClientById(id);
	}


	public Collection<Client> searchClient(String text) {
		Criteria criteria = new Criteria();
		criteria.orOperator(Criteria.where("clientName").regex(text, "i"),Criteria.where("clientAddressStreet").regex(text, "i"),
				Criteria.where("clientAddressCity").regex(text, "i"),Criteria.where("clientAddressPostalCode").regex(text, "i"),
				Criteria.where("clientCode").regex(text, "i"),Criteria.where("contactPersonName").regex(text, "i"),
				Criteria.where("contactPersonNumber").regex(text, "i"),Criteria.where("contactPersonEmail").regex(text, "i"));
		Query query = new Query(criteria); 

		return mongoTemplate.find(query,Client.class);

	}

}
