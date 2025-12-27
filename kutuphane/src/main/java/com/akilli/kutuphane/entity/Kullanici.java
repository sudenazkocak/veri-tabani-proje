package com.akilli.kutuphane.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Kullanicilar")
public class Kullanici {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
// Buraya dikkat: İsmi tam olarak SQL'deki gibi yazıyoruz
@Column(name = "kullaniciID") 
private Integer kullaniciID;

    @Column(name = "ad")
    private String ad;

    @Column(name = "soyad")
    private String soyad;

    @Column(name = "email")
    private String email;

    @Column(name = "sifre")
    private String sifre;

    @Column(name = "rol")
    private String rol;

    // Getter ve Setter Metotları
    public Integer getKullaniciID() { return kullaniciID; }
    public void setKullaniciID(Integer kullaniciID) { this.kullaniciID = kullaniciID; }

    public String getAd() { return ad; }
    public void setAd(String ad) { this.ad = ad; }

    public String getSoyad() { return soyad; }
    public void setSoyad(String soyad) { this.soyad = soyad; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSifre() { return sifre; }
    public void setSifre(String sifre) { this.sifre = sifre; }
    
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}