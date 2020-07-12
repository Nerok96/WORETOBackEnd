package WORETO.repositories;

import WORETO.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByMobile(String mobile);

}
