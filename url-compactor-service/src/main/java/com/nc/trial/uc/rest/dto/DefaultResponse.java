package com.nc.trial.uc.rest.dto;

public class DefaultResponse {
    private boolean success = true;
    private String error;

    public DefaultResponse(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }
}
