package WORETO.business_controller.users;

import WORETO.documents.Project;
import WORETO.documents.TimeRegistry;
import WORETO.documents.User;
import WORETO.dtos.TimeRegistryCreationDto;
import WORETO.dtos.TimeRegistryReadDetailDto;
import WORETO.dtos.TimeRegistryUpdateDto;
import WORETO.repositories.ProjectReactrepository;
import WORETO.repositories.TimeRegistryReactRepository;
import WORETO.repositories.UserReactRepository;
import WORETO.services.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Controller
public class TimeRegistryController {

    private TimeRegistryReactRepository timeRegistryReactRepository;
    private UserReactRepository userReactRepository;
    private ProjectReactrepository projectReactrepository;
    private SequenceGenerator sequenceGenerator;
    private ValidateTimeRegistryController validateTimeRegistryController;

    @Autowired
    public TimeRegistryController(TimeRegistryReactRepository timeRegistryReactRepository,
                                  UserReactRepository userReactRepository,
                                  ProjectReactrepository projectReactrepository,
                                  SequenceGenerator sequenceGenerator,
                                  ValidateTimeRegistryController validateTimeRegistryController) {
        this.timeRegistryReactRepository = timeRegistryReactRepository;
        this.userReactRepository = userReactRepository;
        this.projectReactrepository = projectReactrepository;
        this.sequenceGenerator = sequenceGenerator;
        this.validateTimeRegistryController = validateTimeRegistryController;
    }

    public Mono<TimeRegistryReadDetailDto> readTimeRegistryDetailById(String id) {
        return this.timeRegistryReactRepository.findById(id).map(TimeRegistryReadDetailDto::new);
    }

    public Mono<TimeRegistryReadDetailDto> createTimeRegistry(TimeRegistryCreationDto timeRegistryCreationDto) {
        if (validateTimeRegistryController.validateTimeRegistry(timeRegistryCreationDto)) {
            TimeRegistry timeRegistry = TimeRegistry.builder()
                    .id(sequenceGenerator.getNextSequence(TimeRegistry.SEQUENCE_NAME))
                    .assignedLocalDateTime(timeRegistryCreationDto.getAssignedLocalDateTime())
                    .minutesWorked(timeRegistryCreationDto.getMinutesWorked())
                    .status(timeRegistryCreationDto.getStatus())
                    .description(timeRegistryCreationDto.getDescription())
                    .build();
            Mono<User> assignedUserMono = this.userReactRepository
                    .findByEmail(timeRegistryCreationDto.getAssignedUserEmail())
                    .doOnNext(timeRegistry::setAssignedUser);
            Mono<User> createdByUserMono = this.userReactRepository
                    .findByEmail(timeRegistryCreationDto.getAssignedUserEmail())
                    .doOnNext(timeRegistry::setCreatedByUser);
            Mono<Project> projectMono = this.projectReactrepository
                    .findById(timeRegistryCreationDto.getAssignedProjectId())
                    .doOnNext(timeRegistry::setAssignedProject);
            return Mono.when(assignedUserMono, createdByUserMono, projectMono)
                    .then(this.timeRegistryReactRepository.save(timeRegistry).map(TimeRegistryReadDetailDto::new));
        } else {
            return null;
        }
    }

    public Mono<TimeRegistryReadDetailDto> updateTimeRegistry(TimeRegistryUpdateDto timeRegistryUpdateDto) {
        if (validateTimeRegistryController.validateDraftStatus(timeRegistryUpdateDto)) {
            TimeRegistry timeRegistryFromUpdateDto = TimeRegistry.builder()
                    .assignedLocalDateTime(timeRegistryUpdateDto.getAssignedLocalDateTime())
                    .minutesWorked(timeRegistryUpdateDto.getMinutesWorked())
                    .status(timeRegistryUpdateDto.getStatus())
                    .description(timeRegistryUpdateDto.getDescription())
                    .lastModifiedLocalDateTime(LocalDateTime.now())
                    .build();
            Mono<User> assignedUserMono = this.userReactRepository
                    .findByEmail(timeRegistryUpdateDto.getAssignedUserEmail())
                    .doOnNext(timeRegistryFromUpdateDto::setAssignedUser);
            Mono<User> lastModifiedByUserMono = this.userReactRepository
                    .findByEmail(timeRegistryUpdateDto.getLastModifiedByUserEmail())
                    .doOnNext(timeRegistryFromUpdateDto::setAssignedUser);
            Mono<Project> assignedProjectMono = this.projectReactrepository
                    .findById(timeRegistryUpdateDto.getAssignedProjectId())
                    .doOnNext(timeRegistryFromUpdateDto::setAssignedProject);
            Mono<TimeRegistry> timeRegistryMono =
                    Mono.when(assignedUserMono, assignedProjectMono, lastModifiedByUserMono).
                            then(this.timeRegistryReactRepository
                                    .findById(timeRegistryUpdateDto.getId())
                                    .map(timeRegistry -> {
                                        timeRegistry = timeRegistryFromUpdateDto;
                                        return timeRegistry;
                                    })
                            );
            return Mono.when(timeRegistryMono)
                    .then(this.timeRegistryReactRepository.saveAll(timeRegistryMono)
                            .next())
                    .map(TimeRegistryReadDetailDto::new);
        } else {
            return null;
        }
    }
}
