package com.mycompany.webkatalogvideoigara.controller; 
 import com.mycompany.webkatalogvideoigara.model.Komentar; 
 import org.springframework.web.bind.annotation.RequestMapping;
import com.mycompany.webkatalogvideoigara.model.dto.KomentarDTO; 
import org.springframework.stereotype.Controller; 
import com.mycompany.webkatalogvideoigara.service.GenericService;
@Controller 
@RequestMapping("/api/komentari")
public class KomentarController extends GenericController<Komentar,KomentarDTO,GenericService<Komentar, Integer>>{
public KomentarController(GenericService<Komentar, Integer> service){
 super(service);
}}