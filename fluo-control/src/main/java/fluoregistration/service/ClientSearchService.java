package fluoregistration.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import fluoregistration.domain.Client;

@Service
public class ClientSearchService {

	@Autowired
    MongoTemplate mongoTemplate;

			public Collection<Client> searchClient(String text) {
			       Criteria criteria = new Criteria();
			           criteria.orOperator(Criteria.where("clientName").regex(text, "i"),Criteria.where("clientAddress").regex(text, "i"),Criteria.where("clientCode").regex(text, "i"));
			           Query query = new Query(criteria); 
			        
			return mongoTemplate.find(query,Client.class);
                       
}
	
}
