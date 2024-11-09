package com.mycompany.webkatalogvideoigara.service;

import org.springframework.stereotype.Service;
import com.mycompany.webkatalogvideoigara.model.VideoIgra;
import com.mycompany.webkatalogvideoigara.repository.VideoIgraRepository;

@Service
public class VideoIgraService extends GenericService<VideoIgra, Integer> {

    public VideoIgraService(VideoIgraRepository repository) {
        super(repository);
    }
}
