package com.akilli.kutuphane.repository;

import com.akilli.kutuphane.entity.Ceza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.List;

public interface CezaRepository extends JpaRepository<Ceza, Integer> {

    // Belirli bir ödünç işlemine ait cezayı bulur
    Ceza findByOdunc_OduncID(Integer oduncID);

    // Kullanıcının ödemesi gereken TOPLAM cezayı hesaplar
    // Dashboard'daki 0.00 TL yazısını güncelleyen asıl sorgu budur.
    @Query("SELECT SUM(c.tutar) FROM Ceza c WHERE c.odunc.kullanici.kullaniciID = :kullaniciID")
    BigDecimal sumTutarByKullaniciId(@Param("kullaniciID") Integer kullaniciID);

    // Kullanıcının tüm ceza geçmişini liste olarak getirir
    List<Ceza> findAllByOdunc_Kullanici_KullaniciID(Integer kullaniciID);
}