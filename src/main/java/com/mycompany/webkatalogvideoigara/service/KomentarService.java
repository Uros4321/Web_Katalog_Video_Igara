package com.mycompany.webkatalogvideoigara.service;

import org.springframework.stereotype.Service;
import com.mycompany.webkatalogvideoigara.model.Komentar;
import com.mycompany.webkatalogvideoigara.repository.KomentarRepository;

@Service
public class KomentarService extends GenericService<Komentar, Integer> {

    public KomentarService(KomentarRepository repository) {
        super(repository);
    }
}
