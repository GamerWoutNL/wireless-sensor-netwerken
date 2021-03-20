package nl.iwsn.backend.repositories;

import nl.iwsn.backend.model.smartmeter.SmartMeterData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SmartMeterRepository extends MongoRepository<SmartMeterData, String> {

}
