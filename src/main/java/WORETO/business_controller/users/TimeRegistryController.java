package WORETO.business_controller.users;

import WORETO.documents.Project;
import WORETO.documents.TimeRegistry;
import WORETO.documents.User;
import WORETO.dtos.TimeRegistryCreationDto;
import WORETO.dtos.TimeRegistryReadDetailDto;
import WORETO.repositories.ProjectReactrepository;
import WORETO.repositories.TimeRegistryReactRepository;
import WORETO.repositories.UserReactRepository;
import WORETO.repositories.UserRepository;
import WORETO.services.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class TimeRegistryController {

    private TimeRegistryReactRepository timeRegistryReactRepository;
    private UserReactRepository userReactRepository;
    private ProjectReactrepository projectReactrepository;
    private SequenceGenerator sequenceGenerator;

    @Autowired
    public TimeRegistryController(TimeRegistryReactRepository timeRegistryReactRepository,
                                  UserReactRepository userReactRepository,
                                  ProjectReactrepository projectReactrepository,
                                  SequenceGenerator sequenceGenerator) {
        this.timeRegistryReactRepository = timeRegistryReactRepository;
        this.userReactRepository = userReactRepository;
        this.projectReactrepository = projectReactrepository;
        this.sequenceGenerator = sequenceGenerator;
    }

    public Mono<TimeRegistryReadDetailDto> readTimeRegistryDetailById(String id) {
        return this.timeRegistryReactRepository.findById(id).map(TimeRegistryReadDetailDto::new);
    }

    public Mono<TimeRegistryReadDetailDto> createTimeRegistry(TimeRegistryCreationDto timeRegistryCreationDto) {
        TimeRegistry timeRegistry = TimeRegistry.builder()
                .id(sequenceGenerator.getNextSequence(TimeRegistry.SEQUENCE_NAME))
                .assignedLocalDateTime(timeRegistryCreationDto.getAssignedLocalDateTime())
                .minutesWorked(timeRegistryCreationDto.getMinutesWorked())
                .status(timeRegistryCreationDto.getStatus())
                .description(timeRegistryCreationDto.getDescription())
                .build();
        Mono<User> assignedUserMono = this.userReactRepository.findByEmail(timeRegistryCreationDto.getAssignedUserEmail())
                .doOnNext(timeRegistry::setAssignedUser);
        Mono<User> createdByUserMono = this.userReactRepository.findByEmail(timeRegistryCreationDto.getAssignedUserEmail())
                .doOnNext(timeRegistry::setCreatedByUser);
        Mono<Project> projectMono = this.projectReactrepository.findById(timeRegistryCreationDto.getAssignedProjectId())
                .doOnNext(timeRegistry::setAssignedProject);
        return Mono.when(assignedUserMono, createdByUserMono, projectMono)
                .then(this.timeRegistryReactRepository.save(timeRegistry).map(TimeRegistryReadDetailDto::new));
    }
}
