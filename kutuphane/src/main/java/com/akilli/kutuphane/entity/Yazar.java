package com.akilli.kutuphane.entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Yazarlar")
public class Yazar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "YazarID") // SQL'deki büyük harf uyumu
    private Integer yazarID;

    @Column(name = "YazarAdi") // SQL'de 'YazarAdi' demiştin
    private String ad;

    @OneToMany(mappedBy = "yazar")
    @JsonIgnore
    private List<Kitap> kitaplar;

    // Hibernate için boş constructor
    public Yazar() {}

    // GETTER VE SETTER METOTLARI
    public Integer getYazarID() { 
        return yazarID; 
    }

    public void setYazarID(Integer yazarID) { 
        this.yazarID = yazarID; 
    }

    public String getAd() { 
        return ad; 
    }

    public void setAd(String ad) { 
        this.ad = ad; 
    }

    public List<Kitap> getKitaplar() {
        return kitaplar;
    }

    public void setKitaplar(List<Kitap> kitaplar) {
        this.kitaplar = kitaplar;
    }
}