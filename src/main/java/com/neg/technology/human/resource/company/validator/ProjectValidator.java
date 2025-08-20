package com.neg.technology.human.resource.company.validator;

import com.neg.technology.human.resource.company.model.request.CreateProjectRequest;
import com.neg.technology.human.resource.company.model.request.UpdateProjectRequest;
import com.neg.technology.human.resource.company.service.ProjectServiceReactive;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

@Service
public class ProjectValidatorReactive {

    private final ProjectServiceReactive projectService;

    public ProjectValidatorReactive(ProjectServiceReactive projectService) {
        this.projectService = projectService;
    }

    public Mono<Void> validateCreate(CreateProjectRequest dto) {
        if (!StringUtils.hasText(dto.getName())) {
            return Mono.error(new IllegalArgumentException("Project name must not be empty"));
        }
        return projectService.existsByName(dto.getName())
                .flatMap(exists -> exists
                        ? Mono.error(new IllegalArgumentException("Project name already exists"))
                        : Mono.empty());
    }

    public Mono<Void> validateUpdate(UpdateProjectRequest dto, Long id) {
        if (!StringUtils.hasText(dto.getName())) {
            return Mono.error(new IllegalArgumentException("Project name must not be empty"));
        }
        return projectService.findByName(dto.getName())
                .flatMap(existing -> {
                    if (!existing.getId().equals(id)) {
                        return Mono.error(new IllegalArgumentException("Project name already exists"));
                    }
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.empty()); // Eğer proje yoksa sorun yok
    }
}
