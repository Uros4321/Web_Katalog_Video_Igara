package com.mycompany.webkatalogvideoigara.service;

import org.springframework.stereotype.Service;
import com.mycompany.webkatalogvideoigara.model.Platforma;
import com.mycompany.webkatalogvideoigara.repository.PlatformaRepository;

@Service
public class PlatformaService extends GenericService<Platforma, Integer> {

    public PlatformaService(PlatformaRepository repository) {
        super(repository);
    }
}
