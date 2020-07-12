package WORETO.services;

import WORETO.repositories.ProjectReactrepository;
import WORETO.repositories.TimeRegistryReactRepository;
import WORETO.repositories.UserReactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DatabaseSeedService {

    @Value("${miw.admin.email}")
    private String email;
    @Value("${ miw.admin.name}")
    private String name;
    @Value("${miw.admin.password}")
    private String password;

    //TODO change react repositories for synchronous ones
    private TimeRegistryReactRepository timeRegistryReactRepository;
    private ProjectReactrepository projectReactrepository;
    private UserReactRepository userReactRepository;

    @Autowired
    public DatabaseSeedService(TimeRegistryReactRepository timeRegistryReactRepository,
                               ProjectReactrepository projectReactrepository,
                               UserReactRepository userReactRepository){
        this.timeRegistryReactRepository = timeRegistryReactRepository;
        this.projectReactrepository = projectReactrepository;
        this.userReactRepository = userReactRepository;
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
        this.timeRegistryReactRepository.deleteAll();
        this.projectReactrepository.deleteAll();
        this.userReactRepository.deleteAll();
        // -------------------------------------------------------------------------
        this.initialize();
    }

    private void initialize() {

    }

    public void seedDataBaseJava() {

    }

}
