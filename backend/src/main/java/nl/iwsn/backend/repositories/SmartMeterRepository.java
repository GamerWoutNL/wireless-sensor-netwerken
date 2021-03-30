package nl.iwsn.backend.repositories;

import nl.iwsn.backend.model.smartmeter.SmartMeterData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface SmartMeterRepository extends MongoRepository<SmartMeterData, String> {

    @Query("{'measurement.timestamp': {$gte: ?0, $lte:?1 }}")
    List<SmartMeterData> findPacketArrivedBetween(LocalDateTime startDate, LocalDateTime endDate);

}
