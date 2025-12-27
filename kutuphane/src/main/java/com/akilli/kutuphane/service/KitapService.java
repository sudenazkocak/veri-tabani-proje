package com.akilli.kutuphane.service;

import com.akilli.kutuphane.entity.Kitap;
import com.akilli.kutuphane.entity.Yazar;
import com.akilli.kutuphane.repository.KitapRepository;
import com.akilli.kutuphane.repository.YazarRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KitapService {

    private final KitapRepository repository;
    private final YazarRepository yazarRepository;

    // Constructor Injection: Repository bağlantılarını sağlar
    public KitapService(KitapRepository repository, YazarRepository yazarRepository) { 
        this.repository = repository; 
        this.yazarRepository = yazarRepository;
    }

    /**
     * Akıllı Kayıt: Yazarı isminden kontrol eder, yoksa SQL'e ekler.
     * Bu metot sayesinde frontend'den gelen yazar ismi otomatik yönetilir.
     */
    public Kitap kitapKaydet(Kitap kitap) {
        if (kitap.getYazar() != null && kitap.getYazar().getAd() != null) {
            String yazarAdi = kitap.getYazar().getAd().trim();

            // SQL'de bu isimle yazar var mı bakıyoruz (Büyük/küçük harf duyarsız)
            Yazar yazar = yazarRepository.findByAdIgnoreCase(yazarAdi)
                .orElseGet(() -> {
                    // Eğer yazar yoksa, yeni bir yazar oluşturup SQL'e kaydediyoruz
                    Yazar yeniYazar = new Yazar();
                    yeniYazar.setAd(yazarAdi);
                    return yazarRepository.save(yeniYazar);
                });

            // Kitabı bulduğumuz veya yeni oluşturduğumuz yazara bağlıyoruz
            kitap.setYazar(yazar);
        }
        
        // Kitabı ve ilişkili yazar bilgisini SQL'e kaydet
        return repository.save(kitap);
    } 

    // Tüm kitapları listeler - Katalog paneli için
    public List<Kitap> tumKitaplar() {
        return repository.findAll();
    }

    // Kategoriye göre filtreleme yapar
    public List<Kitap> kategoriyeGoreGetir(Integer kategoriID) {
        return repository.findByKategori_KategoriID(kategoriID);
    }

    // Arama kutusu için gerekli metot
    public List<Kitap> kitapAra(String kelime) {
        return repository.findByAdContainingIgnoreCase(kelime);
    }

    // Kitap silme işlemi
    public void kitapSil(Integer id) {
        repository.deleteById(id);
    }
    
    // ID bazlı tekil kitap getirme (Ödünç işlemleri için)
    public Kitap getirById(Integer id) {
        return repository.findById(id).orElse(null);
    }
} // SINIFIN EN SONU BURASI!