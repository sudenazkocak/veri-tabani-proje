package com.akilli.kutuphane.controller;

import com.akilli.kutuphane.entity.Yazar;
import com.akilli.kutuphane.service.YazarService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/yazarlar")
@CrossOrigin(origins = "*")
public class YazarController {

    private final YazarService service;

    public YazarController(YazarService service) {
        this.service = service;
    }

    // Tüm yazarları getirir (Kitap eklerken seçmek için)
    @GetMapping
    public List<Yazar> tumYazarlar() {
        return service.tumYazarlar();
    }

    // Yeni yazar ekler
    @PostMapping
    public Yazar yazarEkle(@RequestBody Yazar yazar) {
        return service.yazarKaydet(yazar);
    }

    // Yazarı siler
    @DeleteMapping("/{id}")
    public void yazarSil(@PathVariable Integer id) {
        service.yazarSil(id);
    }
}