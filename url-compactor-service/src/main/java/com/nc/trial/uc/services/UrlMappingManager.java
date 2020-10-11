package com.nc.trial.uc.services;

import com.nc.trial.uc.domain.UrlMapping;
import com.nc.trial.uc.exceptions.urlmapping.CreationException;

public interface UrlMappingManager {
    UrlMapping findByPseudoHash(String pseudoHash);
    UrlMapping create(String originalUrl) throws CreationException;
    UrlMapping visit(String pseudoHash);
}
