package com.sugata.mycredibleinfo.ProfDetailsClasses;

public class ProfessionalDetails {

    private String end_date;

    private String organisation;

    private String designation;

    private String start_date;

    public ProfessionalDetails() {
    }

    public ProfessionalDetails(String end_date, String organisation, String designation, String start_date) {
        this.end_date = end_date;
        this.organisation = organisation;
        this.designation = designation;
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
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

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    @Override
    public String toString() {
        return "ProfessionalDetails{" +
                "end_date='" + end_date + '\'' +
                ", organisation='" + organisation + '\'' +
                ", designation='" + designation + '\'' +
                ", start_date='" + start_date + '\'' +
                '}';
    }
}
