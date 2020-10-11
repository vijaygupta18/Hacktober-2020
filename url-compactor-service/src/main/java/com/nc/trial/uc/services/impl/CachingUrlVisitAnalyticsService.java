package com.nc.trial.uc.services.impl;

import com.nc.trial.uc.domain.UrlVisitSummary;
import com.nc.trial.uc.services.UrlVisitAnalyticsService;
import com.nc.trial.uc.services.dao.UrlVisitSummaryDao;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CachingUrlVisitAnalyticsService implements UrlVisitAnalyticsService {

    private final UrlVisitSummaryDao urlVisitSummaryDao;

    public CachingUrlVisitAnalyticsService(UrlVisitSummaryDao urlVisitSummaryDao) {
        this.urlVisitSummaryDao = urlVisitSummaryDao;
    }

    @Override
    @Cacheable(cacheNames = {"topVisited"})
    public Collection<UrlVisitSummary> topVisited(int count) {
        return urlVisitSummaryDao.getTopVisitedUrls(count);
    }
}
