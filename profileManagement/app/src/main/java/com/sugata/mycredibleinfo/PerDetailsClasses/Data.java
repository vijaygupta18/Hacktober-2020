package com.sugata.mycredibleinfo.PerDetailsClasses;

public class Data {
    private String skills;

    private String image;

    private String uid;

    private String mobile_no;

    private String name;

    private String links;

    private String location;

    private String id;

    private String email;

    public String getSkills ()
    {
        return skills;
    }

    public void setSkills (String skills)
    {
        this.skills = skills;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getUid ()
    {
        return uid;
    }

    public void setUid (String uid)
    {
        this.uid = uid;
    }

    public String getMobile_no ()
    {
        return mobile_no;
    }

    public void setMobile_no (String mobile_no)
    {
        this.mobile_no = mobile_no;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getLinks ()
    {
        return links;
    }

    public void setLinks (String links)
    {
        this.links = links;
    }

    public String getLocation ()
    {
        return location;
    }

    public void setLocation (String location)
    {
        this.location = location;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Data{" +
                "skills='" + skills + '\'' +
                ", image='" + image + '\'' +
                ", uid='" + uid + '\'' +
                ", mobile_no='" + mobile_no + '\'' +
                ", name='" + name + '\'' +
                ", links='" + links + '\'' +
                ", location='" + location + '\'' +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
