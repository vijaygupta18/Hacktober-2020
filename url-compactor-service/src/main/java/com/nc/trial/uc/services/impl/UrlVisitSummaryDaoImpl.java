package com.nc.trial.uc.services.impl;

import com.nc.trial.uc.domain.UrlVisitSummary;
import com.nc.trial.uc.services.dao.UrlVisitSummaryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

import static com.nc.trial.uc.util.UrlUtils.enrichWithShortLink;

@Component
public class UrlVisitSummaryDaoImpl implements UrlVisitSummaryDao {

    @Autowired
    private EntityManager entityManager;

    private @Value("${base.url}") String baseUrl = "http://localhost";
    private @Value("${server.port}") int serverPort = 9090;

    String Q_GET_TOP_N_VISITED = "select count(*) as visit_count, url, pseudo_hash\n" +
            "  FROM url_visit AS uv\n" +
            "    JOIN url_mapping\n" +
            "  ON uv.url_mapping_url_mapping_id = url_mapping.url_mapping_id\n" +
            "  GROUP BY uv.url_mapping_url_mapping_id\n" +
            "  ORDER BY 1 DESC LIMIT %d";

    @Override
    public Collection<UrlVisitSummary> getTopVisitedUrls(int count) {
        Query q = entityManager.createNativeQuery(String.format(Q_GET_TOP_N_VISITED, count), "UrlVisitSummaryResult");
        List<UrlVisitSummary> urlVisitSummaries = q.getResultList();
        urlVisitSummaries.forEach((summary) -> enrichWithShortLink(summary, baseUrl, serverPort));
        return urlVisitSummaries;
    }
}
