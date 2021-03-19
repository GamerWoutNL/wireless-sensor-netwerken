package nl.iwsn.backend.database.repositories;

import nl.iwsn.backend.model.dht.DhtData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DhtRepository extends MongoRepository<DhtData, Integer> {

}
