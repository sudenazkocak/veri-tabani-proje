package com.akilli.kutuphane.controller;

import com.akilli.kutuphane.entity.Ceza;
import com.akilli.kutuphane.service.CezaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cezalar")
@CrossOrigin(origins = "*")
public class CezaController {

    private final CezaService service;

    public CezaController(CezaService service) {
        this.service = service;
    }

    // Sistemdeki tüm cezaları listeler (Yönetici için)
    @GetMapping
    public List<Ceza> tumCezalar() {
        return service.tumCezalar();
    }

    // Belirli bir ödünç işlemine ait cezayı getirir
    @GetMapping("/odunc/{oduncID}")
    public Ceza cezaGetir(@PathVariable Integer oduncID) {
        return service.oduncunCezasi(oduncID);
    }
}