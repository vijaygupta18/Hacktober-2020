package com.sugata.mycredibleinfo.EduDetailsClasses;

public class Data {

    private String uid;

    private String image_path;

    private String start_year;

    private String degree;

    private String organisation;

    private String location;

    private String id;

    private String end_year;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getStart_year() {
        return start_year;
    }

    public void setStart_year(String start_year) {
        this.start_year = start_year;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnd_year() {
        return end_year;
    }

    public void setEnd_year(String end_year) {
        this.end_year = end_year;
    }

    @Override
    public String toString() {
        return "Data{" +
                "uid='" + uid + '\'' +
                ", image_path='" + image_path + '\'' +
                ", start_year='" + start_year + '\'' +
                ", degree='" + degree + '\'' +
                ", organisation='" + organisation + '\'' +
                ", location='" + location + '\'' +
                ", id='" + id + '\'' +
                ", end_year='" + end_year + '\'' +
                '}';
    }
}
