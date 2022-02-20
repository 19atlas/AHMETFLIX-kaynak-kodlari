package com.dtw.ahmetflix.model;

public class Users {
    String profilResmi, adi, userId;

    public Users(String profilResmi, String adi, String userId) {
        this.profilResmi = profilResmi;
        this.adi = adi;
        this.userId = userId;
    }
    public Users(){}
    public Users(String adi) {
        this.profilResmi = profilResmi;
        this.adi = adi;
        this.userId = userId;
    }
    public String getProfilResmi() {
        return profilResmi;
    }

    public void setProfilResmi(String profilResmi) {
        this.profilResmi = profilResmi;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}