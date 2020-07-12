package WORETO.business_controller.users;

import WORETO.dtos.TimeRegistryDto;
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

    public Mono<TimeRegistryDto> readTimeRegistryDetailById(String id){
        return this.timeRegistryReactRepository.findById(id).map(TimeRegistryDto::new);
    }

}
