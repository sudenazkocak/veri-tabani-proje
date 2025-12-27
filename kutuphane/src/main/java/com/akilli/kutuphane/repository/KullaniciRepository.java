package com.akilli.kutuphane.repository;

import com.akilli.kutuphane.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface KullaniciRepository extends JpaRepository<Kullanici, Integer> {
    // Harf hatası olmaması için bunu kullan:
    Optional<Kullanici> findByEmailAndSifre(String email, String sifre);
}