package com.sugata.mycredibleinfo.LoginClasses;

public class LoginSignupData {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoginSignupData{" +
                "data=" + data +
                '}';
    }
}
