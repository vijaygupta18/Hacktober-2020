package com.nc.trial.uc.services.dao;

import com.nc.trial.uc.domain.UrlVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

/**
 * DAO for persisting and providing all visit events of any particular shortened url (see {@link UrlVisit})
 */
public interface UrlVisitDao extends JpaRepository<UrlVisit, Long> {

    String Q_GET_ALL_VISITS = "select uv from UrlVisit uv join uv.urlMapping";

    @Query(Q_GET_ALL_VISITS)
    @Deprecated
    Collection<UrlVisit> getAllVisits();
}
