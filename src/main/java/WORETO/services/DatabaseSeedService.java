package WORETO.services;

import WORETO.repositories.TimeRegistryReactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DatabaseSeedService {

    private TimeRegistryReactRepository timeRegistryReactRepository;

    @Autowired
    public DatabaseSeedService(TimeRegistryReactRepository timeRegistryReactRepository){
        this.timeRegistryReactRepository = timeRegistryReactRepository;
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
        // -------------------------------------------------------------------------
        this.initialize();
    }

    private void initialize() {
        // TODO
    }

    public void seedDataBaseJava() {

    }

}
