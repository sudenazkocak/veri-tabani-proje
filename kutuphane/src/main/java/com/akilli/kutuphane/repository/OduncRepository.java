package com.akilli.kutuphane.repository;

import com.akilli.kutuphane.entity.Odunc;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OduncRepository extends JpaRepository<Odunc, Integer> {

    // Bir kullanıcının tüm ödünç geçmişini (iade edilenler dahil) getirir
    List<Odunc> findByKullanici_KullaniciID(Integer kullaniciID);

    // Kütüphane genelinde henüz iade edilmemiş (aktif) tüm kayıtları bulur
    List<Odunc> findByIadeTarihiIsNull();

    /**
     * Sağ taraftaki "Kitaplarım" panelinin ana motorudur.
     * Sadece belirli bir kullanıcının elinde tuttuğu (iade etmediği) kitapları getirir.
     * Metot ismi Entity'deki 'kullaniciID' ve 'iadeTarihi' alanlarıyla birebir uyumludur.
     */
    List<Odunc> findByKullanici_KullaniciIDAndIadeTarihiIsNull(Integer kullaniciID);
}