package com.mycompany.webkatalogvideoigara.service;

import com.mycompany.webkatalogvideoigara.model.KategorijaVideoIgre;
import org.springframework.stereotype.Service;
import com.mycompany.webkatalogvideoigara.model.VideoIgra;
import com.mycompany.webkatalogvideoigara.repository.VideoIgraRepository;
import java.util.List;

@Service
public class VideoIgraService extends GenericService<VideoIgra, Integer> {
    private final VideoIgraRepository r;
    public VideoIgraService(VideoIgraRepository repository) {
        super(repository);
        this.r = repository;
    }
    public Iterable<VideoIgra> filterCategories(List<KategorijaVideoIgre> includedCategories,List<KategorijaVideoIgre> excludeCategories){
        if(includedCategories.isEmpty()){
            if(excludeCategories.isEmpty()){
                return repository.findAll();
            }
            return r.findAllVideoIgraWithoutCategories(excludeCategories);
        }else if(excludeCategories.isEmpty()){
            return r.findAllVideoIgraWithCategories(includedCategories);
        }else{
            return r.findAllVideoIgraWithCategoriesAndWithoutCategories(includedCategories, excludeCategories);
        }
    };
}
