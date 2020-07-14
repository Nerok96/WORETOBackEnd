package WORETO.repositories;

import WORETO.TestConfig;
import WORETO.documents.Status;
import WORETO.documents.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@TestConfig
public class TimeRegistryReactIT {

    @Autowired
    private TimeRegistryReactRepository timeRegistryReactRepository;

    @Autowired
    private UserRepository userRepository;

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

    @Test
    void testFindByStatus() {
        StepVerifier
                .create(this.timeRegistryReactRepository.findByStatus(Status.DRAFT))
                .expectNextMatches(timeRegistry -> {
                    assertEquals("1", timeRegistry.getId());
                    assertEquals(Status.DRAFT, timeRegistry.getStatus());
                    return true;
                })
                .expectNextMatches(timeRegistry -> {
                    assertEquals("5", timeRegistry.getId());
                    assertEquals(Status.DRAFT, timeRegistry.getStatus());
                    return true;
                })
                .expectNextMatches(timeRegistry -> {
                    assertEquals("6", timeRegistry.getId());
                    assertEquals(Status.DRAFT, timeRegistry.getStatus());
                    return true;
                })
                .expectNextMatches(timeRegistry -> {
                    assertEquals("7", timeRegistry.getId());
                    assertEquals(Status.DRAFT, timeRegistry.getStatus());
                    return true;
                })
                .expectComplete()
                .verify();
    }

    @Test
    void testFindByAssignedLocalDateTimeGreaterThan() {
        LocalDate fixedLd = LocalDate.of(2023, 1, 1);
        StepVerifier
                .create(this.timeRegistryReactRepository.findByAssignedLocalDateGreaterThan(fixedLd))
                .expectNextMatches(timeRegistry -> {
                    assertEquals("6", timeRegistry.getId());
                    assertEquals(Status.DRAFT, timeRegistry.getStatus());
                    return true;
                })
                .expectComplete()
                .verify();
    }

    @Test
    void testFindByAssignedLocalDate() {
        LocalDate fixedLd = LocalDate.of(2023, 1, 1);
        StepVerifier
                .create(this.timeRegistryReactRepository.findByAssignedLocalDate(fixedLd))
                .expectNextMatches(timeRegistry -> {
                    assertEquals("7", timeRegistry.getId());
                    assertEquals(Status.DRAFT, timeRegistry.getStatus());
                    return true;
                })
                .expectNextMatches(timeRegistry -> {
                    assertEquals("8", timeRegistry.getId());
                    assertEquals(Status.READY, timeRegistry.getStatus());
                    return true;
                })
                .expectComplete()
                .verify();
    }


    @Test
    void testFindByAssignedLocalDateAndStatus() {
        LocalDate fixedLd = LocalDate.of(2023, 1, 1);
        StepVerifier
                .create(this.timeRegistryReactRepository.findByAssignedLocalDateAndStatus(
                        fixedLd, Status.DRAFT))
                .expectNextMatches(timeRegistry -> {
                    assertEquals("7", timeRegistry.getId());
                    assertEquals(Status.DRAFT, timeRegistry.getStatus());
                    return true;
                })
                .expectComplete()
                .verify();
    }

    @Test
    void testFindByAssignedLocalDateAndStatusAndAssignedUser() {
        User assignedUser = this.userRepository.findByEmail("admin@admin.com").get();
        LocalDate fixedLd = LocalDate.of(2023, 1, 1);
        StepVerifier
                .create(this.timeRegistryReactRepository.findByAssignedLocalDateAndStatusAndAssignedUser(
                        fixedLd, Status.DRAFT, assignedUser))
                .expectNextMatches(timeRegistry -> {
                    assertEquals("7", timeRegistry.getId());
                    assertEquals(Status.DRAFT, timeRegistry.getStatus());
                    return true;
                })
                .expectComplete()
                .verify();
    }



}
