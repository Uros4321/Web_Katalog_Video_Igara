package com.mycompany.webkatalogvideoigara.controller; 
 import com.mycompany.webkatalogvideoigara.model.VideoIgra; 
 import org.springframework.web.bind.annotation.RequestMapping;
import com.mycompany.webkatalogvideoigara.model.dto.VideoIgraDTO; 
import org.springframework.stereotype.Controller; 
import com.mycompany.webkatalogvideoigara.service.GenericService;
@Controller 
@RequestMapping("/api/igre")
public class VideoIgraController extends GenericController<VideoIgra,VideoIgraDTO,GenericService<VideoIgra, Integer>>{
public VideoIgraController(GenericService<VideoIgra, Integer> service){
 super(service);
}}