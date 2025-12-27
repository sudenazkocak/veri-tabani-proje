package com.akilli.kutuphane.controller;

import com.akilli.kutuphane.entity.Kullanici;
import com.akilli.kutuphane.service.KullaniciService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kullanicilar")
@CrossOrigin(origins = "*") // Tarayıcı engellini kaldırır
public class KullaniciController {
    private final KullaniciService service;

    public KullaniciController(KullaniciService service) { this.service = service; }

    @PostMapping("/kayit")
    public Kullanici kayit(@RequestBody Kullanici kullanici) {
        return service.kayitEt(kullanici);
    }
@PostMapping("/giris")
public ResponseEntity<?> giris(@RequestBody Kullanici loginData) {
    try {
        // Gelen veriyi kontrol etmek için konsola yazdır
        System.out.println("Giris denemesi: " + loginData.getEmail());
        
        Kullanici user = service.girisYap(loginData.getEmail(), loginData.getSifre());
        
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            // Kullanıcı yoksa 401 dönüyoruz, 500 değil!
            return ResponseEntity.status(401).body("E-posta veya şifre hatalı.");
        }
    } catch (Exception e) {
        e.printStackTrace(); // Gerçek hatayı VS Code terminaline basar
        return ResponseEntity.status(500).body("Sunucu Hatası: " + e.getMessage());
    }
}
}