package com.mycompany.webkatalogvideoigara.controller; 
 import com.mycompany.webkatalogvideoigara.model.Komentar; 
 import org.springframework.web.bind.annotation.RequestMapping;
import com.mycompany.webkatalogvideoigara.model.dto.KomentarDTO; 
import org.springframework.stereotype.Controller; 
import com.mycompany.webkatalogvideoigara.service.GenericService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = {"http://localhost:3000","http://127.0.0.1:3000"}, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS })
@Controller 
@RequestMapping("/api/komentari")
public class KomentarController extends GenericController<Komentar,KomentarDTO,GenericService<Komentar, Integer>>{
public KomentarController(GenericService<Komentar, Integer> service){
 super(service);
}}