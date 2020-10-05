package com.sugata.mycredibleinfo.LoginClasses;

public class Data {
    private String id;

    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
