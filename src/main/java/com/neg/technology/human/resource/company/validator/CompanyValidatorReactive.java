package com.neg.technology.human.resource.company.validator;

import com.neg.technology.human.resource.company.model.request.CreateCompanyRequest;
import com.neg.technology.human.resource.company.model.request.UpdateCompanyRequest;
import com.neg.technology.human.resource.company.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

@Service
public class CompanyValidatorReactive {

    private final CompanyRepository companyRepository;

    public CompanyValidatorReactive(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Mono<Void> validateCreate(CreateCompanyRequest dto) {
        if (!StringUtils.hasText(dto.getName())) {
            return Mono.error(new IllegalArgumentException("Company name must not be empty"));
        }
        return companyRepository.existsByName(dto.getName())
                .flatMap(exists -> exists
                        ? Mono.error(new IllegalArgumentException("Company name already exists"))
                        : Mono.empty()
                );
    }

    public Mono<Void> validateUpdate(UpdateCompanyRequest dto) {
        if (!StringUtils.hasText(dto.getName())) {
            return Mono.error(new IllegalArgumentException("Company name must not be empty"));
        }
        return companyRepository.findByName(dto.getName())
                .flatMap(existing -> {
                    if (!existing.getId().equals(dto.getId())) {
                        return Mono.error(new IllegalArgumentException("Company name already exists"));
                    }
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.empty());
    }
}
