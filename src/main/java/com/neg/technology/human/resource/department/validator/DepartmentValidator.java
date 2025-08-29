package com.neg.technology.human.resource.department.validator;

import com.neg.technology.human.resource.department.model.request.CreateDepartmentRequest;
import com.neg.technology.human.resource.department.model.request.UpdateDepartmentRequest;
import com.neg.technology.human.resource.department.service.DepartmentService;
import com.neg.technology.human.resource.utility.module.entity.request.NameRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

@Service
public class DepartmentValidator {

    private final DepartmentService departmentService;

    public DepartmentValidator(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public Mono<Void> validateCreate(CreateDepartmentRequest dto) {
        if (!StringUtils.hasText(dto.getName())) {
            return Mono.error(new IllegalArgumentException("Department name must not be empty"));
        }

        NameRequest nameRequest = new NameRequest();
        nameRequest.setName(dto.getName());

        return departmentService.existsByName(nameRequest)
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new IllegalArgumentException("Department name already exists"));
                    }
                    return Mono.empty();
                });
    }

    public Mono<Void> validateUpdate(UpdateDepartmentRequest dto) {
        if (!StringUtils.hasText(dto.getName())) {
            return Mono.error(new IllegalArgumentException("Department name must not be empty"));
        }

        NameRequest nameRequest = new NameRequest();
        nameRequest.setName(dto.getName());

        return departmentService.existsByName(nameRequest)
                .flatMap(exists -> {
                    if (exists) {
                        return departmentService.getDepartmentByName(nameRequest)
                                .flatMap(existing -> {
                                    if (!existing.getId().equals(dto.getId())) {
                                        return Mono.error(new IllegalArgumentException("Department name already exists"));
                                    }
                                    return Mono.empty();
                                });
                    }
                    return Mono.empty();
                });
    }
}
