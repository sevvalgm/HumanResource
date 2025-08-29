package com.neg.technology.human.resource.department.service;

import com.neg.technology.human.resource.department.model.entity.Department;
import com.neg.technology.human.resource.department.model.request.CreateDepartmentRequest;
import com.neg.technology.human.resource.department.model.request.UpdateDepartmentRequest;
import com.neg.technology.human.resource.department.model.response.DepartmentResponse;
import com.neg.technology.human.resource.department.model.response.DepartmentResponseList;
import com.neg.technology.human.resource.department.repository.DepartmentRepository;
import com.neg.technology.human.resource.exception.ResourceNotFoundException;
import com.neg.technology.human.resource.utility.Logger;
import com.neg.technology.human.resource.utility.module.entity.request.IdRequest;
import com.neg.technology.human.resource.utility.module.entity.request.NameRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    public static final String MESSAGE = "Department";
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Mono<DepartmentResponse> createDepartment(CreateDepartmentRequest request) {
        Department department = Department.builder()
                .name(request.getName())
                .location(request.getLocation())
                .build();

        return departmentRepository.save(department)
                .doOnSuccess(saved -> Logger.logCreated(Department.class, saved.getId(), saved.getName()))
                .map(this::toResponse);
    }

    @Override
    public Mono<DepartmentResponse> updateDepartment(UpdateDepartmentRequest request) {
        return departmentRepository.findById(request.getId())
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(MESSAGE, request.getId())))
                .flatMap(existing -> {
                    existing.setName(request.getName());
                    existing.setLocation(request.getLocation());
                    return departmentRepository.save(existing)
                            .doOnSuccess(updated -> Logger.logUpdated(Department.class, updated.getId(), updated.getName()));
                })
                .map(this::toResponse);
    }

    @Override
    public Mono<Void> deleteDepartment(IdRequest request) {
        return departmentRepository.existsById(request.getId())
                .flatMap(exists -> {
                    if (!exists) return Mono.error(new ResourceNotFoundException(MESSAGE, request.getId()));
                    return departmentRepository.deleteById(request.getId())
                            .doOnSuccess(unused -> Logger.logDeleted(Department.class, request.getId()));
                });
    }

    @Override
    public Mono<DepartmentResponse> getDepartmentById(IdRequest request) {
        return departmentRepository.findById(request.getId())
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(MESSAGE, request.getId())))
                .map(this::toResponse);
    }

    @Override
    public Mono<DepartmentResponse> getDepartmentByName(NameRequest request) {
        return departmentRepository.findByName(request.getName())
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(MESSAGE, request.getName())))
                .map(this::toResponse);
    }

    @Override
    public Mono<Boolean> existsByName(NameRequest request) {
        return departmentRepository.existsByName(request.getName());
    }

    @Override
    public Mono<DepartmentResponseList> getAllDepartments() {
        return departmentRepository.findAll()
                .map(this::toResponse)
                .collectList()
                .map(DepartmentResponseList::new);
    }

    // ----------------- UTILITY -----------------
    private DepartmentResponse toResponse(Department department) {
        return DepartmentResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .location(department.getLocation())
                .build();
    }
}
