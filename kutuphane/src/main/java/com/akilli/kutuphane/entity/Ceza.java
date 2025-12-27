package com.akilli.kutuphane.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Cezalar") // SQL'deki tablo adınla tam uyumlu
public class Ceza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CezaID") // SQL'deki sütun adı
    private Integer cezaID;

    @Column(name = "GunSayisi") // SQL'deki sütun adı
    private Integer gunSayisi;

    // Terminaldeki 'Invalid column name CezaTutari' hatasını bitirmek için
    // SQL'deki gerçek sütun adı olan 'Tutar' ismini buraya veriyoruz
    @Column(name = "Tutar", precision = 10, scale = 2) 
    private BigDecimal tutar;

    @OneToOne
    @JoinColumn(name = "OduncID") // SQL'deki yabancı anahtar adı
    private Odunc odunc;

    // JPA için şart olan boş constructor
    public Ceza() {}

    // Getter ve Setter Metotları
    public Integer getCezaID() {
        return cezaID;
    }

    public void setCezaID(Integer cezaID) {
        this.cezaID = cezaID;
    }

    public Integer getGunSayisi() {
        return gunSayisi;
    }

    public void setGunSayisi(Integer gunSayisi) {
        this.gunSayisi = gunSayisi;
    }

    public BigDecimal getTutar() {
        return tutar;
    }

    public void setTutar(BigDecimal tutar) {
        this.tutar = tutar;
    }

    public Odunc getOdunc() {
        return odunc;
    }

    public void setOdunc(Odunc odunc) {
        this.odunc = odunc;
    }
}