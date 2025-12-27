package com.akilli.kutuphane.repository;

import com.akilli.kutuphane.entity.Yazar;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface YazarRepository extends JpaRepository<Yazar, Integer> {
    // KitapService'deki 'orElseGet' mantığının çalışması için bu metot şarttır!
    Optional<Yazar> findByAdIgnoreCase(String ad);
}