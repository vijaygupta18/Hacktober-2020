package com.nc.trial.uc.services.dao;

import com.nc.trial.uc.domain.UrlVisitSummary;

import java.util.Collection;

/**
 * DAO for providing analytic entities: for example top visited urls (see {@link UrlVisitSummary})
 */
public interface UrlVisitSummaryDao {
    Collection<UrlVisitSummary> getTopVisitedUrls(int count);
}
