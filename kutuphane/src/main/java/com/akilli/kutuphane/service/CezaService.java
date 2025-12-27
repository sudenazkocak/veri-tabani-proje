package com.akilli.kutuphane.service;
import com.akilli.kutuphane.entity.Ceza;
import com.akilli.kutuphane.repository.CezaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CezaService {
    private final CezaRepository repository;
    public CezaService(CezaRepository repository) { this.repository = repository; }
    public List<Ceza> tumCezalar() { return repository.findAll(); }
    public Ceza oduncunCezasi(Integer oduncID) { return repository.findByOdunc_OduncID(oduncID); }
}