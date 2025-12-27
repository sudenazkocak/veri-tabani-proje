package com.akilli.kutuphane.controller;

import com.akilli.kutuphane.entity.Kategori;
import com.akilli.kutuphane.service.KategoriService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/kategoriler")
@CrossOrigin(origins = "*") // Frontend (JS) erişimi için kesinlikle gerekli
public class KategoriController {

    private final KategoriService service;

    public KategoriController(KategoriService service) {
        this.service = service;
    }

    // Tüm kategorileri listeler
    @GetMapping
    public List<Kategori> tumKategoriler() {
        // Frontend'deki kategori dropdown menülerini doldurmak için kullanılır
        return service.tumKategoriler();
    }

    // Yeni kategori ekler
    @PostMapping("/ekle") // Endpoint ismini KitapController ile uyumlu hale getirdik
    public ResponseEntity<?> kategoriEkle(@RequestBody Kategori kategori) {
        try {
            Kategori yeniKategori = service.kaydet(kategori);
            return ResponseEntity.ok(yeniKategori);
        } catch (Exception e) {
            // Hata olursa 500 fırlatmak yerine terminale hatayı basar ve 400 döner
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Kategori eklenemedi: " + e.getMessage());
        }
    }

    // Kategoriyi siler
    @DeleteMapping("/{id}")
    public ResponseEntity<String> kategoriSil(@PathVariable Integer id) {
        try {
            service.sil(id);
            return ResponseEntity.ok("Kategori başarıyla silindi.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Silme işlemi başarısız.");
        }
    }
}