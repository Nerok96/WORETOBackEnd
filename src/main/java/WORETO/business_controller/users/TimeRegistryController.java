package WORETO.business_controller.users;

import WORETO.dtos.TimeRegistryReadDetailDto;
import WORETO.repositories.TimeRegistryReactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class TimeRegistryController {

    private TimeRegistryReactRepository timeRegistryReactRepository;

    @Autowired
    public TimeRegistryController(TimeRegistryReactRepository timeRegistryReactRepository){
        this.timeRegistryReactRepository = timeRegistryReactRepository;
    }

    public Mono<TimeRegistryReadDetailDto> readTimeRegistryDetailById(String id) {
        return this.timeRegistryReactRepository.findById(id).map(TimeRegistryReadDetailDto::new);
    }

}
