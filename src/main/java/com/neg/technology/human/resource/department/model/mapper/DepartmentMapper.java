package com.neg.technology.human.resource.department.model.mapper;

import com.neg.technology.human.resource.department.model.response.DepartmentResponse;
import com.neg.technology.human.resource.department.model.request.CreateDepartmentRequest;
import com.neg.technology.human.resource.department.model.request.UpdateDepartmentRequest;
import com.neg.technology.human.resource.department.model.entity.Department;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

public class DepartmentMapper {

    private DepartmentMapper() {}

    public static Mono<DepartmentResponse> toDTO(Department department) {
        if (department == null) return Mono.empty();
        return Mono.just(new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getLocation()
        ));
    }

    public static Mono<Department> toEntity(@Valid CreateDepartmentRequest dto) {
        if (dto == null) return Mono.empty();
        return Mono.just(
                Department.builder()
                        .name(dto.getName())
                        .location(dto.getLocation())
                        .build()
        );
    }

    public static Mono<Department> toEntity(@Valid UpdateDepartmentRequest dto) {
        if (dto == null) return Mono.empty();
        return Mono.just(
                Department.builder()
                        .id(dto.getId())
                        .name(dto.getName())
                        .location(dto.getLocation())
                        .build()
        );
    }

    public static Mono<Department> updateEntity(Department department, UpdateDepartmentRequest dto) {
        if (department == null || dto == null) return Mono.justOrEmpty(department);
        if (dto.getName() != null) department.setName(dto.getName());
        if (dto.getLocation() != null) department.setLocation(dto.getLocation());
        return Mono.just(department);
    }
}
