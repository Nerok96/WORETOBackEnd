package WORETO.repositories;

import WORETO.documents.TimeRegistry;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface TimeRegistryReactRepository extends ReactiveSortingRepository<TimeRegistry, String> {
}
