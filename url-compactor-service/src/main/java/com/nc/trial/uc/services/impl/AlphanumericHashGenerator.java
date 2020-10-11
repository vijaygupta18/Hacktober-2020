package com.nc.trial.uc.services.impl;

import com.nc.trial.uc.services.HashGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
/**
 * This class use
 */
public class AlphanumericHashGenerator implements HashGenerator {
    @Override
    public String generateHash() {
        return RandomStringUtils.randomAlphanumeric(7);
    }
}
