package com.micronaut.repository;

import com.micronaut.domain.PingInfo;

import javax.validation.constraints.NotBlank;

public interface AadarshaRepository {

    PingInfo save(@NotBlank PingInfo pingInfo);
}
