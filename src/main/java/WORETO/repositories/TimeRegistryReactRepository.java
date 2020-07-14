package WORETO.repositories;

import WORETO.documents.Status;
import WORETO.documents.TimeRegistry;
import WORETO.documents.User;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface TimeRegistryReactRepository extends ReactiveSortingRepository<TimeRegistry, String> {
    Flux<TimeRegistry> findByStatus(Status status);

    Flux<TimeRegistry> findByAssignedLocalDateGreaterThan(LocalDate Localdate);

    Flux<TimeRegistry> findByAssignedLocalDate(LocalDate Localdate);

    Flux<TimeRegistry> findByAssignedLocalDateAndStatus(LocalDate Localdate, Status status);

    Flux<TimeRegistry> findByAssignedLocalDateAndStatusAndAssignedUser(LocalDate LocalDate, Status status, User assignedUser);
}
