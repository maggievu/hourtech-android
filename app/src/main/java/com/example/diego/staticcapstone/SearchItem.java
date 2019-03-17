package com.example.diego.staticcapstone;

public class SearchItem {

    private int tech_pic;
    private String tech_name, tech_role, tech_desc;

    public SearchItem(int pic, String name, String role, String desc) {
        tech_pic = pic;
        tech_name = name;
        tech_role = role;
        tech_desc = desc;


    }
public void changeText1(String text){
        tech_name = text;
}
    public int getTech_pic() {
        return tech_pic;
    }

    public String getTech_name() {
        return tech_name;
    }

    public String getTech_role() {
        return tech_role;
    }

    public String getTech_desc() {
        return tech_desc;
    }

    /*
    public void setTech_role(String tech_role) {
        this.tech_role = tech_role;
    }
    public void setTech_name(String tech_name) {
        this.tech_name = tech_name;
    }

    public void setTech_pic(int tech_pic) {
        this.tech_pic = tech_pic;
    }
    public void setTech_desc(String tech_desc) {
        this.tech_desc = tech_desc;
    }
    */
}
