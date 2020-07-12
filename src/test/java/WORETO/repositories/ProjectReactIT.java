package WORETO.repositories;

import WORETO.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertEquals;

@TestConfig
public class ProjectReactIT {

    @Autowired
    private ProjectReactrepository projectReactrepository;

    @Test
    void findById(){
        StepVerifier
                .create(this.projectReactrepository.findById("1"))
                .expectNextMatches(project -> {
                    assertEquals("1111", project.getClientId());
                    assertEquals("111", project.getMatterId());
                    assertEquals("Project ONE 1", project.getProjectName());
                    return true;
                })
                .expectComplete()
                .verify();
    }
}
