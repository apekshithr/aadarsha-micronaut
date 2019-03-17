package com.micronaut.repository.impl;

import com.micronaut.domain.PingInfo;
import com.micronaut.repository.AadarshaRepository;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.runtime.ApplicationConfiguration;
import io.micronaut.spring.tx.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotBlank;

public class AadarshaRepositoryImpl implements AadarshaRepository {

    private static final Logger logger = LoggerFactory.getLogger(AadarshaRepositoryImpl.class);
    private final ApplicationConfiguration applicationConfiguration;
    @PersistenceContext
    private EntityManager entityManager;

    public AadarshaRepositoryImpl(@CurrentSession EntityManager entityManager,
                                  ApplicationConfiguration applicationConfiguration) {
        this.entityManager = entityManager;
        this.applicationConfiguration = applicationConfiguration;
    }

    @Override
    //TODO: Report the bug. Default parameters for @Transactional are expected to be entered
    @Transactional(readOnly = false, timeout = -1, propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public PingInfo save(@NotBlank PingInfo pingInfo) {
        entityManager.persist(pingInfo);
        logger.info("Successfully persisted pingInfo: {}", pingInfo.toString());
        return pingInfo;
    }
}
