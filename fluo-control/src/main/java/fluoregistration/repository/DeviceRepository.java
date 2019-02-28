package fluoregistration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import fluoregistration.domain.Device;

public interface DeviceRepository extends MongoRepository<Device, String> {

	Device findDeviceById(String id);
	
}
