package WORETO.repositories;

import WORETO.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TestConfig
public class UserReactIT {

    @Autowired
    private UserReactRepository userReactRepository;

    @Test
    void findByEmail(){
        // TODO pending seeder service
    }
}
