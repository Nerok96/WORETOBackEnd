package WORETO.controllers.users;

import WORETO.TestConfig;
import WORETO.business_controller.users.ValidateTimeRegistryController;
import WORETO.documents.Project;
import WORETO.documents.Status;
import WORETO.documents.TimeRegistry;
import WORETO.documents.User;
import WORETO.dtos.TimeRegistryCreationDto;
import WORETO.dtos.TimeRegistryReadDetailDto;
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
        project.setClientId("clientID");
        project.setMatterId("MatterID");
        project.setProjectName("project name");
        LocalDateTime ldt = LocalDateTime.now();
        this.timeRegistry = TimeRegistry.builder()
                .id("id")
                .assignedUser(user)
                .assignedProject(project)
                .assignedLocalDate(ldt.toLocalDate())
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
        TimeRegistry timeRegistry = this.timeRegistry;
        timeRegistry.setStatus(Status.DRAFT);
        assertTrue(validateTimeRegistryController.validateDraftStatus(timeRegistry));
    }

    @Test
    void testValidateDraftStatusNoDraft() {
        assertFalse(validateTimeRegistryController.validateDraftStatus(this.timeRegistry));
    }

}
