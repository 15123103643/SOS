package com.cqeec.by.sos;

import org.litepal.crud.LitePalSupport;

public class Userjjlxr extends LitePalSupport {
    private String U_name;
    private String U_phone;
    private String U_email;

    public String getU_name() {
        return U_name;
    }

    public void setU_name(String u_name) {
        U_name = u_name;
    }

    public String getU_phone() {
        return U_phone;
    }

    public void setU_phone(String u_phone) {
        U_phone = u_phone;
    }

    public String getU_email() {
        return U_email;
    }

    public void setU_email(String u_email) {
        U_email = u_email;
    }
}
