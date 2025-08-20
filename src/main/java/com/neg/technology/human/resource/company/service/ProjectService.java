package com.neg.technology.human.resource.company.service;

import com.neg.technology.human.resource.company.model.entity.Project;
import com.neg.technology.human.resource.company.model.request.CreateProjectRequest;
import com.neg.technology.human.resource.company.model.request.ProjectIdRequest;
import com.neg.technology.human.resource.company.model.request.UpdateProjectRequest;
import com.neg.technology.human.resource.company.model.response.ProjectResponse;
import com.neg.technology.human.resource.company.model.response.ProjectResponseList;
import com.neg.technology.human.resource.utility.module.entity.request.NameRequest;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ProjectService {

    Flux<ProjectResponse> getAllProjects();

    Mono<ProjectResponse> getProjectById(ProjectIdRequest request);

    Mono<ProjectResponse> getProjectByName(NameRequest request);

    Mono<ProjectResponse> createProject(CreateProjectRequest request);

    Mono<ProjectResponse> updateProject(UpdateProjectRequest request);

    Mono<Void> deleteProject(ProjectIdRequest request);

    Mono<Boolean> existsByName(NameRequest request);

    Mono<Project> save(Project project);

    Mono<Project> findById(Long id);

    Mono<Project> findByName(String name);

    Flux<Project> findAll();

    Mono<Void> deleteById(Long id);

    Mono<Project> update(Long id, Project project);

    Mono<Boolean> existsById(Long id);
}
