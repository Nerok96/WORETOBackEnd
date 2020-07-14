package WORETO.services;

import WORETO.documents.*;
import WORETO.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class DatabaseSeedService {

    @Value("${miw.admin.email}")
    private String email;
    @Value("${miw.admin.name}")
    private String name;
    @Value("${miw.admin.password}")
    private String password;

    private ArrayList<User> usersArray = new ArrayList<>();
    private ArrayList<Project> projectsArray = new ArrayList<>();
    private ArrayList<TimeRegistry> timeRegistriesArray = new ArrayList<>();


    private TimeRegistryRepository timeRegistryRepository;
    private ProjectRepository projectRepository;
    private UserRepository userRepository;

    private SequenceGenerator sequenceGenerator;

    @Autowired
    public DatabaseSeedService(TimeRegistryRepository timeRegistryRepository,
                               ProjectRepository projectRepository,
                               UserRepository userRepository,
                               SequenceGenerator sequenceGenerator){
        this.timeRegistryRepository = timeRegistryRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.sequenceGenerator = sequenceGenerator;
    }

    @PostConstruct
    public void constructor() {
       this.deleteAllAndInitializeAndSeedDataBase();
    }

    public void deleteAllAndInitializeAndSeedDataBase() {
        this.deleteAllAndInitialize();
        this.seedDataBaseJava();
    }

    public void deleteAllAndInitialize() {
        // Delete Repositories -----------------------------------------------------
        this.timeRegistryRepository.deleteAll();
        this.projectRepository.deleteAll();
        this.userRepository.deleteAll();
        this.sequenceGenerator.deleteAllDatabaseSequence();
        // -------------------------------------------------------------------------
        this.initialize();
    }

    private void initialize() {
        if (!this.userRepository.findByEmail(this.email).isPresent()) {
            User admin = new User();
            admin.setIdFromLong(sequenceGenerator.getNextSequence(User.SEQUENCE_NAME));
            admin.setEmail(this.email);
            admin.setName(this.name);
            admin.setPassword(this.password);
            admin.setRoles( new Role[]{
                    Role.ADMIN,
                    Role.PARTNER,
                    Role.TIMERECORDER,
                    Role.BILLING_SYSTEM,
                    Role.ACTIVEDIRECTORY_SYTEM
            });
            this.userRepository.save(admin);
            this.usersArray.add(admin);
        }
    }

    public void seedDataBaseJava() {
        this.seedUsers();
        this.seedProjects();
        this.seedTimeRegistries();
    }

    private void seedUsers(){
        User[] usersArray = {
                User.builder()
                        .id(sequenceGenerator.getNextSequence(User.SEQUENCE_NAME))
                        .email("partner@partner.com")
                        .name("Partner")
                        .surname("Partner")
                        .roles(Role.PARTNER, Role.TIMERECORDER)
                        .build(),
                User.builder()
                        .id(sequenceGenerator.getNextSequence(User.SEQUENCE_NAME))
                        .email("timerecorderA@timerecorderA.com")
                        .name("TimeRecorderA")
                        .surname("TimeRecorderA")
                        .roles(Role.TIMERECORDER)
                        .build(),
                User.builder()
                        .id(sequenceGenerator.getNextSequence(User.SEQUENCE_NAME))
                        .email("timerecorderB@timerecorderB.com")
                        .name("TimeRecorderB")
                        .surname("TimeRecorderB")
                        .roles(Role.TIMERECORDER)
                        .build()
        };
        this.userRepository.saveAll(Arrays.asList(usersArray));
        this.usersArray.addAll(Arrays.asList(usersArray));
    }

    private void seedProjects(){
        Project[] projectsArray = new Project[]{
                Project.builder()
                        .id(sequenceGenerator.getNextSequence(Project.SEQUENCE_NAME))
                        .clientId("1111")
                        .matterId("111")
                        .projectName("Project ONE 1")
                        .partnerList(this.usersArray.get(1))
                        .build(),
                Project.builder()
                        .id(sequenceGenerator.getNextSequence(Project.SEQUENCE_NAME))
                        .clientId("2222")
                        .matterId("222")
                        .projectName("Project TWO 2")
                        .partnerList(this.usersArray.get(1))
                        .build()

        };
        this.projectRepository.saveAll(Arrays.asList(projectsArray));
        this.projectsArray.addAll(Arrays.asList(projectsArray));
    }

    private void seedTimeRegistries(){
        LocalDateTime fixedLdt = LocalDateTime.of(2022, 4, 1, 1, 1, 1);
        TimeRegistry[] timeRegistriesArray = new TimeRegistry[]{
                TimeRegistry.builder()
                        .id(sequenceGenerator.getNextSequence(TimeRegistry.SEQUENCE_NAME))
                        .assignedUser(this.usersArray.get(2))
                        .assignedProject(this.projectsArray.get(0))
                        .assignedLocalDateTime(fixedLdt)
                        .minutesWorked(10)
                        .status(Status.DRAFT)
                        .description("Working hard")
                        .createdByUser(this.usersArray.get(2))
                        .build(),
                TimeRegistry.builder()
                        .id(sequenceGenerator.getNextSequence(TimeRegistry.SEQUENCE_NAME))
                        .assignedUser(this.usersArray.get(3))
                        .assignedProject(this.projectsArray.get(0))
                        .assignedLocalDateTime(fixedLdt)
                        .minutesWorked(20)
                        .status(Status.READY)
                        .description("Working hard")
                        .createdByUser(this.usersArray.get(3))
                        .build(),
                TimeRegistry.builder()
                        .id(sequenceGenerator.getNextSequence(TimeRegistry.SEQUENCE_NAME))
                        .assignedUser(this.usersArray.get(3))
                        .assignedProject(this.projectsArray.get(1))
                        .assignedLocalDateTime(fixedLdt)
                        .minutesWorked(30)
                        .status(Status.TRANSFERRED)
                        .description("Working hard")
                        .createdByUser(this.usersArray.get(3))
                        .build(),
                TimeRegistry.builder()
                        .id(sequenceGenerator.getNextSequence(TimeRegistry.SEQUENCE_NAME))
                        .assignedUser(this.usersArray.get(1))
                        .assignedProject(this.projectsArray.get(1))
                        .assignedLocalDateTime(fixedLdt)
                        .minutesWorked(40)
                        .status(Status.BILLED)
                        .description("Working hard")
                        .createdByUser(this.usersArray.get(1))
                        .build()
        };
        this.timeRegistryRepository.saveAll(Arrays.asList(timeRegistriesArray));
        this.timeRegistriesArray.addAll(Arrays.asList(timeRegistriesArray));
    }

}
