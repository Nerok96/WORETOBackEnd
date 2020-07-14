package WORETO.controllers.users;

import WORETO.TestConfig;
import WORETO.business_controller.users.ValidateTimeRegistryController;
import WORETO.documents.Project;
import WORETO.documents.Status;
import WORETO.documents.TimeRegistry;
import WORETO.documents.User;
import WORETO.dtos.TimeRegistryCreationDto;
import WORETO.dtos.TimeRegistryDto;
import WORETO.dtos.TimeRegistryUpdateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class ValidateTimeRegistryDraftStatusIT {

    private TimeRegistry timeRegistry;

    @Autowired
    private ValidateTimeRegistryController validateTimeRegistryController;

    @BeforeEach
    void setup() {
        User user = new User();
        user.setEmail("email");
        Project project = new Project();
        project.setProjectName("project name");
        LocalDateTime ldt = LocalDateTime.now();
        this.timeRegistry = TimeRegistry.builder()
                .id("id")
                .assignedUser(user)
                .assignedProject(project)
                .assignedLocalDateTime(ldt)
                .minutesWorked(10)
                .status(Status.READY)
                .description("description")
                .createdByUser(user)
                .lastModifiedByUser(user)
                .lastModifiedLocalDateTime(ldt)
                .build();
    }
    @Test
    void testValidateDraftStatus() {
        TimeRegistry timeRegistry = TimeRegistry.builder()
                .status(Status.DRAFT)
                .build();
        assertTrue(validateTimeRegistryController.validateDraftStatus(timeRegistry));
    }

    @Test
    void testValidateDraftStatusNoDraft() {
        TimeRegistry timeRegistry = TimeRegistry.builder()
                .status(Status.READY)
                .build();
        assertFalse(validateTimeRegistryController.validateDraftStatus(timeRegistry));
    }

    @Test
    void testValidateTimeRegistry() {
        assertTrue(validateTimeRegistryController.validateTimeRegistry(this.timeRegistry));
        this.timeRegistry.setAssignedUser(null);
        assertFalse(validateTimeRegistryController.validateTimeRegistry(this.timeRegistry));
    }

    @Test
    void testValidateTimeRegistryDto() {
        assertTrue(validateTimeRegistryController.validateTimeRegistry(new TimeRegistryDto(this.timeRegistry)));
        this.timeRegistry.setAssignedUser(null);
        assertFalse(validateTimeRegistryController.validateTimeRegistry(new TimeRegistryDto(this.timeRegistry)));
    }

    @Test
    void testValidateTimeRegistryCreationDto() {
        assertTrue(validateTimeRegistryController.validateTimeRegistry(new TimeRegistryCreationDto(this.timeRegistry)));
        this.timeRegistry.setAssignedUser(null);
        assertFalse(validateTimeRegistryController.validateTimeRegistry(new TimeRegistryCreationDto(this.timeRegistry)));
    }

    @Test
    void testValidateTimeRegistryUpdateDto() {
        assertTrue(validateTimeRegistryController.validateTimeRegistry(new TimeRegistryUpdateDto(this.timeRegistry)));
        this.timeRegistry.setAssignedUser(null);
        assertFalse(validateTimeRegistryController.validateTimeRegistry(new TimeRegistryUpdateDto(this.timeRegistry)));
    }
}
