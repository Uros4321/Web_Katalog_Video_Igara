package com.mycompany.webkatalogvideoigara.service;

import org.springframework.stereotype.Service;
import com.mycompany.webkatalogvideoigara.model.KategorijaVideoIgre;
import com.mycompany.webkatalogvideoigara.repository.KategorijaVideoIgreRepository;

@Service
public class KategorijaVideoIgreService extends GenericService<KategorijaVideoIgre, Integer> {

    public KategorijaVideoIgreService(KategorijaVideoIgreRepository repository) {
        super(repository);
    }
}
