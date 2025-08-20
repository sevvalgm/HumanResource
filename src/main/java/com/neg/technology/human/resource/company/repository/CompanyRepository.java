package com.neg.technology.human.resource.company.repository;

import com.neg.technology.human.resource.company.model.entity.Company;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CompanyRepository extends ReactiveCrudRepository<Company, Long> {

    Mono<Company> findByName(String name);

    Mono<Boolean> existsByName(String name);
}
