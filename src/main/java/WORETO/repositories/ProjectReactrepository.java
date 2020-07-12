package WORETO.repositories;

import WORETO.documents.Project;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;

public interface ProjectReactrepository extends ReactiveSortingRepository<Project, String> {
}
