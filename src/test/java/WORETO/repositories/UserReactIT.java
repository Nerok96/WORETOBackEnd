package WORETO.repositories;

import WORETO.TestConfig;
import WORETO.services.DatabaseSeedService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertEquals;

@TestConfig
public class UserReactIT {

    @Autowired
    private UserReactRepository userReactRepository;

    @Test
    void findByEmail(){
        StepVerifier
                .create(this.userReactRepository.findByEmail("admin@admin.com"))
                .expectNextMatches(user -> {
                    assertEquals("admin@admin.com", user.getEmail());
                    assertEquals("admin", user.getName());
                    return true;
                })
                .expectComplete()
                .verify();
        StepVerifier
                .create(this.userReactRepository.findByEmail("timerecorderB@timerecorderB.com"))
                .expectNextMatches(user -> {
                    assertEquals("timerecorderB@timerecorderB.com", user.getEmail());
                    assertEquals("TimeRecorderB", user.getName());
                    return true;
                })
                .expectComplete()
                .verify();
    }
}
