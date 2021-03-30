package nl.iwsn.backend.repositories;

import nl.iwsn.backend.model.dht.DhtData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DhtRepository extends MongoRepository<DhtData, String> {

    DhtData findFirstByOrderByTimestampDesc();
}
