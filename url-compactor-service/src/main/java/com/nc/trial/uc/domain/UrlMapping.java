package com.nc.trial.uc.domain;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="url_mapping",
        /** TODO: Maybe this index declaration is redundant due to the fact that most of production RDBMS create index for column which has been declared unique**/
        indexes = {@Index(name = "url_idx", columnList = "url"),
                @Index(name = "pseudo_hash_idx",
                columnList = "pseudo_hash")})
public class UrlMapping implements ShortLinkAware {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "url_mapping_id")
    private long urlMappingId;

    @Column(name="url", length=4096)
    private String url;

    @Column(name="pseudo_hash", unique=true)
    private String pseudoHash;

    private String shortLink;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "urlMapping")
    private List<UrlVisit> urlVisits = new LinkedList();

    public UrlMapping() {
    }

    public UrlMapping(String url, String pseudoHash) {
        this.url = url;
        this.pseudoHash = pseudoHash;
    }

    public String getUrl() {
        return url;
    }

    public String getPseudoHash() {
        return pseudoHash;
    }

    public long getUrlMappingId() {
        return urlMappingId;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }
}
