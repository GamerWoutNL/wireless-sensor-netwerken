package nl.iwsn.backend.database.repositories;

import nl.iwsn.backend.model.smartmeter.SmartMeterData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartMeterRepository extends MongoRepository<SmartMeterData, String> {

}
