package com.ebntr.cinebilet.models;

//tüm koltukların depişkenleri ve durum bilgisi burada tutulur

public class Koltuk {
    private boolean rezerveMi;
    private boolean seciliMi;


    public Koltuk(boolean rezerveMi) {
        this.rezerveMi = rezerveMi;
        this.seciliMi = false;
    }


    public boolean rezerveMi() {
        return rezerveMi;
    }


    public void rezerveEt(boolean rezerve) {
        this.rezerveMi = rezerve;
    }


    public boolean seciliMi() {
        return seciliMi;
    }


    public void seciliYap(boolean secili) {
        this.seciliMi = secili;
    }


    public void secimiDegistir() {
        this.seciliMi = !this.seciliMi;
    }
}
