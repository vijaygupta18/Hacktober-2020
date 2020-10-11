package com.nc.trial.uc;

import com.nc.trial.uc.domain.UrlMapping;
import com.nc.trial.uc.services.dao.UrlMappingDao;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Map;

/**
 * Mock implementation of {@link com.nc.trial.uc.services.dao.UrlMappingDao UrlMappingDao}
 * Relies on in-memory structure: Map<>
 */
public abstract class InMemoryMappingDao implements UrlMappingDao  {

    @Override
    public UrlMapping findByPseudoHash(String pseudoHash) {
        return getDb().get(pseudoHash);
    }

    abstract Map<String, UrlMapping> getDb();

    @Override
    public UrlMapping save(UrlMapping urlMapping) throws DataIntegrityViolationException {
        if (getDb().containsKey(urlMapping.getPseudoHash())) {
            throw new DataIntegrityViolationException("Such item already exists");
        }
        getDb().put(urlMapping.getPseudoHash(), urlMapping);
        return urlMapping;
    }

    public UrlMapping findByUrl(String url) {
        for(UrlMapping urlMapping: getDb().values()) {
            if (urlMapping.getUrl().equals(url)) {
                return urlMapping;
            }
        }
        return null;
    }

    public long count() {
        return getDb().size();
    }
}
