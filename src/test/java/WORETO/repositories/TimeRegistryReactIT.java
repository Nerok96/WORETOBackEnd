package WORETO.repositories;

import WORETO.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import static org.junit.Assert.assertEquals;

@TestConfig
public class TimeRegistryReactIT {

    @Autowired
    private TimeRegistryReactRepository timeRegistryReactRepository;

    @Test
    void findById(){
        StepVerifier
                .create(this.timeRegistryReactRepository.findById("1"))
                .expectNextMatches(timeRegistry -> {
                    assertEquals("TimeRecorderA", timeRegistry.getCreatedByUser().getName());
                    assertEquals("TimeRecorderA", timeRegistry.getAssignedUser().getName());
                    assertEquals("Project ONE 1", timeRegistry.getAssignedProject().getProjectName());
                    return true;
                })
                .expectComplete()
                .verify();

        StepVerifier
                .create(this.timeRegistryReactRepository.findById("4"))
                .expectNextMatches(timeRegistry -> {
                    assertEquals("Partner", timeRegistry.getCreatedByUser().getName());
                    assertEquals("Partner", timeRegistry.getAssignedUser().getName());
                    assertEquals("Project TWO 2", timeRegistry.getAssignedProject().getProjectName());
                    return true;
                })
                .expectComplete()
                .verify();
    }
}
