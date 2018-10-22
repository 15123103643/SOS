package com.cqeec.by.sos;

import org.litepal.crud.LitePalSupport;
import org.litepal.exceptions.DataSupportException;

public class UserDB extends LitePalSupport {
    private int id;
    private String u_name;
    private String u_sfzh;
    private String u_jtdz;
    private String u_xx;
    private String u_gmfy;
    private String u_zdy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_sfzh() {
        return u_sfzh;
    }

    public void setU_sfzh(String u_sfzh) {
        this.u_sfzh = u_sfzh;
    }

    public String getU_jtdz() {
        return u_jtdz;
    }

    public void setU_jtdz(String u_jtdz) {
        this.u_jtdz = u_jtdz;
    }

    public String getU_xx() {
        return u_xx;
    }

    public void setU_xx(String u_xx) {
        this.u_xx = u_xx;
    }

    public String getU_gmfy() {
        return u_gmfy;
    }

    public void setU_gmfy(String u_gmfy) {
        this.u_gmfy = u_gmfy;
    }

    public String getU_zdy() {
        return u_zdy;
    }

    public void setU_zdy(String u_zdy) {
        this.u_zdy = u_zdy;
    }

}