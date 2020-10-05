package com.sugata.mycredibleinfo.ProfDetailsClasses;

public class Data {

    private String end_date;

    private String uid;

    private String organisation;

    private String designation;

    private String id;

    private String start_date;

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    @Override
    public String toString() {
        return "Data{" +
                "end_date='" + end_date + '\'' +
                ", uid='" + uid + '\'' +
                ", organisation='" + organisation + '\'' +
                ", designation='" + designation + '\'' +
                ", id='" + id + '\'' +
                ", start_date='" + start_date + '\'' +
                '}';
    }
}
