package com.ebntr.cinebilet.models;

public class Film {
    private String baslik;
    private String aciklama;
    private int resimId;

    public Film(String baslik, String aciklama, int resimId) {
        this.baslik = baslik;
        this.aciklama = aciklama;
        this.resimId = resimId;
    }

    public String getBaslik() {
        return baslik;
    }

    public String getAciklama() {
        return aciklama;
    }

    public int getResimId() {
        return resimId;
    }
}
