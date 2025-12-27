package com.akilli.kutuphane.service;

import com.akilli.kutuphane.entity.Kullanici;
import com.akilli.kutuphane.repository.KullaniciRepository;
import org.springframework.stereotype.Service;

@Service
public class KullaniciService {

    private final KullaniciRepository repository;

    // Constructor Injection (En güvenli yol)
    public KullaniciService(KullaniciRepository repository) { 
        this.repository = repository; 
    }

    // Kullanıcıyı veritabanına kaydeder
    public Kullanici kayitEt(Kullanici kullanici) {
        // Eğer kayıt başarılı olursa kaydedilen objeyi, olmazsa hata döner
        return repository.save(kullanici);
    }

    // Giriş kontrolü yapar
    public Kullanici girisYap(String email, String sifre) {
        // Repository'den Optional döner, eğer kullanıcı yoksa null döner
        return repository.findByEmailAndSifre(email, sifre).orElse(null);
    }
}