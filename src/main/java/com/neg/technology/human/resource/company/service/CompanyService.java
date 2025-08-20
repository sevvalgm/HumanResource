package com.neg.technology.human.resource.company.service;

import com.neg.technology.human.resource.company.model.request.CreateCompanyRequest;
import com.neg.technology.human.resource.company.model.request.UpdateCompanyRequest;
import com.neg.technology.human.resource.company.model.response.CompanyResponse;
import com.neg.technology.human.resource.company.model.response.CompanyResponseList;
import com.neg.technology.human.resource.company.model.request.CompanyIdRequest;
import com.neg.technology.human.resource.utility.module.entity.request.NameRequest;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

public interface CompanyService {

    Mono<CompanyResponse> createCompany(CreateCompanyRequest request);

    Mono<CompanyResponse> updateCompany(UpdateCompanyRequest request);

    Mono<Void> deleteCompany(CompanyIdRequest request);

    Flux<CompanyResponse> getAllCompanies();

    Mono<CompanyResponse> getCompanyById(CompanyIdRequest request);

    Mono<CompanyResponse> getCompanyByName(NameRequest request);

    Mono<Boolean> existsByName(NameRequest request);
}
