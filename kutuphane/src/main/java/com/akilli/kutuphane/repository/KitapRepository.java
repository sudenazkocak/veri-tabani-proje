package com.akilli.kutuphane.repository;

import com.akilli.kutuphane.entity.Kitap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository // Spring'in bu sınıfı tanıması için ekledik
public interface KitapRepository extends JpaRepository<Kitap, Integer> {

    // Kitap adına göre arama (Arama çubuğu için)
    // Containing: SQL'deki LIKE '%kelime%' mantığıyla çalışır.
    // IgnoreCase: Büyük/küçük harf ayrımını ortadan kaldırır.
    List<Kitap> findByAdContainingIgnoreCase(String ad);

    // Belirli bir kategorideki tüm kitapları getir
    // Kitap entity içindeki 'kategori' nesnesinin 'kategoriID' alanına göre arar
    List<Kitap> findByKategori_KategoriID(Integer kategoriID);
    
    // İhtiyacın olabilir: Belirli bir yazarın kitaplarını getir
    List<Kitap> findByYazar_YazarID(Integer yazarID);
}