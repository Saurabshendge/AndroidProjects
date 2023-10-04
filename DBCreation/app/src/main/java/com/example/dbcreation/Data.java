package com.example.dbcreation;

public class Data {
    private String name;
    private String uname;
    private String pas;

    public Data(String name, String uname, String pas) {
        this.name = name;
        this.uname = uname;
        this.pas = pas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }
}
