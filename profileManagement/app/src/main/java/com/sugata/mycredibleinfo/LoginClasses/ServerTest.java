package com.sugata.mycredibleinfo.LoginClasses;

public class ServerTest {

    private String server_name;
    private String method;
    private String port;
    private String time;
    private String url;
    private String status;

    public String getServer_name() {
        return server_name;
    }

    public void setServer_name(String server_name) {
        this.server_name = server_name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ServerTest{" +
                "server_name='" + server_name + '\'' +
                ", method='" + method + '\'' +
                ", port='" + port + '\'' +
                ", time='" + time + '\'' +
                ", url='" + url + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
