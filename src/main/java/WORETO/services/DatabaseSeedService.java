package WORETO.services;

import WORETO.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DatabaseSeedService {

    @Value("${miw.admin.email}")
    private String email;
    @Value("${miw.admin.name}")
    private String name;
    @Value("${miw.admin.password}")
    private String password;

    private TimeRegistryRepository timeRegistryRepository;
    private ProjectRepository projectRepository;
    private UserRepository userRepository;

    @Autowired
    public DatabaseSeedService(TimeRegistryRepository timeRegistryRepository,
                               ProjectRepository projectRepository,
                               UserRepository userRepository){
        this.timeRegistryRepository = timeRegistryRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
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
        // -------------------------------------------------------------------------
        this.initialize();
    }

    private void initialize() {

    }

    public void seedDataBaseJava() {

    }

}
