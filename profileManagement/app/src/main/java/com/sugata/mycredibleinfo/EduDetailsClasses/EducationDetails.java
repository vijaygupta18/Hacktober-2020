package com.sugata.mycredibleinfo.EduDetailsClasses;

public class EducationDetails {

    private String organisation;

    private String degree;

    private String location;

    private String start_year;

    private String end_year;

    public EducationDetails() {
    }

    public EducationDetails(String start_year, String degree, String organisation, String location, String end_year) {
        this.start_year = start_year;
        this.degree = degree;
        this.organisation = organisation;
        this.location = location;
        this.end_year = end_year;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStart_year() {
        return start_year;
    }

    public void setStart_year(String start_year) {
        this.start_year = start_year;
    }

    public String getEnd_year() {
        return end_year;
    }

    public void setEnd_year(String end_year) {
        this.end_year = end_year;
    }

    @Override
    public String toString() {
        return "EducationDetails{" +
                "organisation='" + organisation + '\'' +
                ", degree='" + degree + '\'' +
                ", location='" + location + '\'' +
                ", start_year='" + start_year + '\'' +
                ", end_year='" + end_year + '\'' +
                '}';
    }
}
