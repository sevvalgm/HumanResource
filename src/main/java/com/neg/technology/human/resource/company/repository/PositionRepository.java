package com.neg.technology.human.resource.company.repository;

import com.neg.technology.human.resource.company.model.entity.Position;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Repository
public interface PositionRepository extends ReactiveCrudRepository<Position, Long> {

    Mono<Position> findByTitle(String title);

    Mono<Boolean> existsByTitle(String title);

    Flux<Position> findByBaseSalaryGreaterThanEqual(BigDecimal salary);
}
