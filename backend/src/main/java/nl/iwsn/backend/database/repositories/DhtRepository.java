package nl.iwsn.backend.database.repositories;

import nl.iwsn.backend.model.dht.DhtData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DhtRepository extends MongoRepository<DhtData, String> {

}
