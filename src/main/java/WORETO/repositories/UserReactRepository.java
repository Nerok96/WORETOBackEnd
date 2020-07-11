package WORETO.repositories;

import WORETO.documents.User;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Mono;

public interface UserReactRepository extends ReactiveSortingRepository<User, String> {

    Mono<User> findByEmail(String email);
}
