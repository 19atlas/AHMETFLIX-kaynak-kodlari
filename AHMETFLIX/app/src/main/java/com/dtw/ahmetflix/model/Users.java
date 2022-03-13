package com.dtw.ahmetflix.model;
public class Users {
    String profilepic, adi, userId, rol,istek,durum;
    public Users(String profilResmi, String adi, String userId, String rol,String istek,String durum) {
        this.profilepic = profilResmi;
        this.adi = adi;
        this.userId = userId;
        this.rol = rol;
        this.istek = istek;
        this.durum = durum;
    }
    //*giris seysi//*
    public Users(){}
    public Users(String adi) {
        this.adi = adi;
    }
    //*///////giris/////////////////*
    public String getprofilepic() {
        return profilepic;
    }

    public void setprofilepic(String profilepic) {
        this.profilepic = profilepic;
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

    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public String getIstek() {
        return istek;
    }
    public void setIstek(String istek) {
        this.istek = istek;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }
}