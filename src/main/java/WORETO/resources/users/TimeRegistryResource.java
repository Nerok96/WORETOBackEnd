package WORETO.resources.users;

import WORETO.business_controller.users.TimeRegistryController;
import WORETO.dtos.TimeRegistryCreationDto;
import WORETO.dtos.TimeRegistryReadDetailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(TimeRegistryResource.TIME_REGISTRIES)
public class TimeRegistryResource {

    public static final String TIME_REGISTRIES = "/time-registries";
    public static final String TIME_REGISTRIES_ID = "/{id}";

    private TimeRegistryController timeRegistryController;

    @Autowired
    public TimeRegistryResource( TimeRegistryController timeRegistryController) {
        this.timeRegistryController = timeRegistryController;
    }

    @GetMapping(value = TIME_REGISTRIES_ID)
    public Mono<TimeRegistryReadDetailDto> readTimeRegistryDetailById(@PathVariable String id) {
        return timeRegistryController.readTimeRegistryDetailById(id);
    }

    @PostMapping
    public Mono<TimeRegistryReadDetailDto> createTimeRegistry(
            @Valid @RequestBody TimeRegistryCreationDto timeRegistryCreationDto) {
        return this.timeRegistryController.createTimeRegistry(timeRegistryCreationDto);
    }
}
