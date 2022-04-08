package com.dtw.ahmetflix.model;
public class Users {
    String profilepic, adi, userId, rutbe,istek,durum;
    public Users(String profilResmi, String adi, String userId, String rutbe,String istek,String durum) {
        this.profilepic = profilResmi;
        this.adi = adi;
        this.userId = userId;
        this.rutbe = rutbe;
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

    public String getRutbe() {
        return rutbe;
    }

    public void setRutbe(String rutbe) {
        this.rutbe = rutbe;
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