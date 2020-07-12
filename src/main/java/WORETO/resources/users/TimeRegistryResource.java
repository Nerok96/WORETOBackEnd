package WORETO.resources.users;

import WORETO.business_controller.users.TimeRegistryController;
import WORETO.dtos.TimeRegistryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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
    public Mono<TimeRegistryDto> readTimeRegistryDetailById(@PathVariable String id) {
        return timeRegistryController.readTimeRegistryDetailById(id);
    }
}
