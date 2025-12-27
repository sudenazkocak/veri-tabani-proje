package com.akilli.kutuphane.service;

import com.akilli.kutuphane.entity.Yazar;
import com.akilli.kutuphane.repository.YazarRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class YazarService {
    private final YazarRepository repository;

    public YazarService(YazarRepository repository) {
        this.repository = repository;
    }

    // Tüm yazarları listele (Kitap ekleme ekranındaki açılır menü için)
    public List<Yazar> tumYazarlar() {
        return repository.findAll();
    }

    // ID'ye göre yazar getir
    public Yazar yazarBul(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Yazar bulunamadı!"));
    }

    // Yeni yazar ekle veya güncelle
    public Yazar yazarKaydet(Yazar yazar) {
        return repository.save(yazar);
    }

    // Yazar sil
    public void yazarSil(Integer id) {
        repository.deleteById(id);
    }
}