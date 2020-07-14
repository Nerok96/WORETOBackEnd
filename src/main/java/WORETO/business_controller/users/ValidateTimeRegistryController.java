package WORETO.business_controller.users;

import WORETO.documents.Status;
import WORETO.documents.TimeRegistry;
import WORETO.dtos.TimeRegistryCommonDto;
import WORETO.dtos.TimeRegistryCreationDto;
import WORETO.dtos.TimeRegistryReadDetailDto;
import WORETO.dtos.TimeRegistryUpdateDto;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class ValidateTimeRegistryController {

    public Boolean validateDraftStatus(TimeRegistry timeRegistry) {
        return validateDraftStatus(new TimeRegistryUpdateDto(timeRegistry));
    }

    public Boolean validateDraftStatus(TimeRegistryUpdateDto timeRegistryUpdateDto) {
        return timeRegistryUpdateDto.getStatus().roleName().equals(Status.DRAFT.roleName());
    }

    public Boolean validateTimeRegistry(TimeRegistry timeRegistry) {
        return Objects.nonNull(timeRegistry.getId()) &&
                Objects.nonNull(timeRegistry.getCreatedByUser()) &&
                Objects.nonNull(timeRegistry.getCreatedAtLocalDateTime()) &&
                Objects.nonNull(timeRegistry.getLastModifiedByUser()) &&
                Objects.nonNull(timeRegistry.getLastModifiedLocalDateTime()) &&
                validateTimeRegistryCommonFields(
                        new TimeRegistryCommonDto(
                                timeRegistry.getAssignedUser().getEmail(),
                                timeRegistry.getAssignedProject().getId(),
                                timeRegistry.getAssignedLocalDateTime(),
                                timeRegistry.getMinutesWorked(),
                                timeRegistry.getStatus(),
                                timeRegistry.getDescription()
                        )
                );
    }

    public Boolean validateTimeRegistry(TimeRegistryReadDetailDto timeRegistryReadDetailDto) {
        return Objects.nonNull(timeRegistryReadDetailDto.getId()) &&
                Objects.nonNull(timeRegistryReadDetailDto.getCreatedByUserEmail()) &&
                Objects.nonNull(timeRegistryReadDetailDto.getCreatedAtLocalDateTime()) &&
                Objects.nonNull(timeRegistryReadDetailDto.getLastModifiedByUserEmail()) &&
                Objects.nonNull(timeRegistryReadDetailDto.getLastModifiedLocalDateTime()) &&
                validateTimeRegistryCommonFields(
                        new TimeRegistryCommonDto(
                                timeRegistryReadDetailDto.getAssignedUserEmail(),
                                timeRegistryReadDetailDto.getAssignedProjectId(),
                                timeRegistryReadDetailDto.getAssignedLocalDateTime(),
                                timeRegistryReadDetailDto.getMinutesWorked(),
                                timeRegistryReadDetailDto.getStatus(),
                                timeRegistryReadDetailDto.getDescription()
                        )
                );
    }

    public Boolean validateTimeRegistry(TimeRegistryCreationDto timeRegistryCreationDto) {
        return Objects.nonNull(timeRegistryCreationDto.getCreatedByUserEmail()) &&
                validateTimeRegistryCommonFields(
                        new TimeRegistryCommonDto(
                                timeRegistryCreationDto.getAssignedUserEmail(),
                                timeRegistryCreationDto.getAssignedProjectId(),
                                timeRegistryCreationDto.getAssignedLocalDateTime(),
                                timeRegistryCreationDto.getMinutesWorked(),
                                timeRegistryCreationDto.getStatus(),
                                timeRegistryCreationDto.getDescription()
                        ));
    }

    public Boolean validateTimeRegistry(TimeRegistryUpdateDto timeRegistryUpdateDto) {
        return Objects.nonNull(timeRegistryUpdateDto.getId()) &&
                Objects.nonNull(timeRegistryUpdateDto.getLastModifiedByUserEmail()) &&
                validateTimeRegistryCommonFields(
                        new TimeRegistryCommonDto(
                                timeRegistryUpdateDto.getAssignedUserEmail(),
                                timeRegistryUpdateDto.getAssignedProjectId(),
                                timeRegistryUpdateDto.getAssignedLocalDateTime(),
                                timeRegistryUpdateDto.getMinutesWorked(),
                                timeRegistryUpdateDto.getStatus(),
                                timeRegistryUpdateDto.getDescription()
                        ));
    }

    private Boolean validateTimeRegistryCommonFields(TimeRegistryCommonDto timeRegistryCommonDto) {
        return Objects.nonNull(timeRegistryCommonDto.getAssignedUserEmail()) &&
                Objects.nonNull(timeRegistryCommonDto.getAssignedProjectId()) &&
                Objects.nonNull(timeRegistryCommonDto.getAssignedLocalDateTime()) &&
                Objects.nonNull(timeRegistryCommonDto.getMinutesWorked()) &&
                Objects.nonNull(timeRegistryCommonDto.getStatus()) &&
                Objects.nonNull(timeRegistryCommonDto.getDescription());
    }
}
