package com.akilli.kutuphane.service;

import com.akilli.kutuphane.entity.*;
import com.akilli.kutuphane.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OduncService {
    private final OduncRepository oduncRepository;
    private final KitapRepository kitapRepository;
    private final CezaRepository cezaRepository;

    public OduncService(OduncRepository oduncRepository, KitapRepository kitapRepository, CezaRepository cezaRepository) {
        this.oduncRepository = oduncRepository;
        this.kitapRepository = kitapRepository;
        this.cezaRepository = cezaRepository;
    }

    @Transactional
    public Odunc oduncAl(Odunc odunc) {
        Kitap kitap = kitapRepository.findById(odunc.getKitap().getKitapID())
                .orElseThrow(() -> new RuntimeException("Kitap bulunamadı!"));

        if (kitap.getStok() <= 0) {
            throw new RuntimeException("Kitap stokta kalmamıştır!");
        }

        kitap.setStok(kitap.getStok() - 1);
        kitapRepository.save(kitap);

        // Ödünç zamanını saniye/dakika hassasiyetiyle kaydet
        odunc.setOduncTarihi(LocalDateTime.now()); 
        return oduncRepository.save(odunc);
    }

   @Transactional
public Odunc iadeEt(Integer oduncID) {
    Odunc odunc = oduncRepository.findById(oduncID)
            .orElseThrow(() -> new RuntimeException("Kayıt yok!"));

    odunc.setIadeTarihi(LocalDateTime.now());

    Kitap kitap = odunc.getKitap();
    kitap.setStok(kitap.getStok() + 1);
    kitapRepository.save(kitap);

    // DAKİKA FARKINI HESAPLA
    long dakikaFarki = java.time.temporal.ChronoUnit.MINUTES.between(odunc.getOduncTarihi(), odunc.getIadeTarihi());
    
    if (dakikaFarki >= 1) { // 1 dakikayı geçtiği an ceza başlar
        Ceza ceza = new Ceza();
        ceza.setOdunc(odunc);
        ceza.setGunSayisi((int) dakikaFarki); 
        ceza.setTutar(java.math.BigDecimal.valueOf(dakikaFarki * 20.0)); // Dakika başı 20 TL
        cezaRepository.save(ceza);
    }
    return oduncRepository.save(odunc);
}
    // Kullanıcının toplam borcunu hesaplayan yeni metot (Sitede göstermek için)
    public BigDecimal kullaniciToplamCeza(Integer kullaniciID) {
        return cezaRepository.findAll().stream()
                .filter(c -> c.getOdunc().getKullanici().getKullaniciID().equals(kullaniciID))
                .map(Ceza::getTutar)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Odunc> kullaniciyaGoreGetir(Integer kullaniciID) {
        return oduncRepository.findByKullanici_KullaniciIDAndIadeTarihiIsNull(kullaniciID);
    }

    public List<Odunc> aktifOduncler() {
        return oduncRepository.findByIadeTarihiIsNull();
    }
}