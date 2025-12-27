package com.akilli.kutuphane.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Kitaplar")
public class Kitap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KitapID") // SQL'deki gerçek sütun ismi
    private Integer kitapID;

    @Column(name = "KitapAdi", nullable = false) // SQL tablandaki isim 'KitapAdi' ise burayı eşle
    private String ad;

    @Column(name = "Stok")
    private Integer stok;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "YazarID") // SQL'deki Yabancı Anahtar ismi
    @JsonIgnoreProperties("kitaplar") // 500 HATASINI ÇÖZEN SATIR: Yazarı getirirken onun kitap listesine girme!
    private Yazar yazar;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "KategoriID") // SQL'deki Yabancı Anahtar ismi
    @JsonIgnoreProperties("kitaplar") // 500 HATASINI ÇÖZEN SATIR: Kategoriyi getirirken döngüye girme!
    private Kategori kategori;

    // Hibernate için boş constructor
    public Kitap() {}

    // GETTER VE SETTER METOTLARI
    public Integer getKitapID() { return kitapID; }
    public void setKitapID(Integer kitapID) { this.kitapID = kitapID; }

    public String getAd() { return ad; }
    public void setAd(String ad) { this.ad = ad; }

    public Integer getStok() { return stok; }
    public void setStok(Integer stok) { this.stok = stok; }

    public Yazar getYazar() { return yazar; }
    public void setYazar(Yazar yazar) { this.yazar = yazar; }

    public Kategori getKategori() { return kategori; }
    public void setKategori(Kategori kategori) { this.kategori = kategori; }
}