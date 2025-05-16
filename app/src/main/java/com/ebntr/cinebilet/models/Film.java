package com.ebntr.cinebilet.models;

//tüm filmlerin depişkenleri ve durum bilgisi burada tutulur
public class Film {
    private String baslik;
    private int resimId;


    public Film(String baslik, int resimId) {
        this.baslik = baslik;
        this.resimId = resimId;
    }


    public String getBaslik() {
        return baslik;
    }


    public int getResimId() {
        return resimId;
    }
}