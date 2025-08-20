package com.neg.technology.human.resource.company.repository;

import com.neg.technology.human.resource.company.model.entity.Project;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ProjectRepository extends ReactiveCrudRepository<Project, Long> {

    Mono<Project> findByName(String name);

    Mono<Boolean> existsByName(String name);
}
