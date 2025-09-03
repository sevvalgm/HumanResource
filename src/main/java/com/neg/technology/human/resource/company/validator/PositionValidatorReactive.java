package com.neg.technology.human.resource.company.validator;

import com.neg.technology.human.resource.company.model.request.CreatePositionRequest;
import com.neg.technology.human.resource.company.model.request.UpdatePositionRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

@Service
public class PositionValidatorReactive {

    public Mono<Void> validateCreate(CreatePositionRequest dto) {
        if (!StringUtils.hasText(dto.getTitle())) {
            return Mono.error(new IllegalArgumentException("Title must not be empty"));
        }
        if (dto.getBaseSalary() != null && dto.getBaseSalary().signum() == -1) {
            return Mono.error(new IllegalArgumentException("Base salary must be zero or positive"));
        }
        return Mono.empty();
    }

    public Mono<Void> validateUpdate(UpdatePositionRequest dto) {
        if (!StringUtils.hasText(dto.getTitle())) {
            return Mono.error(new IllegalArgumentException("Title must not be empty"));
        }
        if (dto.getBaseSalary() != null && dto.getBaseSalary().signum() == -1) {
            return Mono.error(new IllegalArgumentException("Base salary must be zero or positive"));
        }
        return Mono.empty();
    }
}
