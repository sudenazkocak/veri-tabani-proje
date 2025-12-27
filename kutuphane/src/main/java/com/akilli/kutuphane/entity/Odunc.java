package com.akilli.kutuphane.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime; // Dakika ve saniye hassasiyeti için zorunlu
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Odunc")
public class Odunc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OduncID")
    private Integer oduncID;

    @ManyToOne
    @JoinColumn(name = "KullaniciID")
    // Frontend'e giderken kullanıcı bilgilerini güvenli ve döngüsüz gönderir
    @JsonIgnoreProperties({"sifre", "rol", "oduncler"}) 
    private Kullanici kullanici;

    @ManyToOne
    @JoinColumn(name = "KitapID")
    @JsonIgnoreProperties({"yazar", "kategori"})
    private Kitap kitap;

    @Column(name = "OduncTarihi")
    private LocalDateTime oduncTarihi; // Ceza hesabı için LocalDateTime şart

    @Column(name = "IadeTarihi")
    private LocalDateTime iadeTarihi; // Kitap iade edilene kadar NULL kalabilir

    // JPA için gerekli boş constructor
    public Odunc() {}

    // GETTER VE SETTER METOTLARI
    
    public Integer getOduncID() {
        return oduncID;
    }

    public void setOduncID(Integer oduncID) {
        this.oduncID = oduncID;
    }

    public Kullanici getKullanici() {
        return kullanici;
    }

    public void setKullanici(Kullanici kullanici) {
        this.kullanici = kullanici;
    }

    public Kitap getKitap() {
        return kitap;
    }

    public void setKitap(Kitap kitap) {
        this.kitap = kitap;
    }

    public LocalDateTime getOduncTarihi() {
        return oduncTarihi;
    }

    // Bu metodun LocalDateTime parametresi alması 17 hatayı bitiren asıl kısımdır
    public void setOduncTarihi(LocalDateTime oduncTarihi) {
        this.oduncTarihi = oduncTarihi;
    }

    public LocalDateTime getIadeTarihi() {
        return iadeTarihi;
    }

    public void setIadeTarihi(LocalDateTime iadeTarihi) {
        this.iadeTarihi = iadeTarihi;
    }
}