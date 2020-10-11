package com.nc.trial.uc.rest.dto;

public class CompactResultResponse extends DefaultResponse {
    private String shortLink;

    public CompactResultResponse(boolean success, String error) {
        super(success, error);
    }

    public CompactResultResponse(String shortLink) {
        super(true, null);
        this.shortLink = shortLink;
    }

    public String getShortLink() {
        return shortLink;
    }
}
