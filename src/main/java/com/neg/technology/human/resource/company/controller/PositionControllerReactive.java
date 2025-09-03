package com.neg.technology.human.resource.company.controller;

import com.neg.technology.human.resource.company.model.request.CreatePositionRequest;
import com.neg.technology.human.resource.company.model.request.UpdatePositionRequest;
import com.neg.technology.human.resource.company.model.response.PositionResponse;
import com.neg.technology.human.resource.company.service.PositionService;
import com.neg.technology.human.resource.utility.module.entity.request.IdRequest;
import com.neg.technology.human.resource.utility.module.entity.request.SalaryRequest;
import com.neg.technology.human.resource.utility.module.entity.request.TitleRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/positions")
@RequiredArgsConstructor
public class PositionControllerReactive {

    private final PositionService positionService;

    @Operation(summary = "Get all positions")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    @GetMapping("/getAll")
    public Flux<PositionResponse> getAllPositions() {
        return positionService.getAllPositions(); // Flux<PositionResponse>
    }

    @Operation(summary = "Get position by ID")
    @ApiResponse(responseCode = "200", description = "Position found")
    @PostMapping("/getById")
    public Mono<ResponseEntity<PositionResponse>> getPositionById(@Valid @RequestBody IdRequest request) {
        return positionService.getPositionById(request)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create new position")
    @ApiResponse(responseCode = "200", description = "Position successfully created")
    @PostMapping("/create")
    public Mono<ResponseEntity<PositionResponse>> createPosition(@Valid @RequestBody CreatePositionRequest request) {
        return positionService.createPosition(request)
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Update position")
    @ApiResponse(responseCode = "200", description = "Position successfully updated")
    @PostMapping("/update")
    public Mono<ResponseEntity<PositionResponse>> updatePosition(@Valid @RequestBody UpdatePositionRequest request) {
        return positionService.updatePosition(request)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete position")
    @ApiResponse(responseCode = "204", description = "Position successfully deleted")
    @PostMapping("/delete")
    public Mono<ResponseEntity<Void>> deletePosition(@Valid @RequestBody IdRequest request) {
        return positionService.deletePosition(request)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

    @Operation(summary = "Get position by title")
    @ApiResponse(responseCode = "200", description = "Position found")
    @PostMapping("/getByTitle")
    public Mono<ResponseEntity<PositionResponse>> getPositionByTitle(@Valid @RequestBody TitleRequest request) {
        return positionService.getPositionByTitle(request)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Check if position exists by title")
    @ApiResponse(responseCode = "200", description = "Boolean result indicating existence")
    @PostMapping("/existsByTitle")
    public Mono<ResponseEntity<Boolean>> existsByTitle(@Valid @RequestBody TitleRequest request) {
        return positionService.existsByTitle(request)
                .map(ResponseEntity::ok);
    }

    @Operation(summary = "Get positions by base salary")
    @ApiResponse(responseCode = "200", description = "List of matching positions")
    @PostMapping("/getByBaseSalary")
    public Flux<PositionResponse> getPositionsByBaseSalary(@Valid @RequestBody SalaryRequest request) {
        return positionService.getPositionsByBaseSalary(request); // Flux<PositionResponse>
    }
}
