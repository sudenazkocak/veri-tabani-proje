package com.akilli.kutuphane.controller;

import com.akilli.kutuphane.entity.Odunc;
import com.akilli.kutuphane.service.OduncService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal; // Ceza hesaplamaları için şart
import java.util.List;

@RestController
@RequestMapping("/api/odunc")
@CrossOrigin("*") // Frontend (localhost:9091) bağlantısı için şart
public class OduncController {

    private final OduncService oduncService;

    public OduncController(OduncService oduncService) {
        this.oduncService = oduncService;
    }

    /**
     * Ödünç alma endpoint'i.
     * image_4a51fe.png'deki 500 hatasını önlemek için try-catch eklendi.
     */
    @PostMapping("/al")
    public ResponseEntity<?> oduncAl(@RequestBody Odunc odunc) {
        try {
            // Servisteki stok kontrolü ve LocalDateTime kaydını tetikler
            Odunc kaydedilenOdunc = oduncService.oduncAl(odunc);
            return ResponseEntity.ok(kaydedilenOdunc);
        } catch (Exception e) {
            // Hata detayını frontend alert kutusuna gönderir
            return ResponseEntity.internalServerError().body("Hata: " + e.getMessage());
        }
    }

    /**
     * Yan panel (Kitaplarım) için kullanıcının elindeki aktif ödünçleri getirir.
     */
    @GetMapping("/kullanici/{id}")
    public ResponseEntity<List<Odunc>> getKullaniciOduncleri(@PathVariable Integer id) {
        // Repository'deki 'IsNull' filtresini kullanarak iade edilmemişleri getirir
        List<Odunc> liste = oduncService.kullaniciyaGoreGetir(id);
        return ResponseEntity.ok(liste);
    }

    /**
     * Kitap iade etme endpoint'i.
     * İade anında ceza hesaplamasını tetikler.
     */
    @PostMapping("/iade/{id}")
    public ResponseEntity<?> iadeEt(@PathVariable Integer id) {
        try {
            Odunc iadeEdilen = oduncService.iadeEt(id);
            return ResponseEntity.ok(iadeEdilen);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("İade Hatası: " + e.getMessage());
        }
    }

    /**
     * SİTEDEKİ "CEZA DURUMU" İÇİN KRİTİK ENDPOINT.
     * Kullanıcının tüm geçmiş cezalarını toplayıp döner.
     */
    @GetMapping("/ceza/toplam/{id}")
    public ResponseEntity<BigDecimal> getToplamCeza(@PathVariable Integer id) {
        // OduncService içinde yazdığımız toplama metodunu çağırır
        BigDecimal toplam = oduncService.kullaniciToplamCeza(id);
        return ResponseEntity.ok(toplam);
    }
}