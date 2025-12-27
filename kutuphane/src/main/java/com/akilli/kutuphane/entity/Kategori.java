package com.akilli.kutuphane.entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Kategoriler")
public class Kategori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KategoriID") // SQL'deki büyük harf uyumu
    private Integer kategoriID;

    @Column(name = "KategoriAdi") // SQL'de 'KategoriAdi' demiştin
    private String ad;

    @OneToMany(mappedBy = "kategori")
    @JsonIgnore
    private List<Kitap> kitaplar;

    // Hibernate için boş constructor
    public Kategori() {}

    // GETTER VE SETTER METOTLARI
    public Integer getKategoriID() { 
        return kategoriID; 
    }

    public void setKategoriID(Integer kategoriID) { 
        this.kategoriID = kategoriID; 
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