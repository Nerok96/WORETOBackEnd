package WORETO.business_controller.users;

import WORETO.documents.Status;
import WORETO.documents.TimeRegistry;
import WORETO.dtos.TimeRegistryCommonDto;
import WORETO.dtos.TimeRegistryCreationDto;
import WORETO.dtos.TimeRegistryDto;
import WORETO.dtos.TimeRegistryUpdateDto;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class ValidateTimeRegistryController {

    public Boolean validateDraftStatus(TimeRegistry timeRegistry) {
        return validateDraftStatus(new TimeRegistryUpdateDto(timeRegistry));
    }

    public Boolean validateDraftStatus(TimeRegistryUpdateDto timeRegistryUpdateDto) {
        return timeRegistryUpdateDto.getStatus() == Status.DRAFT;
    }

    public Boolean validateTimeRegistry(TimeRegistry timeRegistry) {
        return Objects.nonNull(timeRegistry.getId()) &&
                Objects.nonNull(timeRegistry.getCreatedByUser()) &&
                Objects.nonNull(timeRegistry.getCreatedAtLocalDateTime()) &&
                Objects.nonNull(timeRegistry.getLastModifiedByUser()) &&
                Objects.nonNull(timeRegistry.getLastModifiedLocalDateTime()) &&
                validateTimeRegistryCommonFields(
                        new TimeRegistryCommonDto(
                                timeRegistry.getAssignedUser(),
                                timeRegistry.getAssignedProject(),
                                timeRegistry.getAssignedLocalDateTime(),
                                timeRegistry.getMinutesWorked(),
                                timeRegistry.getStatus(),
                                timeRegistry.getDescription()
                        )
                );
    }

    public Boolean validateTimeRegistry(TimeRegistryDto timeRegistryDto) {
        return Objects.nonNull(timeRegistryDto.getId()) &&
                Objects.nonNull(timeRegistryDto.getCreatedByUser()) &&
                Objects.nonNull(timeRegistryDto.getCreatedAtLocalDateTime()) &&
                Objects.nonNull(timeRegistryDto.getLastModifiedByUser()) &&
                Objects.nonNull(timeRegistryDto.getLastModifiedLocalDateTime()) &&
                validateTimeRegistryCommonFields(
                        new TimeRegistryCommonDto(
                                timeRegistryDto.getAssignedUser(),
                                timeRegistryDto.getAssignedProject(),
                                timeRegistryDto.getAssignedLocalDateTime(),
                                timeRegistryDto.getMinutesWorked(),
                                timeRegistryDto.getStatus(),
                                timeRegistryDto.getDescription()
                        )
                );
    }

    public Boolean validateTimeRegistry(TimeRegistryCreationDto timeRegistryCreationDto) {
        return Objects.nonNull(timeRegistryCreationDto.getCreatedByUser()) &&
                validateTimeRegistryCommonFields(
                        new TimeRegistryCommonDto(
                                timeRegistryCreationDto.getAssignedUser(),
                                timeRegistryCreationDto.getAssignedProject(),
                                timeRegistryCreationDto.getAssignedLocalDateTime(),
                                timeRegistryCreationDto.getMinutesWorked(),
                                timeRegistryCreationDto.getStatus(),
                                timeRegistryCreationDto.getDescription()
                        ));
    }

    public Boolean validateTimeRegistry(TimeRegistryUpdateDto timeRegistryUpdateDto) {
        return Objects.nonNull(timeRegistryUpdateDto.getId()) &&
                Objects.nonNull(timeRegistryUpdateDto.getLastModifiedByUser()) &&
                validateTimeRegistryCommonFields(
                        new TimeRegistryCommonDto(
                                timeRegistryUpdateDto.getAssignedUser(),
                                timeRegistryUpdateDto.getAssignedProject(),
                                timeRegistryUpdateDto.getAssignedLocalDateTime(),
                                timeRegistryUpdateDto.getMinutesWorked(),
                                timeRegistryUpdateDto.getStatus(),
                                timeRegistryUpdateDto.getDescription()
                        ));
    }

    private Boolean validateTimeRegistryCommonFields(TimeRegistryCommonDto timeRegistryCommonDto) {
        return Objects.nonNull(timeRegistryCommonDto.getAssignedUser()) &&
                Objects.nonNull(timeRegistryCommonDto.getAssignedProject()) &&
                Objects.nonNull(timeRegistryCommonDto.getAssignedLocalDateTime()) &&
                Objects.nonNull(timeRegistryCommonDto.getMinutesWorked()) &&
                Objects.nonNull(timeRegistryCommonDto.getStatus()) &&
                Objects.nonNull(timeRegistryCommonDto.getDescription());
    }
}
