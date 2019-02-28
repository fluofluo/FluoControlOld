package fluoregistration.service;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import fluoregistration.domain.Device;
import fluoregistration.repository.DeviceRepository;

@Service
public class DeviceSearchService {

	@Autowired
	MongoTemplate mongoTemplate;	

	@Autowired
	DeviceRepository deviceRepository;



	
	  //method for client by clientCode public Client findClientByClientCode(String
	 // clientCode) { return clientRepository.findByClientCode(clientCode); }
	  
	public Device findDeviceById (String id) { 
		  return deviceRepository.findDeviceById(id); 
	}
	 

	public Collection<Device> searchDevice(String text) {
		Criteria criteria = new Criteria();
		criteria.orOperator(Criteria.where("deviceName").regex(text, "i"),Criteria.where("deviceType").regex(text, "i"),
				Criteria.where("deviceMacAddress").regex(text, "i"));
		Query query = new Query(criteria); 

		return mongoTemplate.find(query,Device.class);

	}

}
