package com.nc.trial.uc;

import com.nc.trial.uc.domain.UrlMapping;
import com.nc.trial.uc.services.HashGenerator;
import com.nc.trial.uc.services.UrlMappingManager;
import com.nc.trial.uc.services.dao.UrlVisitDao;
import com.nc.trial.uc.services.impl.AlphanumericHashGenerator;
import com.nc.trial.uc.services.impl.RetryingUrlMappingManager;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseTest {
    protected static final String ORIGIN = "https://gist.github.com/subfuzion/08c5d85437d5d4f00e58";
    protected static final String PSEUDO_HASH = "JVCR";

    protected final HashGenerator hashGenerator = new AlphanumericHashGenerator();
    protected final InMemoryMappingDao urlMappingDao = mock(InMemoryMappingDao.class);
    protected final UrlVisitDao urlVisitDao = mock(UrlVisitDao.class);
    protected final UrlMappingManager urlMappingManager = new RetryingUrlMappingManager(urlMappingDao,
            urlVisitDao, hashGenerator);
    private final Map<String, UrlMapping> db = new HashMap<>();

    {
        when(urlMappingDao.save(any(UrlMapping.class))).thenCallRealMethod();
        when(urlMappingDao.findByPseudoHash(anyString())).thenCallRealMethod();
        when(urlMappingDao.findByUrl(anyString())).thenCallRealMethod();
        when(urlMappingDao.count()).thenCallRealMethod();
        when(urlMappingDao.getDb()).thenReturn(db);
    }
}
