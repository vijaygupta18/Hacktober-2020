package com.sugata.mycredibleinfo.ProfileImgClasses;

//map the server response if the upload is successful or not
public class StatusMessage {
    private String status_message;

    public String getStatus_message ()
    {
        return status_message;
    }

    public void setStatus_message (String status_message)
    {
        this.status_message = status_message;
    }

    @Override
    public String toString() {
        return "StatusMessage{" +
                "status_message='" + status_message + '\'' +
                '}';
    }
}
