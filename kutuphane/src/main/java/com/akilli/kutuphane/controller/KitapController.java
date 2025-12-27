package com.akilli.kutuphane.controller;

import com.akilli.kutuphane.entity.Kitap;
import com.akilli.kutuphane.service.KitapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/kitaplar")
@CrossOrigin(origins = "*") // Frontend'den gelen isteklerin engellenmemesi için şart
public class KitapController {

    private final KitapService service;

    public KitapController(KitapService service) { 
        this.service = service; 
    }

    // Tüm kitapları getirir
    @GetMapping
    public List<Kitap> tumKitaplar() {
        return service.tumKitaplar();
    }

    // Kitap adına göre arama yapar
    // Örnek: /api/kitaplar/ara?kelime=nutuk
    @GetMapping("/ara")
    public List<Kitap> kitapAra(@RequestParam String kelime) {
        return service.kitapAra(kelime);
    }

    // Belirli bir kategoriye ait kitapları listeler
    // Örnek: /api/kitaplar/kategori/1
    @GetMapping("/kategori/{kategoriID}")
    public List<Kitap> kategoriyeGoreListele(@PathVariable Integer kategoriID) {
        return service.kategoriyeGoreGetir(kategoriID);
    }

    // Yeni kitap ekler
    @PostMapping("/ekle") // Frontend'deki handleKitapEkle ile uyumlu hale getirildi
    public ResponseEntity<Kitap> kitapKaydet(@RequestBody Kitap kitap) {
        try {
            Kitap yeniKitap = service.kitapKaydet(kitap);
            return ResponseEntity.ok(yeniKitap);
        } catch (Exception e) {
            // Bir hata oluşursa (örneğin sütun ismi hatası) terminalde görmeni sağlar
            return ResponseEntity.internalServerError().build();
        }
    }

    // Kitap siler
    @DeleteMapping("/{id}")
    public ResponseEntity<String> kitapSil(@PathVariable Integer id) {
        try {
            service.kitapSil(id);
            return ResponseEntity.ok("Kitap başarıyla silindi.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Silme işlemi başarısız.");
        }
    }
}