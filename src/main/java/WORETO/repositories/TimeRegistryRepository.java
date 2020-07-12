package WORETO.repositories;

import WORETO.documents.TimeRegistry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimeRegistryRepository extends MongoRepository<TimeRegistry, String> {
}
