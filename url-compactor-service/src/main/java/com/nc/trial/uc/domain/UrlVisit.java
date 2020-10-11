package com.nc.trial.uc.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="url_visit")
public class UrlVisit {

    @ManyToOne(fetch = FetchType.LAZY)
    private UrlMapping urlMapping;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = false, updatable = false)
    private long id;

    @Column(name="visited_at")
    private Date visitedAt;

    public UrlVisit() {
    }

    public UrlVisit(UrlMapping urlMapping, Date visitedAt) {
        this.urlMapping = urlMapping;
        this.visitedAt = visitedAt;
    }

    public Date getVisitedAt() {
        return visitedAt;
    }

    public UrlMapping getUrlMapping() {
        return urlMapping;
    }
}
