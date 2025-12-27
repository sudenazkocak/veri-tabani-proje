package com.akilli.kutuphane.controller;

import com.akilli.kutuphane.entity.Odunc;
import com.akilli.kutuphane.entity.Ceza;
import com.akilli.kutuphane.repository.OduncRepository;
import com.akilli.kutuphane.repository.CezaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*") // Frontend'in (9091 portu) backend ile konuşması için zorunlu
public class AdminController {

    @Autowired
    private OduncRepository oduncRepository;

    @Autowired
    private CezaRepository cezaRepository;

    /**
     * Tüm ödünç işlemlerini detaylarıyla (Kullanıcı ve Kitap bilgisi dahil) getirir.
     * Bu endpoint index.html içindeki admin takibi için kullanılır.
     */
    @GetMapping("/detayli-takip")
    public List<Odunc> getDetayliTakip() {
        // findAll() veritabanındaki tüm ilişkili tabloları otomatik bağlar (Join)
        return oduncRepository.findAll(); 
    }

    /**
     * Sistemdeki tüm cezaları listeler.
     */
    @GetMapping("/tum-cezalar")
    public List<Ceza> getTumCezalar() {
        return cezaRepository.findAll();
    }

    /**
     * Alternatif ödünç listeleme endpoint'i.
     */
    @GetMapping("/tum-oduncler")
    public List<Odunc> getTumOduncler() {
        return oduncRepository.findAll();
    }
}