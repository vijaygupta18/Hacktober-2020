package com.sugata.mycredibleinfo.ProfileImgClasses;

public class Photo {
    private String uid;

    private String photo;

    public Photo(String uid, String photo){
        this.uid = uid;
        this.photo = photo;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "uid='" + uid + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
