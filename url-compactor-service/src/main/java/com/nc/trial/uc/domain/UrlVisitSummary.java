package com.nc.trial.uc.domain;

import javax.persistence.*;

@SqlResultSetMapping(
        name = "UrlVisitSummaryResult",
        classes = {
                @ConstructorResult(
                        targetClass = com.nc.trial.uc.domain.UrlVisitSummary.class,
                        columns = {
                                @ColumnResult(name = "visit_count", type=Integer.class),
                                @ColumnResult(name = "url"),
                                @ColumnResult(name = "pseudo_hash")
                        }
                )
        }
)
@Entity
public class UrlVisitSummary implements ShortLinkAware {

    @Id
    private long id;

    private int visitCount;
    private String url;
    private String pseudoHash;

    private String shortLink;

    public UrlVisitSummary(int visitCount, String url, String pseudoHash) {
        this.visitCount = visitCount;
        this.url = url;
        this.pseudoHash = pseudoHash;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public String getUrl() {
        return url;
    }

    public String getPseudoHash() {
        return pseudoHash;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }
}
