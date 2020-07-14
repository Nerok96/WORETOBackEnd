package WORETO.business_controller.users;

import WORETO.documents.Status;
import WORETO.documents.TimeRegistry;
import WORETO.dtos.TimeRegistryUpdateDto;
import org.springframework.stereotype.Controller;

@Controller
public class ValidateTimeRegistryController {

    public Boolean validateDraftStatus(TimeRegistry timeRegistry) {
        return validateDraftStatus(new TimeRegistryUpdateDto(timeRegistry));
    }

    public Boolean validateDraftStatus(TimeRegistryUpdateDto timeRegistryUpdateDto) {
        return timeRegistryUpdateDto.getStatus() == Status.DRAFT;
    }
}
