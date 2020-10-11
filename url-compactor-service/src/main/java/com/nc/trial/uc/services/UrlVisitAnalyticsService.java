package com.nc.trial.uc.services;

import com.nc.trial.uc.domain.UrlVisitSummary;

import java.util.Collection;

public interface UrlVisitAnalyticsService {
    Collection<UrlVisitSummary> topVisited(int count);
}
