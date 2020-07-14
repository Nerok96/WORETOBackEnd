package WORETO.controllers.users;

import WORETO.TestConfig;
import WORETO.business_controller.users.ValidateTimeRegistryController;
import WORETO.documents.Status;
import WORETO.documents.TimeRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class ValidateTimeRegistryDraftStatusIT {

    @Autowired
    private ValidateTimeRegistryController validateTimeRegistryController;

    @Test
    void validateDraftStatus() {
        TimeRegistry timeRegistry = TimeRegistry.builder()
                .status(Status.DRAFT)
                .build();
        assertTrue(validateTimeRegistryController.validateDraftStatus(timeRegistry));
    }

    @Test
    void validateDraftStatusNoDraft() {
        TimeRegistry timeRegistry = TimeRegistry.builder()
                .status(Status.READY)
                .build();
        assertFalse(validateTimeRegistryController.validateDraftStatus(timeRegistry));
    }
}
