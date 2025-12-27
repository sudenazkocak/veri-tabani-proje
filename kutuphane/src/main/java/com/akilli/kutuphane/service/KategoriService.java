package com.akilli.kutuphane.service;

import com.akilli.kutuphane.entity.Kategori;
import com.akilli.kutuphane.repository.KategoriRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Spring Boot'un bu sınıfı servis olarak tanıması için şart
public class KategoriService {

    private final KategoriRepository repository;

    // Constructor Injection: Repository bağlantısını güvenli yapar
    public KategoriService(KategoriRepository repository) { 
        this.repository = repository; 
    }

    // Tüm kategorileri listeler
    public List<Kategori> tumKategoriler() { 
        return repository.findAll(); 
    }

    // Yeni kategori kaydeder veya günceller
    public Kategori kaydet(Kategori k) { 
        return repository.save(k); 
    }

    // Kategoriyi veritabanından siler
    public void sil(Integer id) {
        repository.deleteById(id);
    }

    // Opsiyonel: ID'ye göre tek bir kategori getirir (Hata yönetimli)
    public Kategori getirById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}